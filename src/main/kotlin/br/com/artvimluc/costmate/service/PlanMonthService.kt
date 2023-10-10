package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.domain.Income
import br.com.artvimluc.costmate.domain.PlanMonth
import br.com.artvimluc.costmate.dto.PlanMonthDTO
import br.com.artvimluc.costmate.exception.AllReadyExistsException
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.repository.ExpenseRepository
import br.com.artvimluc.costmate.repository.IncomeRepository
import br.com.artvimluc.costmate.repository.PlanMonthRepository
import br.com.artvimluc.costmate.util.MessageLocator
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

private const val ALREADY_EXISTS = "plan.month.already.exists"

private const val NOT_FOUND = "plan.month.not.found"

@Service
class PlanMonthService

    @Autowired
    constructor (
        private val planMonthRepository: PlanMonthRepository,
        private val expenseRepository: ExpenseRepository,
        private val incomeRepository: IncomeRepository,
        private val messageLocator: MessageLocator,
    ) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun create(planMonth: PlanMonth): PlanMonth {
        if (exists(planMonth)) {
           throw AllReadyExistsException(String.format(messageLocator.getMessage(ALREADY_EXISTS), planMonth.referenceYear, planMonth.referenceMonth))
        }

        return planMonthRepository.save(planMonth)
    }

    fun update(planMonth: PlanMonth): PlanMonth {
        if (!exists(planMonth)) {
            throw NotFoundException(String.format(messageLocator.getMessage(NOT_FOUND), planMonth.referenceYear, planMonth.referenceMonth))
        }

        return planMonthRepository.save(planMonth)
    }

    fun delete(referenceYear: Int?, referenceMonth: Int?) : Boolean {
        return planMonthRepository.deleteByReferenceYearAndReferenceMonth(referenceYear, referenceMonth)
    }

    fun exists(planMonth: PlanMonth): Boolean {
        return planMonthRepository.existsByReferenceYearAndReferenceMonth(planMonth.referenceYear, planMonth.referenceMonth)
    }

    fun findCurrent() : PlanMonth {
        return find(LocalDate.now().year, LocalDate.now().month.value)
    }

    fun find(referenceYear: Int?, referenceMonth: Int?) : PlanMonth {
        val planMonth: PlanMonth? = planMonthRepository.findByReferenceYearAndReferenceMonth(referenceYear, referenceMonth)

        return planMonth?: throw NotFoundException(String.format(messageLocator.getMessage(NOT_FOUND), referenceYear, referenceMonth))
    }

    fun findDTO(referenceYear: Int?, referenceMonth: Int?) : PlanMonthDTO {
        return planMonthDTO(find(referenceYear, referenceMonth))
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
