package com.mirrora.app.data.model

enum class FacialAreaType(val label: String) {
    MATA("Mata"),
    ALIS("Alis"),
    HIDUNG("Hidung"),
    MULUT("Mulut"),
    RAHANG("Rahang")
}

data class FacialAreaScore(
    val type: FacialAreaType,
    val percent: Int
)
