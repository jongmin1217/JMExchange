package com.bellminp.remote.mapper

import com.bellminp.data.model.DataExchange
import com.bellminp.remote.model.ExchangeResponse

fun ExchangeResponse.exchangeToData() : DataExchange{
    return DataExchange(
        this.success,
        this.terms,
        this.privacy,
        this.timestamp,
        this.source,
        this.quotes?.usdKrw,
        this.quotes?.usdJpy,
        this.quotes?.usdPhp,
        this.error?.code,
        this.error?.info
    )
}