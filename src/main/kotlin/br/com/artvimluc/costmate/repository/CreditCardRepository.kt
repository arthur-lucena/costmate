package br.com.artvimluc.costmate.repository

import br.com.artvimluc.costmate.domain.CreditCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditCardRepository : JpaRepository<CreditCard, Long> {

}