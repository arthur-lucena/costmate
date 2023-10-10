package br.com.artvimluc.costmate.repository

import br.com.artvimluc.costmate.domain.PlanMonth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanMonthRepository : JpaRepository<PlanMonth, Long> {
    fun findByReferenceYearAndReferenceMonth(referenceYear: Int?, referenceMonth: Int?): PlanMonth?

    fun existsByReferenceYearAndReferenceMonth(referenceYear: Int?, referenceMonth: Int?): Boolean

    fun deleteByReferenceYearAndReferenceMonth(referenceYear: Int?, referenceMonth: Int?): Boolean
}