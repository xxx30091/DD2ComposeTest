package com.example.dd2composetest.ui.compose.payment

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dd2composetest.R
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.MineScreen
import com.example.dd2composetest.ui.compose.MyDataToolbar

@Preview
@Composable
fun PayPriceChoose() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 14.dp, bottom = 26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 需要跟3個選項一樣寬
        Column(
            modifier = Modifier
//                .wrapContentWidth()
                .width(intrinsicSize = IntrinsicSize.Max)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "充值方案", modifier = Modifier.padding(end = 4.dp),fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "适合分享者，上传视频赚取红币。", fontSize = 12.sp, color = Color(0xff6d7278))
            }
            PayChooseReminder()
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterHorizontally),
            ) {
                PayChooseCellSmall(99.0, 0, false)
                PayChooseCellSmall(148.0, 0, true)
                PayChooseCellSmall(248.0, 10, false)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(2.dp, Color(0xff979797)),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {  }
                    .padding(14.dp)
                    .width(intrinsicSize = IntrinsicSize.Min),
            ) {
                Image(painter = painterResource(id = R.mipmap.golden_coin), contentDescription = "")
                Column(
                    modifier = Modifier.padding(start = 14.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append(
                                AnnotatedString(
                                    "¥ 800.0",
                                    spanStyle = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )
                            )
                            append(
                                AnnotatedString(
                                    text = " ±5",
                                    spanStyle = SpanStyle(
                                        color = Color(0xff0091ff)
                                    )
                                )
                            )
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("多贈")
                            append(
                                AnnotatedString(
                                    text = " 20% ",
                                    spanStyle = SpanStyle(color = Color.Red)
                                )
                            )
                            append("優惠")
                        },
                        fontSize = 10.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(2.dp, Color(0xff979797)),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {  }
                    .padding(14.dp)
                    .width(intrinsicSize = IntrinsicSize.Min),
            ) {
                Image(painter = painterResource(id = R.mipmap.golden_coin), contentDescription = "")
                Column(
                    modifier = Modifier.padding(start = 14.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append(
                                AnnotatedString(
                                    "自选金额",
                                    spanStyle = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )
                            )
                            append(
                                AnnotatedString(
                                    text = "    赠送 ",
                                    spanStyle = SpanStyle(fontSize = 10.sp)
                                )
                            )
                            append(
                                AnnotatedString(
                                    text = "0% - 20%",
                                    spanStyle = SpanStyle(
                                        color = Color(0xffe02020),
                                        fontSize = 10.sp
                                    )
                                )
                            )
                            append(
                                AnnotatedString(
                                    text = " 优惠",
                                    spanStyle = SpanStyle(fontSize = 10.sp)
                                )
                            )
                        },
                    )
                    Text(
                        text = "输入金额，多买多送，限制 ¥90 - 990",
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PayChooseReminder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffc3ddff), RoundedCornerShape(8.dp))
            .padding(6.dp)
            .width(intrinsicSize = IntrinsicSize.Min)
    ) {
        Text(
            text = "特别注意：因受实时汇率影响，你的实际付款金额会和面价有正负5元范围内的浮动，最终支付金额以浮动后价格为准，选择充值即为同意此规则。",
            fontSize = 11.sp,
            color = Color(0xff0091ff),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PayChooseCellSmall(amount: Double = 150.0, discount: Int = 10, isBest: Boolean = true) {
    Box(
        modifier = Modifier
            .clickable {  }
    ) {
        if (isBest) {
            Image(painter = painterResource(id = R.drawable.background_pay_choose_tip), contentDescription = "")
            Text(
                text = "Best",
                modifier = Modifier
                    .rotate(-45f)
                    .padding(start = 3.dp, top = 10.dp),
                color = Color.White,
                fontSize = 10.sp
            )
        }

        Column(
            modifier = Modifier
                .width(117.dp)
                .border(
                    border = BorderStroke(
                        2.dp,
                        if (!isBest) Color(0xff979797)
                        else Color(0xff619cf8)
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(
                    top = 20.dp, bottom = 20.dp,
//                    start = 12.dp, end = 12.dp // 註解掉讓每一格等寬
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.mipmap.golden_coin), contentDescription = "")
            Text(
                text = buildAnnotatedString {
                    append(
                        AnnotatedString(
                            text = "¥ $amount",
                            spanStyle = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    )
                    append(
                        AnnotatedString(
                            text = " ±5",
                            spanStyle = SpanStyle(
                                color = Color(0xff0091ff)
                            )
                        )
                    )
                },
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = buildAnnotatedString {
                    append("多贈")
                    append(
                        AnnotatedString(
                            text = " $discount% ",
                            spanStyle = SpanStyle(color = Color.Red)
                        )
                    )
                    append("優惠")
                },
                fontSize = 10.sp
            )
        }
    }
}

@Preview
@Composable
fun PayTimeChoose(remainTimes: Int = 0) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 14.dp, start = 28.dp, end = 28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "包时段方案", modifier = Modifier.padding(end = 4.dp),fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "适合观看者，自由看片不受限。", fontSize = 12.sp, color = Color(0xff6d7278))
            }
            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "您还有", fontSize = 12.sp)
                Text(text = "$remainTimes", color = Color.Red, fontSize = 18.sp)
                Text(text = "次购买包时段的特惠机会", fontSize = 12.sp)
            }
            if (remainTimes == 0) {
                Text(
                    text = "你今天直接购时的机会已经用完，要购买包时段，请先进行一次金币购买",
                    modifier = Modifier.padding(bottom = 28.dp)
                )
            }
            else {
                PayChooseReminder()
                Spacer(modifier = Modifier.height(16.dp))
                PayTimeChooseCell("体验", "24小时", 89.0)
                PayTimeChooseCell("经济", "7天", 149.0)
                PayTimeChooseCell("优惠", "30天", 399.0)
            }
        }
    }
}

