package com.androidinsights.set

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.androidinsights.set.ui.catdetails.CatDetails
import com.androidinsights.set.ui.catslist.CatsList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatsContent(
    modifier: Modifier
) {
    var showCatDetails by remember { mutableStateOf(false) }

    var selectedCat: Cat? by remember { mutableStateOf(null) }

    SharedTransitionLayout {
        AnimatedContent(
            targetState = showCatDetails
        ) { targetState ->
            if (targetState) {
                BackHandler { showCatDetails = false }

                CatDetails(
                    modifier = modifier.fillMaxSize(),
                    cat = selectedCat!!,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                )
            } else {
                CatsList(
                    modifier = modifier.fillMaxSize(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onCatClicked = { cat ->
                        selectedCat = cat
                        showCatDetails = true
                    }
                )
            }
        }
    }
}
