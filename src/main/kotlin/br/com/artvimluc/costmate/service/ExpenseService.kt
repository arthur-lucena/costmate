package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.CreditCard
import br.com.artvimluc.costmate.repository.CreditCardRepository
import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.dto.PlanMonthDTO
import br.com.artvimluc.costmate.repository.ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ExpenseService

    @Autowired
    constructor (
        private val expenseRepository: ExpenseRepository,
        private val creditCardRepository: CreditCardRepository,
        private val planMonthService: PlanMonthService,
    ) {

    fun create(current: Boolean): Expense {
        var cardAme : CreditCard = CreditCard(
            name = "Cartão Ame",
            description = "Meu cartão secundário",
            dayDueDate = 10,
            daysToClose = 13
        )

        cardAme = creditCardRepository.save(cardAme)

        val planMonth = planMonthService.findCurrent();

        val expense: Expense = Expense(
            planMonthId = planMonth.id,
            creditCardId = cardAme.id,
            description = "cartao nu",
            value = BigDecimal.valueOf(3580.89),
            installment = 1,
            totalInstallments = 1,
            refund = false,
            receiptFileUploaded = false)

        expenseRepository.save(expense)

//        return find(2023, 12)
        return Expense()
    }

    fun findAll(year: Int?, month: Int?) : List<Expense> {

    }
}
