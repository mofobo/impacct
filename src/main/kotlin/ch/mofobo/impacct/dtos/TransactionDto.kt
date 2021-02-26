package ch.mofobo.impacct.dtos

import ch.mofobo.impacct.entities.Category
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.enums.TransactionType
import java.time.YearMonth

class TransactionDto {

    var id: Int? = null
    var type: TransactionType? = null
    var categoryId: Int? = null
    var title: String? = null
    var description: String? = null
    var amount: Int? = null
    var date: YearMonth? = null

    constructor()

    constructor(id: Int? = null, type: TransactionType? = null, categoryId: Int? = null, title: String? = null, description: String? = null, amount: Int? = null, date: YearMonth? = null) {
        this.id = id
        this.type = type
        this.categoryId = categoryId
        this.title = title
        this.description = description
        this.amount = amount
        this.date = date
    }

    constructor(entity: Transaction) {
        this.id = entity.id
        this.type = TransactionType.valueOf(entity.type)
        this.categoryId = entity.category.id
        this.title = entity.title
        this.description = entity.description
        this.amount = entity.amount
        this.date = entity.date
    }
}