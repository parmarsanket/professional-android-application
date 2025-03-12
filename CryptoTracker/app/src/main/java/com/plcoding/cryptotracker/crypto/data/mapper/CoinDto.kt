package com.plcoding.cryptotracker.crypto.data.mapper

import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinDto
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin():Coin{
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        chnagePrecent24Hr = changePercent24Hr,
        priceUsd =priceUsd
    )
}

fun CoinPriceDto.toCoinPrice():CoinPrice
{
    return CoinPrice(
        priceUsd = priceUsd,
        dataTime = Instant.ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )
}