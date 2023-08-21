package com.example.dcpracticeproject

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

fun boldAndNormal(boldText: String, normalText: String): AnnotatedString = buildAnnotatedString {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(boldText)
    }
    withStyle(style = SpanStyle()) {
        append(normalText)
    }
}