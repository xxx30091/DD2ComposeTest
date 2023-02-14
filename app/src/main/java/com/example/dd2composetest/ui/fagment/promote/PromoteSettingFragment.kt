package com.example.dd2composetest.ui.fagment.promote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.example.dd2composetest.databinding.FragmentPromoteSettingBinding

@Deprecated ("All in fans promote")
class PromoteSettingFragment : Fragment() {
    private lateinit var binding: FragmentPromoteSettingBinding


    var selectedVideo = listOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPromoteSettingBinding.inflate(layoutInflater, container, false)
        binding.promoteSettingCompose.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme() {
                    PromoteSettingScreen(false)
                }
            }
        }

        return binding.root
    }

    @Preview
    @Composable
    fun FansPromote(hasPromoted: Boolean = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
//                    .background(Color.White, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "向自己的粉丝定向推送", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = buildAnnotatedString {
                        append("(每月首次免费，第2次起 ")
                        append(
                            AnnotatedString(
                                text = "300",
                                spanStyle = SpanStyle(
                                    color = Color(0xFFFF3B30)
                                )
                            )
                        )
                        append(" 币/次)")
                    },
                    fontSize = 10.sp,
                    color = Color(0xFF34C759))
            }
            Divider()

            VideoSelector(true)

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(top = 12.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = {

                }
            ) {
                Text(
                    text = if (hasPromoted) "马上推送" else "马上推送 (本次免费)",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }

    @Preview
    @Composable
    fun NotFansPromote(hasPromoted: Boolean = false) {
        var amount by remember {
            mutableStateOf(0)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
//                    .background(Color.White, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "向非粉丝定向推送", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = buildAnnotatedString {
                        append("( ")
                        append(
                            AnnotatedString(
                                text = "300",
                                spanStyle = SpanStyle(
                                    color = Color(0xFFFF3B30)
                                )
                            )
                        )
                        append(" 币/100用户，每月限1次)")
                    },
                    fontSize = 10.sp, color = Color(0xFF34C759)
                )
            }
            Divider()
            //
//            selectedVideo = listOf("", "")
            VideoSelector(false, "")
            VideoSelector(false, "")
            VideoSelector(false)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "推送人数：", fontSize = 14.sp, modifier = Modifier.padding(end = 8.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_promote_neg),
                    contentDescription = "",
                    modifier = Modifier
                        .size(12.dp)
                        .clickable {
                        if (amount >= 100) amount -= 100
                    }
                )
                Text(
                    text = amount.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    color = Color(0xFFFF9500)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_promote_add),
                    contentDescription = "",
                    modifier = Modifier
                        .size(12.dp)
                        .clickable {
                            amount += 100
                        }
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = buildAnnotatedString {
                    append("当前推送的人数，收费  ")
                    append( (amount * 3).toString() )
                    append(" 币")
                },
                fontSize = 12.sp,
                color = Color(0xFF679FF8)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(top = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    if (hasPromoted) Color(0xFFC7C7CC)
                    else Color.Black
                ),
                onClick = {
                    if (!hasPromoted) Toast.makeText(requireContext(), "推送成功", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(requireContext(), "本月已推送过，无法再推送", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(
                    text = if (hasPromoted) "本月已推送过，无法再推送" else "马上推送 (本次免费)",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }

    @Preview
    @Composable
    fun VideoSelector(isFans: Boolean = false, coverUrl: String? = null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                .background(Color(0xFFf5f5f5)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (coverUrl == null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_video),
                    contentDescription = "",
                    modifier = Modifier.size(51.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = if (isFans) "请选择1个你的视频作品" else "请选择你的视频作品（最多可3个)",
                    fontSize = 14.sp,
                    color = Color(0xFF9D9D9D)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.a),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    @Composable
    fun PromoteSettingScreen(hasPromoted: Boolean) {
        val scroll = rememberScrollState()
        Scaffold(
        ) { paddings ->
            Card(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(top = paddings.calculateTopPadding())
            ) {
                Column(
                    modifier = Modifier.verticalScroll(scroll)
                ) {
                    FansPromote()
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .background(Color(0xFFF5F5F5))
                    )
                    NotFansPromote(hasPromoted)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .background(Color(0xFFF5F5F5))
                    )
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PromoteSettingFragment()
    }
}