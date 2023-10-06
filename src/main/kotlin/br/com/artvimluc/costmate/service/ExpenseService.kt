package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.CreditCard
import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.repository.CreditCardRepository
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

    fun create(expense: Expense): Expense {
        var cardAme = CreditCard(
            name = "Cartão Ame",
            description = "Meu cartão secundário",
            dayDueDate = 10,
            daysToClose = 13
        )

        cardAme = creditCardRepository.save(cardAme)

        val planMonth = planMonthService.findCurrent();

        val expense = Expense(
            planMonthId = planMonth.id,
            creditCardId = cardAme.id,
            description = "cartão nu",
            value = BigDecimal.valueOf(3580.89),
            installment = 1,
            totalInstallments = 1,
            refund = false,
            receiptFileUploaded = false)

        return expenseRepository.save(expense)
    }

    fun find(year: Int?, month: Int?) : List<Expense?>? {
        val planMonth = planMonthService.find(year, month);

        return expenseRepository.findByPlanMonthId(planMonth.id)
    }
}
