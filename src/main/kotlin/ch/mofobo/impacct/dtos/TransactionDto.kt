package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.enums.Period
import ch.mofobo.impacct.enums.TransactionType

data class TransactionDto(
        var type: TransactionType,
        var categoryId: Int,
        var title: String,
        var description: String,
        var amount: Int,
        var period: Period,
        var date: String
)