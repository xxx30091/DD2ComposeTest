package com.example.dd2composetest.ui.compose.mine.edituser

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import com.example.dd2composetest.R
import com.example.dd2composetest.data.bean.UserData
import com.example.dd2composetest.enum.BottomSheet
import com.example.dd2composetest.enum.Screen
import com.example.dd2composetest.ui.compose.components.AddTagSheet
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.navigation.material.bottomSheet

/**
 *  Date: 2023.02.07
 */

val userData = UserData(
    imageUrl = "https://i1.jueshifan.com/2f5221d51dedbc4f9f/205a28/7c077a81/204328d017a8fa4d8c26.png",
    nickName = "叫我大哥",
    gender = 1,
    sexOrientation = 3,
    email = "17@ax2tw.net",
    tags = arrayListOf("可愛", "帥氣", "運動", "陽光", "結實")
)

@ExperimentalFoundationApi
fun NavGraphBuilder.editUser(navController: NavController) {
    composable(Screen.EDIT_USER_SCREEN.route) {
        EditUserScreen(navController = navController)
    }
    bottomSheet(BottomSheet.ADD_USER_TAG.route) {
        val parent = remember(it) {
            navController.getBackStackEntry(Screen.EDIT_USER_SCREEN.route)
        }
        val viewModel: EditUserViewModel = hiltViewModel(parent)

        AddTagSheet(
            navController = navController,
            onClick = { tag -> viewModel.onEditUserEvent(EditUserEvent.AddUserTag(tag)) }
        )
    }
}

fun NavHostController.navigateToEditUser() {
    navigate(Screen.EDIT_USER_SCREEN.route) {
        launchSingleTop = true
    }
}

@ExperimentalFoundationApi
@Composable
fun EditUserScreen(navController: NavController) {
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .verticalScroll(scroll)
        ) {
            EditUserHeader(navController)
            EditUserInfo(navController)
        }
        Text(
            text = "保存",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 18.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(4.dp))
                .clickable {

                }
                .background(
                    color = Color(0XFFECECEC),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(top = 12.dp, bottom = 12.dp),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EditUserHeader(
    navController: NavController,
    viewModel: EditUserViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        LeaveEditDialog(setShowDialog = {showDialog.value = it}, navController)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFF362d8b))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFF362d8b))
        ) {
            Text(
                text = "個人資料",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { showDialog.value = true },
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.arrow_left),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        val painter = rememberAsyncImagePainter(model = viewModel.userData.imageUrl)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 18.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    border = BorderStroke(2.dp, Color(0XFFd8d8d8)),
                    shape = CircleShape
                )
                .clickable { },
            contentScale = ContentScale.Crop
        )
        Text(
            text = "選擇圖片",
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable { },
            color = Color.White,
            fontSize = 15.sp
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun EditUserInfo(navController: NavController, viewModel: EditUserViewModel = hiltViewModel()) {
//    val a by remember {
//        mutableListOf(viewModel.userData.tags)
//    }
    val tags = viewModel.userData.tags
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFF362d8b))
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        EditUserInfoItem(title = "郵箱", EditUserItemType.EMAIL.itemType, )
        EditUserInfoItem(title = "暱稱", EditUserItemType.NICKNAME.itemType, )
        EditUserInfoItem(title = "性別", EditUserItemType.GENDER.itemType, )
        EditUserInfoItem(title = "取向", EditUserItemType.ORIENTATION.itemType)
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 18.dp)
                .fillMaxWidth()
        ) {
            Text(text = "標籤", color = Color(0XFF696969), fontSize = 16.sp)
            FlowRow(
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .padding(start = 10.dp),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 5.dp
            ) {
                UserTagItem(1, "", navController = navController)
                for (i in 0 until tags.size) {
                    UserTagItem(0, tags[i], position = i, navController = navController)
                }
            }
        }

        SpanText()
    }
}

