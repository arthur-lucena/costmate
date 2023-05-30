package br.com.artvimluc.costmate.planmonth

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanMonthRepository : JpaRepository<PlanMonth, Long> {
    fun findByReferenceYearAndReferenceMonth(referenceYear: Int?, referenceMonth: Int?): PlanMonth

}