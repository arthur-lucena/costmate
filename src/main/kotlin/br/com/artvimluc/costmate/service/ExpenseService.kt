package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.repository.ExpenseRepository
import br.com.artvimluc.costmate.util.MessageLocator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private const val NOT_FOUND = "expense.not.found"

@Service
class ExpenseService

    @Autowired
    constructor (
        private val expenseRepository: ExpenseRepository,
        private val messageLocator: MessageLocator,
    ) {

    fun create(expense: Expense): Expense {
        return expenseRepository.save(expense)
    }

    fun update(id: Long, expense: Expense): Expense {
        val expenseOnBase = find(id)

        expense.id = expenseOnBase.id

        return expenseRepository.save(expense)
    }

    fun delete(id: Long) {
        val expense = find(id)

        expenseRepository.delete(expense)
    }

    fun find(id: Long) : Expense {
        val expense = expenseRepository.findById(id)

        return expense.orElseThrow { throw NotFoundException(
            String.format(
                messageLocator.getMessage(NOT_FOUND),
                id
            )) }
    }

//    fun create(expense: Expense): Expense {
//        var cardAme = CreditCard(
//            name = "Cartão Ame",
//            description = "Meu cartão secundário",
//            dayDueDate = 10,
//            daysToClose = 13
//        )
//
//        cardAme = creditCardRepository.save(cardAme)
//
//        val planMonth = planMonthService.findCurrent();
//
//        val expense = Expense(
//            planMonthId = planMonth.id,
//            creditCardId = cardAme.id,
//            description = "cartão nu",
//            value = BigDecimal.valueOf(3580.89),
//            installment = 1,
//            totalInstallments = 1,
//            refund = false,
//            receiptFileUploaded = false)
//
//        return expenseRepository.save(expense)
//    }
//
//    fun find(year: Int?, month: Int?) : List<Expense?>? {
//        val planMonth = planMonthService.find(year, month);
//
//        return expenseRepository.findByPlanMonthId(planMonth.id)
//    }
}
