package com.youssefmsaber.caffeine.screen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@Composable
fun TopApp(
    modifier: Modifier = Modifier,
    leading: @Composable (() -> Unit)? = null,
    label: String = "",
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leading != null)
                leading()
            BasicText(
                text = label,
                style = TextStyle(
                    color = Color(0xDE1F1F1F),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    letterSpacing = 0.25.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
        if (trailing != null)
            trailing()
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppPreview() {
    TopApp(
        leading = {Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.pfp),
            contentDescription = "Profile Image",
        )},
        label = "Macchiato",
        trailing = {Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {}
                .size(48.dp)
                .background(Gray)
                .padding(12.dp),
            imageVector = ImageVector.vectorResource(R.drawable.add_01),
            contentDescription = ""
        )}
    )
}