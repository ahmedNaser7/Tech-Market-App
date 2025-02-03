package com.example.techmarket.latech.presentation.auth.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.ui.theme.onPrimaryContainerLight

@Composable
fun TextEntryModule(
    description:String,
    hint: String,
    textValue: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    cursorColor: Color,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (()->Unit)?=null,
    onValueChange: (String) -> Unit,
){

 Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Start,
            fontSize = 25.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = modifier.height(60.dp).background(color = Color.White,shape = RoundedCornerShape(3.dp)).border(1.dp, Color.White, RoundedCornerShape(3.dp)),
            value = textValue,
            onValueChange = onValueChange,
            singleLine = true,
            shape = RoundedCornerShape(3.dp),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                color = Color.White, // Text color
                fontSize = 20.sp, // Text size
                fontWeight = FontWeight.Medium // Text weight
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = onPrimaryContainerLight,
                unfocusedContainerColor = onPrimaryContainerLight,
                disabledContainerColor = onPrimaryContainerLight,
                disabledTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = cursorColor
            ),
            trailingIcon = {
                if(trailingIcon != null){
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "trailingIcon",
                        tint = cursorColor,
                        modifier = Modifier.clickable {
                            if(onTrailingIconClick != null){
                                onTrailingIconClick()
                            }
                        }
                    )
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
            }
        )
    }

}


// old version

//var text by remember { mutableStateOf("") }
//        BasicTextField(
//            value = text,
//            onValueChange = { text = it },
//            textStyle = TextStyle(
//                textAlign = TextAlign.Start,
//                color = Color.White, // Text color
//                fontSize = 20.sp, // Text size
//                fontWeight = FontWeight.Medium // Text weight
//            ),
//            singleLine = true,
//            decorationBox = { innerTextField ->
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .border(1.dp, color = Color.White, RoundedCornerShape(4.dp))
//                        .align(Alignment.CenterHorizontally)
//                        .padding(vertical = 8.dp).padding(start = 10.dp),
//                ) {
//                    if (text.isEmpty()) {
//                        Text(
//                            text = name,
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                            color = Color.White,
//                        )
//                    }
//                    innerTextField()
//                }
//            }
//        )

//enum class TypeOfTextField {
//    FULLNAME, EMAIL, PASSWORD
//}