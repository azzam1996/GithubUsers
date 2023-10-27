package com.azzam.githubusers.ui.users.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.azzam.githubusers.R
import com.azzam.githubusers.ui.theme.Valentino

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchUsers: (String) -> Unit,
    searchKeyword: String,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        val (ivSearch, etSearch) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search icon",
            colorFilter = ColorFilter.tint(color = Valentino),
            modifier = Modifier
                .size(30.dp, 30.dp)
                .constrainAs(ivSearch) {
                    top.linkTo(parent.top, 5.dp)
                    bottom.linkTo(parent.bottom, 5.dp)
                    start.linkTo(parent.start, 10.dp)
                }
        )

        TextField(
            value = searchKeyword,
            onValueChange = searchUsers,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Search ...")
            },
            modifier = Modifier
                .background(Color.White)
                .constrainAs(etSearch) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(ivSearch.end, 5.dp)
                    end.linkTo(parent.end, 10.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}