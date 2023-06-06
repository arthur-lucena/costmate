package br.com.artvimluc.costmate.expense

import br.com.artvimluc.costmate.creditcard.CreditCard
import br.com.artvimluc.costmate.creditcard.CreditCardRepository
import br.com.artvimluc.costmate.planmonth.PlanMonthDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExpenseService

    @Autowired
    constructor (
        private val expenseRepository: ExpenseRepository,
        private val creditCardRepository: CreditCardRepository,
    ) {

    fun create(): Expense {
        var cardAme : CreditCard = CreditCard(
            name = "Cartão Ame",
            description = "Meu cartão secundário",
            dayDueDate = 10,
            daysToClose = 13
        )

        cardAme = creditCardRepository.save(cardAme)

//        val expense: Expense = Expense(
//            planMonthId = planMonth.id,
//            creditCardId = cardAme.id,
//            description = "cartao nu",
//            value = BigDecimal.valueOf(3580.89),
//            installment = 1,
//            totalInstallments = 1,
//            refund = false,
//            receiptFileUploaded = false)
//
//        expenseRepository.save(expense)
//
//        return find(2023, 12)
        return Expense()
    }

    fun find(year: Int?, month: Int?) : PlanMonthDTO {
//        val planMonth: PlanMonth = planMonthRepository.findByReferenceYearAndReferenceMonth(year, month)
//        val mapper = ModelMapper()
//        val dto: PlanMonthDTO = mapper.map(planMonth, PlanMonthDTO::class.java)
//        val expenses: List<Expense?>? = expenseRepository.findByPlanMonthId(planMonth.id)
//        val incomes: List<Income?>? = incomeRepository.findByPlanMonthId(planMonth.id)
//        dto.listExpense = expenses
//        dto.listIncome = incomes
        return PlanMonthDTO()
    }
}
