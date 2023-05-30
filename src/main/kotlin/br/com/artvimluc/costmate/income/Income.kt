package br.com.artvimluc.costmate.income

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "INCOME")
class Income (
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "PLAN_MONTH_ID")
    var planMonthId: Long? = null,

    @Column(name = "DESCRIPTION")
    var description: String? = null,

    @Column(name = "INCOME_VALUE")
    var value: BigDecimal? = null
)