package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.enums.TransactionType
import java.time.YearMonth

data class TransactionDto(
        var type: TransactionType,
        var categoryId: Int,
        var title: String,
        var description: String,
        var amount: Int,
        var date: YearMonth
)