const val editUserInlineId = "editUserInlineId"
val inlineContent = mapOf(
    Pair(
        editUserInlineId,
        InlineTextContent(
            Placeholder(
                width = 15.sp,
                height = 15.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(painterResource(id = R.mipmap.arrow_right), "", tint = Color(0xff696969))
        }
    )
)

@Composable
fun EditUserInfoItem(
    title: String,
    itemType: Int,
//    onOrientationClick: () -> Unit = {},
    onModifyClick: () -> Unit = {},
    viewModel: EditUserViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        SelectOrientationDialog(setShowDialog = {showDialog.value = it})
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 18.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, color = Color(0XFF696969), fontSize = 16.sp)
            if (itemType == EditUserItemType.NICKNAME.itemType) {
                BasicTextField(
                    value = viewModel.userData.nickName,
                    onValueChange = { value ->
                        viewModel.onEditUserEvent(
                            EditUserEvent.ChangeNickname(value)
                        )
                    },
                    modifier = Modifier
                        .padding(start = 10.dp, end = 5.dp)
                        .weight(1f),
                    textStyle = TextStyle(fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    decorationBox = @Composable { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = viewModel.userData.nickName,
                            enabled = true,
                            innerTextField = innerTextField,
                            interactionSource = remember { MutableInteractionSource() },
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            placeholder = { Text(text = "上限中文10個字、英文20個字") },
                            contentPadding = PaddingValues(0.dp)
                        )
                    }
                )
            } else {
                Text(
                    text = when (itemType) {
                        EditUserItemType.EMAIL.itemType -> viewModel.userData.email
                        EditUserItemType.GENDER.itemType -> getGender(viewModel.userData.gender)
                        EditUserItemType.ORIENTATION.itemType -> getOrientation(viewModel.userData.sexOrientation)
                        else -> ""
                    },
                    modifier = Modifier
                        .padding(start = 10.dp, end = 5.dp)
                        .weight(1f)
                        .clickable(
                            enabled = itemType == EditUserItemType.ORIENTATION.itemType
                        ) {
                            showDialog.value = true
                        },
                    color = Color(0XFF222226),
                    fontSize = 16.sp
                )
            }
            if (itemType == EditUserItemType.EMAIL.itemType) {
                Text(
                    text = buildAnnotatedString {
                        append("修改")
                        appendInlineContent(editUserInlineId)
                    },
                    modifier = Modifier
                        .clickable {
                            onModifyClick()
                        },
                    color = Color(0XFF696969),
                    fontSize = 15.sp,
                    inlineContent = inlineContent
                )
            }
        }
        Divider(modifier = Modifier
            .height(0.5.dp)
            .fillMaxWidth()
            .background(Color.Black)
        )
    }
}

fun getGender(type: Int): String {
    return when (type) {
        1 -> "男"
        2 -> "女"
        else -> "男"
    }
}

fun getOrientation(type: Int): String {
    return when (type) {
        1 -> "同性"
        2 -> "異性"
        3 -> "通殺"
        else -> "同性"
    }
}

@Composable
fun SelectOrientationDialog(
    setShowDialog: (Boolean) -> Unit,
    viewModel: EditUserViewModel = hiltViewModel()
) {
    val radioSelected = remember {
        mutableStateOf(0)
    }
    AlertDialog(
        onDismissRequest = {
            setShowDialog(false)
        },
        title = {
            Text(text = "取向", fontSize = 20.sp)
        },
        text = {
            Column() {
                CustomRadioGroup(
                    selection = radioSelected.value,
                    enabled = true,
                ) { type ->
                    radioSelected.value = type
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "取消",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 0.dp)
                            .clickable { setShowDialog(false) }
                            .padding(end = 8.dp),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "確認",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                            .clickable {
                                viewModel.onEditUserEvent(
                                    EditUserEvent.ChangeSexOrientation(radioSelected.value)
                                )
                                setShowDialog(false)
                            }
                            .padding(end = 0.dp),
                        fontSize = 14.sp
                    )
                }
            }
        },
        buttons = {}
    )
}

@Composable
fun LeaveEditDialog(
    setShowDialog: (Boolean) -> Unit,
    navController: NavController
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(
            dismissOnClickOutside = false
        ),
        content = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "資料尚未保存，是否確定放棄變更？",
                    color = Color(0XFF696969),
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "取消",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .clickable { setShowDialog(false) },
                        fontSize = 14.sp
                    )
                    Text(
                        text = "確認",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 0.dp)
                            .clickable {
                                setShowDialog(false)
                                navController.popBackStack()
                            },
                        fontSize = 14.sp
                    )
                }
            }
        }
    )
}

@Composable
fun CustomRadioButton(
    isSelected: Boolean,
    title: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onClick() },
            modifier = Modifier.size(18.dp),
            enabled = enabled,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.DarkGray,
                unselectedColor = Color.DarkGray,
                disabledColor = Color.LightGray
            )
        )
        Text(
            text = title, 
            modifier = Modifier.padding(start = 8.dp),
            color = if (enabled) Color.DarkGray else Color.LightGray
        )
    }
}

@Composable
fun CustomRadioGroup(selection: Int, enabled: Boolean, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomRadioButton(isSelected = selection == 1, title = "同性", enabled = enabled) {
            onClick(1)
        }
        CustomRadioButton(isSelected = selection == 2, title = "異性", enabled = enabled) {
            onClick(2)
        }
        CustomRadioButton(isSelected = selection == 3, title = "通殺", enabled = enabled) {
            onClick(3)
        }
    }
}

@Composable
fun UserTagItem(
    viewType: Int = 1,
    tag: String = "123",
    position: Int = 0,
    navController: NavController,
    viewModel: EditUserViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier
            .height(28.dp)
            .background(
                color = if (viewType == 1) Color(0XFFF6F6F6) else Color(0XFFfff2e5),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                if (viewType == 1) navController.navigate(BottomSheet.ADD_USER_TAG.route)
                else viewModel.onEditUserEvent(
                    EditUserEvent.RemoveUserTag(position)
                )
            }
            .padding(start = 14.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (viewType == 1) Icon(
            painter = painterResource(id = R.mipmap.add),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(16.dp),
            tint = Color(0XFF696969)
        )
        Text(
            text = if (viewType == 1) "標籤" else tag,
            modifier = Modifier,
            color = if (viewType == 1) Color(0XFF696969) else Color(0XFFff9d3e),
            textAlign = TextAlign.Center
        )
        if (viewType != 1) Icon(
            painter = painterResource(id = R.drawable.user_detail_close),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(12.dp),
            tint = Color(0XFFff9d3e)
        )
    }
}

