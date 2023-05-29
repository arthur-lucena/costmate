package br.com.artvimluc.costmate.expense

import org.springframework.data.jpa.repository.JpaRepository

interface ExpenseRepository : JpaRepository<Expense, Long> {
    fun findByPlanMonthId(planMonthId: Long?): List<Expense?>?

}