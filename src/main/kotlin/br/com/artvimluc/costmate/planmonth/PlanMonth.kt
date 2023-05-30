package br.com.artvimluc.costmate.planmonth

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "PLAN_MONTH")
class PlanMonth (

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "REFERENCE_MONTH")
    var referenceMonth: Int? = null,

    @Column(name = "REFERENCE_YEAR")
    var referenceYear: Int? = null,

    @Column(name = "TOTAL_EXPENSES")
    var totalExpenses: BigDecimal? = null,

    @Column(name = "TOTAL_INCOME")
    var totalIncome: BigDecimal? = null,

    @Column(name = "GOAL")
    var goal: BigDecimal? = null,

    @Column(name = "SURPLUS")
    var surplus: BigDecimal? = null

)