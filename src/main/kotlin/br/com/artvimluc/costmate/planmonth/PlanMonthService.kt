package br.com.artvimluc.costmate.planmonth

import br.com.artvimluc.costmate.config.MessageLocator
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.expense.Expense
import br.com.artvimluc.costmate.expense.ExpenseRepository
import br.com.artvimluc.costmate.income.Income
import br.com.artvimluc.costmate.income.IncomeRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanMonthService

    @Autowired
    constructor (
        private val planMonthRepository: PlanMonthRepository,
        private val expenseRepository: ExpenseRepository,
        private val incomeRepository: IncomeRepository,
        private val messageLocator: MessageLocator,
    ) {

    fun create(planMonth: PlanMonth): PlanMonthDTO {
        planMonthRepository.save(planMonth)

        return find(2023, 12)
    }

    fun find(year: Int?, month: Int?) : PlanMonthDTO {
        val planMonth: PlanMonth? = planMonthRepository.findByReferenceYearAndReferenceMonth(year, month)

        return planMonthDTO(planMonth?: throw NotFoundException(messageLocator.getMessage("not.found")))
    }

    private fun planMonthDTO(planMonth: PlanMonth): PlanMonthDTO {
        val mapper = ModelMapper()
        val dto: PlanMonthDTO = mapper.map(planMonth, PlanMonthDTO::class.java)
        val expenses: List<Expense?>? = expenseRepository.findByPlanMonthId(planMonth.id)
        val incomes: List<Income?>? = incomeRepository.findByPlanMonthId(planMonth.id)
        dto.listExpense = expenses
        dto.listIncome = incomes
        return dto
    }
}
