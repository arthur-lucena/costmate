package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.util.MessageLocator
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.domain.PlanMonth
import br.com.artvimluc.costmate.repository.ExpenseRepository
import br.com.artvimluc.costmate.domain.Income
import br.com.artvimluc.costmate.dto.PlanMonthDTO
import br.com.artvimluc.costmate.exception.ExistsException
import br.com.artvimluc.costmate.repository.IncomeRepository
import br.com.artvimluc.costmate.repository.PlanMonthRepository
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

    fun create(planMonth: PlanMonth): PlanMonth {
        if (exists(planMonth)) throw ExistsException(messageLocator.getMessage("already.exists"))

        return planMonthRepository.save(planMonth)
    }

    fun exists(planMonth: PlanMonth): Boolean {
        return planMonthRepository.existsByReferenceYearAndReferenceMonth(planMonth.referenceYear, planMonth.referenceMonth)
    }

    fun find(referenceYear: Int?, referenceMonth: Int?) : PlanMonthDTO {
        val planMonth: PlanMonth? = planMonthRepository.findByReferenceYearAndReferenceMonth(referenceYear, referenceMonth)

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
