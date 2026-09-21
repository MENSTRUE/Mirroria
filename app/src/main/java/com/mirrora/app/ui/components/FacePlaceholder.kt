package com.mirrora.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mirrora.app.ui.theme.MirroraMutedBackground

/**
 * Neutral anonymous person silhouette placeholder.
 * Used consistently for: recent analysis thumbnails, history thumbnails,
 * result face preview, and the profile avatar (see design spec section 11).
 *
 * If [imageUri] is provided, the real captured/picked photo is shown instead.
 */
@Composable
fun FacePlaceholder(
    imageUri: String? = null,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(MirroraMutedBackground),
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Foto wajah",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Placeholder wajah",
                tint = Color(0xFF828C9B),
                modifier = Modifier.fillMaxSize(0.62f)
            )
        }
    }
}
