package com.androidinsights.set.ui.catslist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.androidinsights.set.Cat
import com.androidinsights.set.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatsList(
    modifier: Modifier,
    onCatClicked: (Cat) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val cats = rememberCatsList()

    Box(
        modifier = modifier,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = cats,
                key = { cat -> cat.iconRes },
                contentType = { cat -> cat::class }
            ) { item ->
                Cat(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onCatClicked(item)
                        }
                        .padding(
                            horizontal = 8.dp
                        ),
                    cat = item,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
        }

        ExpandableFAB(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Cat(
    modifier: Modifier,
    cat: Cat,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    with(sharedTransitionScope) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(128.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = cat.iconRes.toString()),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                painter = painterResource(cat.iconRes),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Text(
                modifier = Modifier,
                text = stringResource(cat.textRes),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun rememberCatsList(): List<Cat> {
    return remember {
        listOf(
            Cat(
                iconRes = R.drawable.cat_1,
                textRes = R.string.lorem_ipsum,
            ),
            Cat(
                iconRes = R.drawable.cat_2,
                textRes = R.string.lorem_ipsum,
            ),
            Cat(
                iconRes = R.drawable.cat_3,
                textRes = R.string.lorem_ipsum,
            ),
            Cat(
                iconRes = R.drawable.cat_4,
                textRes = R.string.lorem_ipsum,
            ),
        )
    }
}