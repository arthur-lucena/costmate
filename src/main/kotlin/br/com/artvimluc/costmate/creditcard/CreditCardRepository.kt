package br.com.artvimluc.costmate.creditcard

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditCardRepository : JpaRepository<CreditCard, Long> {

}