enum class EditUserItemType(val itemType: Int) {
    EMAIL(1),
    NICKNAME(2),
    GENDER(3),
    ORIENTATION(4),
}

@Preview
@Composable
fun PreviewEditUserHeader() {
    EditUserHeader(NavController(LocalContext.current))
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewEditUserInfo() {
    EditUserInfo(NavController(LocalContext.current))
}

@Preview
@Composable
fun PreviewLeaveEditDialog() {
    LeaveEditDialog({}, NavController(LocalContext.current))
}

@Preview
@Composable
fun PreviewSelectOrientationDialog() {
    SelectOrientationDialog({})
}

fun selectImg() {

}


@Composable
fun SpanText() {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        val annotatedString = buildAnnotatedString {
            append("By using ClickableText")

            pushStringAnnotation(tag = "first_clickable", annotation = "click the first clickable text ")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(" first clickable")
            }
            pop()

            append(" and ")

            pushStringAnnotation(tag = "second_clickable", annotation = "click the second clickable text")

            withStyle(style = SpanStyle(
                color = Color.Black,
                textDecoration = TextDecoration.Underline
            )) {
                append("second clickable")
            }

            pop()
        }

        ClickableText(text = annotatedString, style = MaterialTheme.typography.body1, onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "first_clickable", start = offset, end = offset).firstOrNull()?.let {
                Log.d("test_clickable", it.item)
            }

            annotatedString.getStringAnnotations(tag = "second_clickable", start = offset, end = offset).firstOrNull()?.let {
                // What you want to do
                Log.d("test_clickable", it.item)
            }
        })
    }

//        val annotatedString = buildAnnotatedString {
//            append("By joining, you agree to the ")
//
//            pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
////            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
////                append("privacy policy")
////            }
//            withStyle(style = SpanStyle(color = Color.Red)) {
//                append("privacy policy")
//            }
//            pop()
//
//            append(" and ")
//
//            pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")
//
//            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
//                append("terms of use")
//            }
//
//            pop()
//        }
//
//        ClickableText(text = annotatedString, style = MaterialTheme.typography.body1, onClick = { offset ->
//            annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset).firstOrNull()?.let {
//                Log.d("policy URL", it.item)
//            }
//
//            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset).firstOrNull()?.let {
//                Log.d("terms URL", it.item)
//            }
//        })
//    }



//    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
//
//        val str = "Click this link to go to web site"
//        val startIndex = str.indexOf("link")
//        val endIndex = startIndex + 4
//        append(str)
//        addStyle(
//            style = SpanStyle(
//                color = Color(0xff64B5F6),
//                fontSize = 18.sp,
//                textDecoration = TextDecoration.Underline
//            ), start = startIndex, end = endIndex
//        )
//
//        // attach a string annotation that stores a URL to the text "link"
//        addStringAnnotation(
//            tag = "URL",
//            annotation = "https://github.com",
//            start = startIndex,
//            end = endIndex
//        )
//
//    }
//
//// UriHandler parse and opens URI inside AnnotatedString Item in Browse
//    val uriHandler = LocalUriHandler.current
//
//// 🔥 Clickable text returns position of text that is clicked in onClick callback
//    ClickableText(
//        modifier = modifier
//            .padding(16.dp)
//            .fillMaxWidth(),
//        text = annotatedLinkString,
//        onClick = {
//            annotatedLinkString
//                .getStringAnnotations("URL", it, it)
//                .firstOrNull()?.let { stringAnnotation ->
//                    uriHandler.openUri(stringAnnotation.item)
//                }
//        }
//    )
}


//fun watermark(uri: Uri, context: Context?): Bitmap? {
//    val i = context?.contentResolver?.openInputStream(uri)
//    var o = BitmapFactory.Options()
//    o.inJustDecodeBounds = true
//    BitmapFactory.decodeStream(i, null, o)
//    val max = 128
//    var w1 = o.outWidth
//    var h1 = o.outHeight
//    var scale = 1
//    while (w1 > max && h1 > max) {
//        w1 /= 2
//        h1 /= 2
//        scale++
//    }
//    Log.d("AppDebug", "b $scale")
//    o = BitmapFactory.Options()
//    o.inSampleSize = scale
//    val b = BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(uri), null, o)
//
//    return if (b != null) {
//        val r = Bitmap.createBitmap(b.width, b.height, b.config)
//        val c = Canvas(r)
//        c.drawBitmap(b, 0f, 0f, null)
//
//        val s = b.width.coerceAtMost(b.height)
//        var w = BitmapFactory.decodeResource(resources, R.drawable.watermark)
//        w = Bitmap.createScaledBitmap(w, s, s, false)
//
//        c.drawBitmap(w, (b.width - s) / 2f, (b.height - s) / 2f, null)
//
//        r
//    } else null
//}