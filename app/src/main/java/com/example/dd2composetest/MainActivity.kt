package com.example.dd2composetest

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.preference.Preference
import com.example.dd2composetest.ui.compose.*
import com.example.dd2composetest.ui.compose.components.datepicker.datePicker
import com.example.dd2composetest.ui.compose.engage.myEngage
import com.example.dd2composetest.ui.compose.login.login
import com.example.dd2composetest.ui.compose.mine.*
import com.example.dd2composetest.ui.compose.mine.myworks.myWorks
import com.example.dd2composetest.ui.compose.payment.payChoose
import com.example.dd2composetest.ui.compose.setting.setting
import com.example.dd2composetest.ui.fagment.mine.MineFragment
import com.example.dd2composetest.ui.fagment.mydata.MyDataFragment
import com.example.dd2composetest.ui.fagment.promote.FansPromoteFragment
import com.example.dd2composetest.ui.fagment.promote.PromoteSettingFragment
import com.example.dd2composetest.ui.fagment.promote.RecommendationVideoFragment
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var lastFragment: Fragment? = null

    @ExperimentalTextApi
    @ExperimentalMaterialNavigationApi
    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val navController = findNavController(R.id.fragmentContainer)
//        val myCoinViewModel by viewModels<MyCoinViewModel>()

        setContent {
            val navigator = rememberBottomSheetNavigator(true)
            MaterialTheme {
                com.google.accompanist.navigation.material.ModalBottomSheetLayout(
                    bottomSheetNavigator = navigator,
                    sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    sheetBackgroundColor = Color.White
                ) {
                    ComposeNavigation(this)

                }
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

@ExperimentalTextApi
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ComposeNavigation(activity: MainActivity) {
    val navigator = rememberBottomSheetNavigator(skipHalfExpanded = true)
    val navController = rememberNavController(navigator)
    val isLogin = Preference.getInstance(ThisApp.instance).getLoginStatus()

    com.google.accompanist.navigation.material.ModalBottomSheetLayout(
        bottomSheetNavigator = navigator,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = if (isLogin) Screen.MINE_SCREEN.route
            else Screen.LOGIN_SCREEN.route
//        Screen.LOGIN_SCREEN.route
        ) {
            login(navController)
            mine(navController)
            editUser(navController)
            setting(navController)
            myCoin(navController, activity)
            myData(navController)
            myWorks(navController, activity)
            fansPromote(navController)
            recommendationVideo(navController)
            payChoose(navController)
            myEngage(navController)
            dateRangePicker(navController)
            datePicker(navController)

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

@ExperimentalMaterialNavigationApi
@ExperimentalMaterialApi
@Composable
public fun rememberBottomSheetNavigator(skipHalfExpanded: Boolean): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        skipHalfExpanded = skipHalfExpanded,
        confirmStateChange = { true }
    )
    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
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