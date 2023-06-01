package br.com.artvimluc.costmate;

import br.com.artvimluc.costmate.expense.ExpenseRepository
import br.com.artvimluc.costmate.income.IncomeRepository
import br.com.artvimluc.costmate.planmonth.PlanMonthDTO
import br.com.artvimluc.costmate.planmonth.PlanMonthRepository
import br.com.artvimluc.costmate.planmonth.PlanMonthService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/plan-month")
class PlanMonthController

@Autowired
constructor (
    private val planMonthService: PlanMonthService,
) {
    @PostMapping
    fun create(): ResponseEntity<PlanMonthDTO> {
        return ResponseEntity.ok(planMonthService.create())
    }
}
