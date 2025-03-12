package com.plcoding.cryptotracker.core.presenttation.util

import android.content.Context
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.core.domain.util.NetworkError

fun NetworkError.toSting(context: Context) :String{
  val resId = when(this)
  {
      NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
      NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
      NetworkError.NO_INTERNET -> R.string.error_no_Internet
      NetworkError.SERVER_ERROR -> R.string.error_unknown
      NetworkError.SERIALIZATION -> R.string.error_serialization
      NetworkError.UNKNOWN -> R.string.error_unknown
  }
    return context.getString(resId)
}