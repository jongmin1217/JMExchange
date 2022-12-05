package com.bellminp.jmexchange.mapper

import com.bellminp.common.timberMsg
import com.bellminp.domain.model.DomainExchange
import com.bellminp.jmexchange.model.ExchangeData
import com.bellminp.jmexchange.utils.convertTimestampToDateTerm
import com.bellminp.jmexchange.utils.roundDownDigit
import java.text.DecimalFormat
import kotlin.math.floor

fun DomainExchange.mapToPresentation(
    type: Int,
    remittanceAmount: Double,
    unit : String
): ExchangeData {

    val price = when (type) {
        0 -> this.krw?: 0.00
        1 -> this.jpy?: 0.00
        else -> this.php?: 0.00
    }

    val exchange = when (type) {
        0 -> roundDownDigit(price,2)
        1 -> roundDownDigit(price,2)
        else -> roundDownDigit(price,2)
    }

    val recipientAmount = when (type) {
        0 -> roundDownDigit((price*remittanceAmount),2)
        1 -> roundDownDigit((price*remittanceAmount),2)
        else -> roundDownDigit((price*remittanceAmount),2)
    }

    val dec = DecimalFormat("#,##0.00")

    return ExchangeData(
        convertTimestampToDateTerm((this.timestamp?:0L)*1000),
        String.format("%s %s",dec.format(exchange),unit),
        String.format("%s %s",dec.format(recipientAmount),unit)
    )

}