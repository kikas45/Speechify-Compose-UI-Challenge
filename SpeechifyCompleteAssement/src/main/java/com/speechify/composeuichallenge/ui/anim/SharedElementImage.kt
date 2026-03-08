package com.speechify.composeuichallenge.ui.anim

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale

@Composable
fun SharedElementImage(
    targetKey: String,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {

    var visible by remember{ mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue =  if (visible) 1f else 0.3f)
    val alpha by animateFloatAsState(targetValue =  if(visible) 1f else 0f)

    LaunchedEffect(targetKey) {
        visible = true
    }

    Box(
        modifier = Modifier
            .scale(scale)
            .alpha(alpha),
        content = content
    )

}