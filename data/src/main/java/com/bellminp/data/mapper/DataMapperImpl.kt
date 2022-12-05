package com.bellminp.data.mapper

import com.bellminp.data.model.DataExchange
import com.bellminp.domain.model.DomainExchange

fun DataExchange.dataToDomain() : DomainExchange{
    return DomainExchange(
        this.success,
        this.terms,
        this.privacy,
        this.timestamp,
        this.source,
        this.krw,
        this.jpy,
        this.php,
        this.errorCode,
        this.errorInfo
    )
}

fun DomainExchange.domainToData() : DataExchange{
    return DataExchange(
        this.success,
        this.terms,
        this.privacy,
        this.timestamp,
        this.source,
        this.krw,
        this.jpy,
        this.php,
        this.errorCode,
        this.errorInfo
    )
}