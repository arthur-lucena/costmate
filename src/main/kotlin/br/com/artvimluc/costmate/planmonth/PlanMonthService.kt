package br.com.artvimluc.costmate.planmonth

import br.com.artvimluc.costmate.expense.Expense
import br.com.artvimluc.costmate.expense.ExpenseRepository
import br.com.artvimluc.costmate.income.Income
import br.com.artvimluc.costmate.income.IncomeRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PlanMonthService

    @Autowired
    constructor (
        private val planMonthRepository: PlanMonthRepository,
        private val expenseRepository: ExpenseRepository,
        private val incomeRepository: IncomeRepository
    ) {

    fun create(): PlanMonthDTO {
        val planMonth: PlanMonth = PlanMonth(
            referenceMonth = 12,
            referenceYear = 2023
        )

        planMonthRepository.save(planMonth)

        val expense: Expense = Expense(
            planMonthId = planMonth.id,
            description = "cartao nu",
            value = BigDecimal.valueOf(3580.89),
            installment = 1,
            totalInstallments = 1,
            refund = false,
            receiptFileUploaded = false)

        expenseRepository.save(expense)

        val income: Income = Income(
            planMonthId = planMonth.id,
            description = "salario",
            value = BigDecimal.valueOf(16500.00))

        incomeRepository.save(income)

        return find(2023, 12)
    }

    fun find(year: Int?, month: Int?) : PlanMonthDTO {
        val planMonth: PlanMonth = planMonthRepository.findByReferenceYearAndReferenceMonth(year, month)
        val mapper = ModelMapper()
        val dto: PlanMonthDTO = mapper.map(planMonth, PlanMonthDTO::class.java)
        val expenses: List<Expense?>? = expenseRepository.findByPlanMonthId(planMonth.id)
        val incomes: List<Income?>? = incomeRepository.findByPlanMonthId(planMonth.id)
        dto.listExpense = expenses
        dto.listIncome = incomes
        return dto
    }
}
