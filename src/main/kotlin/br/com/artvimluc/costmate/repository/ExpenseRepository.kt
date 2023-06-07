package br.com.artvimluc.costmate.repository

import br.com.artvimluc.costmate.domain.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long> {
    fun findByPlanMonthId(planMonthId: Long?): List<Expense?>?

}