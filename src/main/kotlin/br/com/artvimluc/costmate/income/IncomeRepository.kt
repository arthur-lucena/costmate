package br.com.artvimluc.costmate.income

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IncomeRepository : JpaRepository<Income, Long> {
    fun findByPlanMonthId(planMonthId: Long?): List<Income?>?
}