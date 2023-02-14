package com.example.dd2composetest.ui.fagment.promote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dd2composetest.MainActivity
import com.example.dd2composetest.R
import com.example.dd2composetest.databinding.FragmentRecommendationVideoBinding
import com.example.dd2composetest.ui.fagment.mydata.MyDataFragment

class RecommendationVideoFragment : Fragment() {
    private lateinit var binding: FragmentRecommendationVideoBinding
    val msgs = listOf(0, 1, 2, 3, 1)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendationVideoBinding.inflate(layoutInflater, container, false)
        binding.recommendationCompose.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme() {
                    RecommendVideoScreen(msgs)
                }
            }
        }


        return binding.root
    }

    @Preview
    @Composable
    fun Items(imgCount: Int = 3) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.a),
                    contentDescription = "",
                    modifier = Modifier
                        .size(39.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.size(8.dp))
                Column(
                    modifier = Modifier.width(249.dp)
                ) {
                    Text(text = "零之轨迹", fontSize = 14.sp, color = Color.Black)
                    Spacer(Modifier.size(4.dp))
                    Text(text = "求加粉  《视频标题，标题标题白哦题标题标题，标题标题》",
                        fontSize = 16.sp, color = Color(0xFF50585D))

                    Spacer(Modifier.size(4.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                if (imgCount == 0) 0.dp
                                else 80.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        when (imgCount) {
                            0 -> { }
                            1 -> {
                                Image(painter = painterResource(id = R.drawable.e),
                                    modifier = Modifier
                                        .height(80.dp)
                                        .fillMaxWidth(),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                            }
                            2 -> {
                                Image(painter = painterResource(id = R.drawable.b),
                                    modifier = Modifier.size(80.dp, 80.dp),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                                Spacer(modifier = Modifier.fillMaxHeight().width(4.5.dp))
                                Image(painter = painterResource(id = R.drawable.c),
                                    modifier = Modifier.size(80.dp, 80.dp),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                            }
                            else -> {
                                Image(painter = painterResource(id = R.drawable.b),
                                    modifier = Modifier.size(80.dp, 80.dp),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                                Spacer(modifier = Modifier.fillMaxHeight().width(4.5.dp))
                                Image(painter = painterResource(id = R.drawable.c),
                                    modifier = Modifier.size(80.dp, 80.dp),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                                Spacer(modifier = Modifier.fillMaxHeight().width(4.5.dp))
                                Image(painter = painterResource(id = R.drawable.d),
                                    modifier = Modifier.size(80.dp, 80.dp),
                                    contentDescription = "", contentScale = ContentScale.Crop)
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 27.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "2022-10-12", fontSize = 12.sp, color = Color(0xFFCCCCCC))
            }
            Divider(color = Color(0xFFE6E6E6))
        }
    }

    @Composable
    fun List(msgs: List<Int>) {
        LazyColumn {
            items(msgs) { msgs ->
                Items(msgs)
            }
        }
    }

    @Composable
    fun ToolBar() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Black),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = "视频推荐", color = Color.White, fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            IconButton(
                onClick = {
//                    navController.popBackStack()
                    parentFragmentManager.popBackStack()
                },
                ) {
                Icon(painter = painterResource(id = R.mipmap.close_black),
                    contentDescription = "", tint = Color.White)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewToolBar() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Black),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = "视频推荐", color = Color.White, fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            IconButton(
                onClick = {
//                    (activity as MainActivity).backToMyData()
                },
            ) {
                Icon(painter = painterResource(id = R.mipmap.close_black),
                    contentDescription = "", tint = Color.White)
            }
        }
    }

    @Composable
    fun RecommendVideoScreen(msgs: List<Int>) {
        Column {
            ToolBar()
            List(msgs)
        }
    }

    @Preview
    @Composable
    fun PreviewCustomSwitch(selected: Boolean = true) {
        // this is to disable the ripple effect
        val interactionSource = remember {
            MutableInteractionSource()
        }

        // state of the switch
        var on by remember {
            mutableStateOf(selected)
        }
        val anim by animateFloatAsState(if (on) 1f else -1f)
        val align by derivedStateOf { BiasAlignment(horizontalBias = anim, verticalBias = 0f) }

        Box(
            modifier = Modifier
                .size(width = 34.dp, height = 18.dp)
                .background(if (on) Color(0xFF679ff8) else Color(0xFF9d9d9d), RoundedCornerShape(9.dp))
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    on = !on
                },
            contentAlignment = align
        ) {
            Spacer(
                modifier = Modifier
                    .size(size = 18.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecommendationVideoFragment()
    }
}