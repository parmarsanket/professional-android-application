package com.plcoding.cryptotracker.crypto.presentation.model

import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.core.presenttation.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id:String,
    val rank:Int,
    val name:String,
    val symbol:String,
    val marketCapUsd:DisplayableNumber,
    val priceUsd:DisplayableNumber,
    val changePrecent24Hr:DisplayableNumber,
    @DrawableRes
     val iconRes:Int


)
data class DisplayableNumber(
    val value: Double,
    val formtted:String
)

fun Coin.toCoinUi():CoinUi{
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        marketCapUsd = marketCapUsd.toDisplaybleNumber(),
        priceUsd = priceUsd.toDisplaybleNumber(),
        changePrecent24Hr = chnagePrecent24Hr.toDisplaybleNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplaybleNumber():DisplayableNumber
{
    val formtted =NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits =2
        maximumFractionDigits =2
    }
    return DisplayableNumber(
        value = this,
        formtted = formtted.format(this)
    )

}