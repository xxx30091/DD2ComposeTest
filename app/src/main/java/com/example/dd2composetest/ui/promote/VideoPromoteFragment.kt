package com.example.dd2composetest.ui.promote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dd2composetest.R
import com.example.dd2composetest.data.VideoPromoteData
import com.example.dd2composetest.databinding.FragmentVideoPromoteBinding

class VideoPromoteFragment : Fragment() {
    lateinit var binding: FragmentVideoPromoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideoPromoteBinding.inflate(inflater, container, false)
        binding.videoPromoteContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme() {

                }
            }
        }


        return binding.root
    }

    @Preview
    @Composable
    fun ToolBar() {
        TopAppBar(
            modifier = Modifier.height(56.dp),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 68.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "视频推广", fontSize = 18.sp)
                }
            },
            navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            },
            backgroundColor = Color.Black,
            contentColor = Color.White,
        )
    }

    @Preview
    @Composable
    fun VideoPromoteHeader() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(Color.White, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "已设置的推广",
                fontSize = 14.sp,
                color = Color(0xFF50585D),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color(0xFFF5F5F5)
        )
    }

    @Preview
    @Composable
    fun item(data: VideoPromoteData = VideoPromoteData(223, 12, "搜索栏底部banner位", "2022-12-12", "上架")) {
        val isCheck = remember {
            mutableStateOf(true)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 15.dp)
            ) {
                Column(
                    modifier = Modifier.width(107.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(63.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        painter = painterResource(id = R.drawable.g),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("狀態: ")
                                append(data.status)
                            }
                        )
                        Switch(
                            checked = isCheck.value,
                            onCheckedChange = { isCheck.value = it },
                            modifier = Modifier.height(18.dp),
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = Color(0xFF679ff8),
                                checkedThumbColor = Color.White
                            )

                        )
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoPromoteFragment()
    }
}