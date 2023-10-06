package br.com.artvimluc.costmate.controller;

import br.com.artvimluc.costmate.domain.PlanMonth
import br.com.artvimluc.costmate.dto.PlanMonthDTO
import br.com.artvimluc.costmate.service.PlanMonthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/plan-month")
class PlanMonthController

@Autowired
constructor (
    private val planMonthService: PlanMonthService,
) {
    @PostMapping
    fun create(@RequestBody planMonth: PlanMonth): ResponseEntity<PlanMonth> {
        return ResponseEntity(planMonthService.create(planMonth), HttpStatus.CREATED)
    }

    @GetMapping("/{referenceMonth}/{referenceYear}")
    fun get(@PathVariable referenceYear: Int, @PathVariable referenceMonth: Int): ResponseEntity<PlanMonthDTO> {
        return ResponseEntity.ok(planMonthService.findDTO(referenceYear, referenceMonth))
    }
}
