package com.plcoding.cryptotracker.crypto.presentation.coin_detail

import android.icu.number.NumberFormatter
import io.ktor.client.content.LocalFileContent
import java.text.NumberFormat
import java.util.Locale

data class ValueLable(
    val value:Float,
    val unit:String,
)
{
    fun formatted():String
    {
        val formatter = NumberFormat.getInstance(Locale.getDefault()).apply {
            val fractionDigits = when
            {
                value > 1000 -> 0
                value in 2f..999f -> 2
                else -> 3
            }
            maximumFractionDigits = fractionDigits
            minimumFractionDigits = 0
        }
        return "${formatter.format(value)}$unit"
    }
}