@Preview
@Composable
fun PayTimeChooseCell(title: String = "体验", subtitle: String = "24小时", price: Double = 89.0) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .border(
                border = BorderStroke(2.dp, Color(0xff979797)),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.mipmap.time),
            contentDescription = "",
            modifier = Modifier.padding(start = 14.dp, top = 14.dp, bottom = 14.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    AnnotatedString(
                        title,
                        spanStyle = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                )
                append(" ")
                append(
                    AnnotatedString(
                        text = subtitle,
                        spanStyle = SpanStyle(
                            color = Color(0xff6d7278),
                            fontSize = 10.sp
                        )
                    )
                )
            },
            modifier = Modifier.weight(1f)
        )
        Text(
            text = buildAnnotatedString {
                append(
                    AnnotatedString(
                        text = "¥ $price",
                        spanStyle = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                )
                append(
                    AnnotatedString(
                        text = " ±5",
                        spanStyle = SpanStyle(
                            color = Color(0xff0091ff)
                        )
                    )
                )
            },
            modifier = Modifier.padding(end = 14.dp)
        )
    }
}

@Preview
@Composable
fun PayChooseScreen(navController: NavHostController = NavHostController(LocalContext.current)) {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color(0xfff1f1f1))
            .verticalScroll(scroll)
    ) {
        MyDataToolbar(navController = navController, title = "充值")
        Spacer(modifier = Modifier.padding(8.dp))
        PayPriceChoose()
        Spacer(modifier = Modifier.padding(8.dp))
        PayTimeChoose()
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.payChoose(navController: NavHostController) {
    composable(Screen.PAY_CHOOSE_SCREEN.route) {
        PayChooseScreen(navController = navController)
    }
}

fun NavHostController.navigateToPayChoose() {
    navigate(Screen.PAY_CHOOSE_SCREEN.route) {
        launchSingleTop = true
    }
}