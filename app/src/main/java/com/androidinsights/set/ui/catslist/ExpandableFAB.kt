package com.androidinsights.set.ui.catslist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ExpandableFAB(
    modifier: Modifier,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val columnModifier = Modifier
        .clip(
            RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
            )
        )
        .fillMaxWidth()
        .background(LocalContentColor.current)
        .padding(16.dp)

    SharedTransitionLayout(
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = isExpanded,
            label = "expandable_fab"
        ) { targetState ->
            Box {
                if (targetState) {
                    ExpandedContent(
                        modifier = columnModifier,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                        onClick = {
                            isExpanded = false
                        },
                    )
                } else {
                    FAB(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                        onClick = {
                            isExpanded = true
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ExpandedContent(
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        Column(
            modifier = modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(
                    key = "fab",
                ),
                animatedVisibilityScope = animatedVisibilityScope,
                enter = fadeIn(
                    animationSpec = tween(2000)
                ) + scaleInSharedContentToBounds(
                    alignment = Alignment.BottomEnd,
                ),
                boundsTransform = { initialBounds, targetBounds ->
                    tween(2000)
                }
            ),
        ) {
            repeat(3) { index ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClick,
                ) {
                    Text(
                        text = "button ${index + 1}"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun FAB(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd,
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(
                            key = "fab",
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                    .padding(16.dp),
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                )
            }
        }
    }
}