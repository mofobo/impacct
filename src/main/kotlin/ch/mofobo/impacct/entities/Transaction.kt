package ch.mofobo.impacct.entities

import java.time.YearMonth
import javax.persistence.*

@Entity(name = "transactions")
class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "owner_email")
        var ownerEmail: String,

        @Column(name = "type", columnDefinition = "TransactionType")
        var type: String,

        @OneToOne
        @JoinColumn(name = "category_id")
        var category: Category,

        var title: String,

        var description: String? = null,

        var amount: Int,

        @Convert(converter = YearMonthDateAttributeConverter::class) var date: YearMonth
)