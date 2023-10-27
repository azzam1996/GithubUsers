package com.azzam.githubusers.ui.users.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.SubcomposeAsyncImage
import com.azzam.githubusers.ui.theme.Gray43
import com.azzam.githubusers.ui.theme.Gray99

@Composable
fun Avatar(
    url: String?,
    size: Dp,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (bg, iv) = createRefs()

        ConstraintLayout(
            modifier = Modifier
                .clip(CircleShape)
                .alpha(0.2f)
                .border(BorderStroke(width = 2.dp, color = Gray99), shape = CircleShape)
                .size(size.plus(4.dp))
                .background(color = Gray43, shape = CircleShape)
                .constrainAs(bg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {}

        SubcomposeAsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {
                Box {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)

                    )
                }
            },
            modifier = Modifier
                .clip(CircleShape)
                .size(size)
                .constrainAs(iv) {
                    top.linkTo(bg.top)
                    bottom.linkTo(bg.bottom)
                    start.linkTo(bg.start)
                    end.linkTo(bg.end)
                }
        )
    }
}