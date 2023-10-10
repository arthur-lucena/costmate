package br.com.artvimluc.costmate.web.controller;

import br.com.artvimluc.costmate.domain.Expense
import br.com.artvimluc.costmate.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expense")
class ExpenseController

@Autowired
constructor (
    private val expenseService: ExpenseService,
) {
    @PostMapping("/{referenceMonth}/{referenceYear}")
    fun create(@PathVariable referenceYear: Int, @PathVariable referenceMonth: Int,
               @RequestBody expense: Expense): ResponseEntity<Expense> {
        return ResponseEntity.ok(expenseService.create(expense))
    }

    @GetMapping("/{referenceMonth}/{referenceYear}")
    fun get(@PathVariable referenceYear: Int, @PathVariable referenceMonth: Int): ResponseEntity<List<Expense?>?> {
        return ResponseEntity.ok(expenseService.find(referenceYear, referenceMonth))
    }
}
