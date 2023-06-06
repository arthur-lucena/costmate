package br.com.artvimluc.costmate.expense

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "EXPENSE")
class Expense (
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "CREDIT_CARD_ID")
    var creditCardId: Long? = null,

    @Column(name = "PLAN_MONTH_ID")
    var planMonthId: Long? = null,

    @Column(name = "DESCRIPTION")
    var description: String? = null,

    @Column(name = "EXPENSE_VALUE")
    var value: BigDecimal? = null,

    @Column(name = "REFUND")
    var refund: Boolean? = null,

    @Column(name = "INSTALLMENT")
    var installment: Int? = null,

    @Column(name = "TOTAL_INSTALLMENTS")
    var totalInstallments: Int? = null,

    @Column(name = "RECEIPT_FILE")
    var receiptFile: String? = null,

    @Column(name = "RECEIPT_FILE_UPLOADED")
    var receiptFileUploaded: Boolean? = null,

    @Column(name = "DATE_CREATE")
    var dateCreate: LocalDateTime? = null,

    @Column(name = "DATE_UPDATE")
    var dateUpdate: LocalDateTime? = null
)