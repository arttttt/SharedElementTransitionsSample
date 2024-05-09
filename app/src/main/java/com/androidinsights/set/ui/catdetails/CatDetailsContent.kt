package com.androidinsights.set.ui.catdetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.androidinsights.set.Cat

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatDetails(
    modifier: Modifier,
    cat: Cat,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    with(sharedTransitionScope) {
        Column(
            modifier = modifier.verticalScroll(
                state = rememberScrollState(),
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                modifier = Modifier.sharedElement(
                    state = rememberSharedContentState(key = cat.iconRes.toString()),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
                painter = painterResource(cat.iconRes),
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                    ),
                text = stringResource(cat.textRes)
            )
        }
    }
}
