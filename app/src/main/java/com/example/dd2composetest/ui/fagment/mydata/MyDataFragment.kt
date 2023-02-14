package com.example.dd2composetest.ui.fagment.mydata

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.dd2composetest.MainActivity
import com.example.dd2composetest.R
import com.example.dd2composetest.databinding.FragmentMyDataBinding
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.navigateSingleTopTo
import com.example.dd2composetest.ui.compose.FansPromoteScreen
import com.example.dd2composetest.ui.compose.MyDataScreen
import com.example.dd2composetest.ui.compose.RecommendVideoScreen
import com.example.dd2composetest.ui.compose.myData
import com.example.dd2composetest.ui.fagment.promote.FansPromoteFragment
import com.example.dd2composetest.ui.fagment.promote.RecommendationVideoFragment
import com.google.accompanist.pager.ExperimentalPagerApi

class MyDataFragment : Fragment() {
    private lateinit var binding: FragmentMyDataBinding

    @ExperimentalPagerApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMyDataBinding.inflate(inflater, container, false)
        binding.myDataContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val navController = rememberNavController()
                    MyDataScreen(navController)
//                    ComposeNavigation()
                }
            }
        }

//        val view = NavHostFragment.createView(this, inflater)

        return binding.root
    }

    @Preview
    @Composable
    fun Toolbar() {
        TopAppBar(
            modifier = Modifier.height(56.dp),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 68.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "我的资料", fontSize = 18.sp)
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    parentFragmentManager.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            },
            backgroundColor = Color.Black,
            contentColor = Color.White,
        )
    }

    @ExperimentalPagerApi
    @Composable
    fun Item(imgId: Int, title: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = "",
                modifier = Modifier
                    .size(49.dp, 20.dp)
                    .padding(start = 23.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 21.dp)
                    .weight(0.7f),
                fontSize = 14.sp,
                color = Color(0xff222226)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "",
                modifier = Modifier.padding(end = 13.dp)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color(0xffe2e2e2)
        )
    }

    @ExperimentalPagerApi
    @Preview
    @Composable
    fun PreviewItem(imgId: Int = R.drawable.ic_edit_user, title: String = "编辑个人资料", onClick: () -> Unit = { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = "",
                modifier = Modifier
                    .size(49.dp, 20.dp)
                    .padding(start = 23.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 21.dp)
                    .weight(0.7f),
                fontSize = 14.sp,
                color = Color(0xff222226)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "",
                modifier = Modifier.padding(end = 13.dp)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color(0xffe2e2e2)
        )
    }

    @ExperimentalPagerApi
    @Composable
    fun MyDataScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top
        ) {
            Toolbar()

            // dd2 中不會用到 navigation？
            Item(R.drawable.ic_edit_user, "编辑个人资料") { goToVideo() }
            Item(R.drawable.ic_my_work, "我的作品") { goToFans() }
            Item(R.drawable.ic_notification, "消息通知") { goToVideo() }
            Item(R.drawable.ic_my_favorite, "我的收藏") { goToFans() }

            // ERROR: no current navigation node
//            Item(R.drawable.ic_edit_user, "编辑个人资料") { navController.navigate(R.id.action_to_fansPromote) }
//            Item(R.drawable.ic_my_work, "我的作品") { navController.navigate(R.id.action_to_fansPromote) }

            // ERROR: java.lang.IllegalStateException: You must call setGraph() before calling getGraph()
//            Item(R.drawable.ic_edit_user, "编辑个人资料") { navController.navigateSingleTopTo(Screen.RECOMMEND_VIDEO_SCREEN.route) }
//            Item(R.drawable.ic_my_work, "我的作品") { navController.navigateSingleTopTo(Screen.RECOMMEND_VIDEO_SCREEN.route) }

            // @Composable invocations can only happen from the context of a @Composable function
//            Item(R.drawable.ic_edit_user, "编辑个人资料") { NavigateToVideo() }
        }
    }

    @ExperimentalPagerApi
    @Preview
    @Composable
    fun PreviewMyDataScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top
        ) {
            Toolbar()
            Item(R.drawable.ic_edit_user, "编辑个人资料") {  }
            Item(R.drawable.ic_my_work, "我的作品") {  }
            Item(R.drawable.ic_notification, "消息通知") {  }
            Item(R.drawable.ic_my_favorite, "我的收藏") {  }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyDataFragment()
    }

    private val msgs = listOf(0, 1, 2, 3, 1, 2)

    fun goToVideo() {
        // navigation
        findNavController().navigate(R.id.action_to_recommendationVideo)
//        (activity as MainActivity).onReplaceFragment(RecommendationVideoFragment())

        // dd2 跳轉
//        startActivityForResult(Intent(this, UserActivity::class.java), 999)
    }

    fun goToFans() {
        // navigation
        findNavController().navigate(R.id.action_to_fansPromote)
//        (activity as MainActivity).onReplaceFragment(FansPromoteFragment())
    }

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    fun NavGraphBuilder.myData(navController: NavHostController) {
        composable(Screen.MY_DATA_SCREEN.route) {
            com.example.dd2composetest.ui.compose.MyDataScreen(navController = navController)
        }
    }

    fun NavHostController.navigateToMyData() {
        navigate(Screen.MY_DATA_SCREEN.route) {
            launchSingleTop = true
        }
    }

//    @ExperimentalPagerApi
//    @Composable
//    fun ComposeNavigation() {
//        val navController = rememberNavController()
//        NavHost(
//            navController = navController,
//            startDestination = Screen.MY_DATA_SCREEN.route
//        ) {
//            composable(Screen.MY_DATA_SCREEN.route) {
//                MyDataScreen()
//            }
//            composable(Screen.RECOMMEND_VIDEO_SCREEN.route) {
//                RecommendVideoScreen(
//                    onClickBack = { navController.popBackStack() },
//                    msgs = msgs
//                )
//            }
//            composable(Screen.FANS_PROMOTE_SCREEN.route) {
//                FansPromoteScreen(onClickBack = { navController.popBackStack() })
//            }
//        }
//    }

//    private fun NavHostController.navigateSingleTopTo(route: String) =
//        this.navigate(route) {
//            popUpTo(
//                this@navigateSingleTopTo.graph.findStartDestination().id
//            ) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }

//    @Composable
//    fun NavigateToVideo() {
//        val navController = rememberNavController()
//        NavHost(navController = navController,
//            startDestination = Screen.MY_DATA_SCREEN.route
//        ) {
//            composable(Screen.RECOMMEND_VIDEO_SCREEN.route) {
//                RecommendVideoScreen(
//                    onClickBack = { navController.popBackStack() },
//                    msgs = msgs
//                )
//            }
//        }
//    }
//
//    @ExperimentalPagerApi
//    @Composable
//    fun NavigateToFans() {
//        val navController = rememberNavController()
//        NavHost(navController = navController,
//            startDestination = Screen.MY_DATA_SCREEN.route
//        ) {
//            composable(Screen.FANS_PROMOTE_SCREEN.route) {
//                FansPromoteScreen(onClickBack = { navController.popBackStack() })
//            }
//        }
//    }
}

//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//fun NavGraphBuilder.searchTag(navController: NavHostController) {
//    composable(SCREEN_SEARCH_TAG) {
//        SearchTagScreen(navController = navController)
//    }
//}
//
//fun NavHostController.navigateToSearchTag() {
//    navigate(SCREEN_SEARCH_TAG) {
//        launchSingleTop = true
//    }
//}

