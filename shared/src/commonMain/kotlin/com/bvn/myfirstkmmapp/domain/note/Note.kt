package com.bvn.myfirstkmmapp.domain.note

import com.bvn.myfirstkmmapp.presentation.BabyBlueHex
import com.bvn.myfirstkmmapp.presentation.LightGreenHex
import com.bvn.myfirstkmmapp.presentation.RedOrangeHex
import com.bvn.myfirstkmmapp.presentation.RedPinkHex
import com.bvn.myfirstkmmapp.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val  colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)
        fun generateRandomColor() = colors.random()
    }
}