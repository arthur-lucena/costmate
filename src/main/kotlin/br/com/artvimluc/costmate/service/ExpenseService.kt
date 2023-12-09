package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.repository.ExpenseRepository
import br.com.artvimluc.costmate.util.MessageLocator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.InputStream

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

    fun find() : List<Expense> {
        return expenseRepository.findAll();
    }

    fun readCsv(inputStream: InputStream): List<Expense> {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()

        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (description, value, descCreditCard, fixed, refund, percentage, installment, totalInstallment, month, year) = it.split(',', ignoreCase = false, limit = 8)

            }.toList()
    }
}
