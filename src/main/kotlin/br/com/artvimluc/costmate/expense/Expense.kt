package br.com.artvimluc.costmate.expense

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

class Expense (
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

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
    var receiptFileUploaded: Boolean? = null
)