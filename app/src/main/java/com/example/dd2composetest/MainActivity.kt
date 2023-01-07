package com.example.dd2composetest

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.*
import com.example.dd2composetest.ui.compose.mine.myCoin
import com.example.dd2composetest.ui.compose.payment.payChoose
import com.example.dd2composetest.ui.compose.setting.setting
import com.example.dd2composetest.ui.mine.MineFragment
import com.example.dd2composetest.ui.mydata.MyDataFragment
import com.example.dd2composetest.ui.promote.FansPromoteFragment
import com.example.dd2composetest.ui.promote.PromoteSettingFragment
import com.example.dd2composetest.ui.promote.RecommendationVideoFragment
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : AppCompatActivity() {
    var lastFragment: Fragment? = null

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        val navController = findNavController(R.id.fragmentContainer)

        setContent {
            MaterialTheme {
                ComposeNavigation()
            }
        }

        val f1 = RecommendationVideoFragment.newInstance()
        val f2 = PromoteSettingFragment.newInstance() // x
        val f3 = FansPromoteFragment.newInstance()
        val f4 = MyDataFragment.newInstance()
        val f5 = MineFragment.newInstance()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, f5).commit()
//        onReplaceFragment(f4)
    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.fragmentContainer).navigateUp()
//    }

//    fun onReplaceFragment(fragment: Fragment) {
//        try {
//            val fb = supportFragmentManager.beginTransaction()
//            lastFragment?.let {
//                fb.hide(it)
//                fb.remove(it)
//            }
//            if (!fragment.isAdded) fb.add(R.id.fragmentContainer, fragment)
//            else fb.show(fragment)
//            lastFragment = fragment
//            fb.commit()
//        } catch (e: Exception) {
//            Log.d("AppDebug", e.toString())
//        }
//    }
}

private val msgs = listOf(0, 1, 2, 3, 1, 2)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MINE_SCREEN.route
    ) {
        mine(navController)
        setting(navController)
        myCoin(navController)
        myData(navController)
        fansPromote(navController)
        recommendationVideo(navController)
        payChoose(navController)

//        composable(Screen.MY_DATA_SCREEN.route) {
//            MyDataScreen(
////                onClickRecommendVideo = {
////                    navController.navigateSingleTopTo(Screen.RECOMMEND_VIDEO_SCREEN.route)
////                },
////                onClickFansPromote = {
////                    navController.navigateSingleTopTo(Screen.FANS_PROMOTE_SCREEN.route)
////                }
//            navController
//            )
//        }
//        composable(Screen.RECOMMEND_VIDEO_SCREEN.route) {
//            RecommendVideoScreen(
//                onClickBack = {
//                    navController.popBackStack()
//                },
//                msgs
//            )
//        }
//        composable(Screen.FANS_PROMOTE_SCREEN.route) {
//            FansPromoteScreen(onClickBack = { navController.popBackStack() })
//        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    DD2ComposeTestTheme {
//        Greeting("Android")
//    }
//}