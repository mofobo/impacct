package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.enums.Period

data class TransactionCreationDto(
        var subcategory: Long,
        var title: String,
        var description: String,
        var amount: Long,
        var period: Period,
        var date: String
)