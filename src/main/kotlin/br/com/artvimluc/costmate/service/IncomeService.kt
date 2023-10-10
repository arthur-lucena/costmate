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

private const val NOT_FOUND = "income.not.found"

@Service
class IncomeService

    @Autowired
    constructor (
        private val incomeRepository: IncomeRepository,
        private val messageLocator: MessageLocator,
    ) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun create(income: Income): Income {
        return incomeRepository.save(income)
    }

    fun update(id: Long, income: Income): Income {
        val incomeOnBase = find(id)

        income.id = incomeOnBase.id

        return incomeRepository.save(income)
    }

    fun delete(id: Long) {
        val income = find(id)

        incomeRepository.delete(income)
    }

    fun find(id: Long) : Income {
        val income = incomeRepository.findById(id)

        return income.orElseThrow { throw NotFoundException(
            String.format(
                messageLocator.getMessage(NOT_FOUND),
                id
            )) }
    }

//    fun exists(planMonth: PlanMonth): Boolean {
//        return planMonthRepository.existsByReferenceYearAndReferenceMonth(planMonth.referenceYear, planMonth.referenceMonth)
//    }
//    fun findCurrent() : PlanMonth {
//        return find(LocalDate.now().year, LocalDate.now().month.value)
//    }
//
//    fun findDTO(referenceYear: Int?, referenceMonth: Int?) : PlanMonthDTO {
//        return planMonthDTO(find(referenceYear, referenceMonth))
//    }
//
//    private fun planMonthDTO(planMonth: PlanMonth): PlanMonthDTO {
//        val mapper = ModelMapper()
//        val dto: PlanMonthDTO = mapper.map(planMonth, PlanMonthDTO::class.java)
//        val expenses: List<Expense?>? = expenseRepository.findByPlanMonthId(planMonth.id)
//        val incomes: List<Income?>? = incomeRepository.findByPlanMonthId(planMonth.id)
//        dto.listExpense = expenses
//        dto.listIncome = incomes
//        return dto
//    }
}