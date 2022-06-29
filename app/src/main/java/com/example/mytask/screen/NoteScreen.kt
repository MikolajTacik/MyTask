package com.example.mytask.screen

import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytask.R


@Composable
fun TaskScreen() {
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                    Text(text = stringResource(id = R.string.app_name), color = Color.White, fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold)
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            }, backgroundColor = Color(0xFF184e77))
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}