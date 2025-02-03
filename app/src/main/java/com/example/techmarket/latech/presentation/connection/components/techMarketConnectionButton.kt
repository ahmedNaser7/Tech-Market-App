package com.example.techmarket.latech.presentation.connection.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.R
import com.example.techmarket.ui.theme.onPrimaryContainerLight

@Composable
fun ConnectionButton(
    type: ButtonType,
    modifier: Modifier,
    textResId: Int,
    contentColor: Color,
    containerColor: Color = Color.White,
    onClick: () -> Unit = {}
    ){
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = Color.Black,
            disabledContentColor = onPrimaryContainerLight
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        when(type){
            ButtonType.EMAIL -> EmailButtonContent(textResId = textResId, color = contentColor)
            ButtonType.GOOGLE -> GoogleButtonContent(textResId = textResId, color = contentColor)
            ButtonType.FACEBOOK -> FacebookButtonContent(textResId = textResId, color = contentColor)
        }
    }

}

@Composable
fun EmailButtonContent(textResId: Int,color: Color) {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = stringResource(textResId),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color,
    )
}

@Composable
fun GoogleButtonContent(textResId: Int,color: Color) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.google_icon),
        contentDescription = "Google Content",
    )

    Spacer(modifier = Modifier.width(20.dp))

    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = stringResource(textResId),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color,
    )
}

@Composable
fun FacebookButtonContent(textResId: Int,color: Color) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.facebook_icon),
        contentDescription = "Google Content",
    )

    Spacer(modifier = Modifier.width(30.dp))

    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = stringResource(textResId),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = color,
    )
}

enum class ButtonType{
    EMAIL,
    GOOGLE,
    FACEBOOK
}