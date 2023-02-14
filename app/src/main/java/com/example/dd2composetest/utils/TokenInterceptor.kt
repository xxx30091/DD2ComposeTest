package com.example.dd2composetest.utils

import android.content.Context
import android.util.Log
import com.example.dd2composetest.ThisApp
import com.example.dd2composetest.data.bean.ApiResponseBean
import com.example.dd2composetest.data.bean.LoginData
import com.example.dd2composetest.data.service.API_DOMAIN
import com.example.dd2composetest.data.service.ApiService
import com.example.dd2composetest.preference.Preference
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.nio.charset.Charset

class TokenInterceptor : Interceptor {

    private val TAG = "TokenInterceptor"

    private val UTF8: Charset = Charset.forName("UTF-8")

    private val context: Context? = null

    private val LOCK_OBJ = Any()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        // step 1: 取得原始Response
        //原請求结果為空 直接返回结果
        val originResponse = chain.proceed(originRequest)
        if (originResponse == null) return originResponse

        //step2： 請求頭中不含 token 直接返回原請求结果
        val originReqAccessToken = originRequest.header("Authorization")
        if (originReqAccessToken.isNullOrEmpty()) return originResponse

        // 進行同步處理防止多線程並發場景下造成重複刷新
        synchronized(LOCK_OBJ) {

            // 拿到返回結果
            // 返回結果為空 直接返回
            val responseBody = originResponse.body ?: return originResponse

            // step5：讀取返回數據並判斷token是否失效、如果失效執行刷新操作
            // 設置編碼 準備讀取返回數據
            var charSet = UTF8
            val bufferSource = responseBody.source()
            bufferSource.request(Long.MAX_VALUE)
            val buffer = bufferSource.buffer

            val contentType = responseBody.contentType()
            if (contentType != null) {
                Log.e(TAG, "contentType.charset(UTF8)")
                charSet = contentType.charset(UTF8)!!
            }

            //編碼為空 直接返回
            if (charSet == null) {
                Log.e(TAG, "null charSet, originResponse")
                return originResponse
            }

            // 網絡請求返回數據
            if (originResponse.code == 401) {
                val isRefreshTokenOk = requestTokenAndSaveData()
                if (isRefreshTokenOk) {
                    // 獲取原 請求頭信息
                    val originHeaders = originRequest.headers

                    // 如果請求頭中含有 token  請求字段 重新添加更新後的token
                    if (originHeaders.names().contains("Authorization")) {
                        // 構建新的請求頭
                        val userToken = Preference.getInstance(ThisApp.instance).getUserToken()
                        val newHeaders = originHeaders
                            .newBuilder()
                            .set("Authorization", "Bearer $userToken")
                            .build()
                        // 重新構建請求
                        val newRequest = originRequest
                            .newBuilder()
                            .headers(newHeaders)
                            .build()

                        // 重新發起請求
                        originResponse.close()
                        Log.e(TAG, "chain proceed newRequest")
                        return chain.proceed(newRequest)
                    }
                    // 重新發起請求
                    originResponse.close()
                    Log.e(TAG, "chain proceed originRequest")
                    return chain.proceed(originRequest)
                }
                return originResponse
            }
        }
        Log.e(TAG, "originResponse")
        return originResponse
    }

    private fun requestTokenAndSaveData(): Boolean {
        val okHttpClient = OkHttpClient()
        val refreshToken = Preference.getInstance(ThisApp.instance).getRefreshToken() ?: ""

        val body = MultipartBody
            .Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("refreshToken", refreshToken)
            .build()

        val response = okHttpClient
            .newCall(
                HttpUtils.getRequestUseFormUrlencoded(body,
                    API_DOMAIN + ApiService.REFRESH_TOKEN
                )
            )
            .execute()

        try {
            if (!response.isSuccessful) {
                return false
            }
            val responseBody = response.body ?: return false
            val resultData = responseBody.string()
            if (resultData.isNullOrEmpty()) return false

            var responseData = ApiService.gson.fromJson(resultData, ApiResponseBean::class.java)
            if (responseData.code != 0) return false

            val tokenData = ApiService.gson.fromJson(
                ApiService.gson.toJson(responseData.date), LoginData::class.java)

            Preference.getInstance(ThisApp.instance).saveUserToken(tokenData.accessToken)
            Preference.getInstance(ThisApp.instance).saveRefreshToken(tokenData.refreshToken)
            return true
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            return false
        }
    }

}