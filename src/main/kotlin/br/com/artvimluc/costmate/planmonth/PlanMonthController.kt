package br.com.artvimluc.costmate.planmonth;

import org.springframework.beans.factory.annotation.Autowired
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
    fun create(@RequestBody planMonth: PlanMonth): ResponseEntity<PlanMonthDTO> {
        return ResponseEntity.ok(planMonthService.create(planMonth))
    }

    @GetMapping("/{month}/{year}")
    fun get(@PathVariable month: Int, @PathVariable year: Int): ResponseEntity<PlanMonthDTO> {
        return ResponseEntity.ok(planMonthService.find(year, month))
    }
}
