package com.bit.purple.project.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi // <-- ADD THIS IMPORT
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
// REMOVED: import androidx.compose.ui.text.font.Font.VariationSettings
import com.bit.purple.project.R

val JostVariable = FontFamily(
    Font(
        resId = R.font.jost_variablefont_wght,
        weight = FontWeight.Normal

    )
)