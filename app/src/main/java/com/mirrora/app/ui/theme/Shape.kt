package com.mirrora.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val MirroraShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(MirroraRadius.small),
    medium = RoundedCornerShape(MirroraRadius.standard),
    large = RoundedCornerShape(MirroraRadius.large),
    extraLarge = RoundedCornerShape(28.dp)
)