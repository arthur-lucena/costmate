package br.com.artvimluc.costmate.service

import br.com.artvimluc.costmate.domain.CreditCard
import br.com.artvimluc.costmate.exception.NotFoundException
import br.com.artvimluc.costmate.repository.CreditCardRepository
import br.com.artvimluc.costmate.util.MessageLocator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private const val NOT_FOUND = "credit.card.not.found"

@Service
class CreditCardService

    @Autowired
    constructor (
        private val creditCardRepository: CreditCardRepository,
        private val messageLocator: MessageLocator,
    ) {

    fun create(creditCard: CreditCard): CreditCard {
        return creditCardRepository.save(creditCard)
    }

    fun update(id: Long, creditCard: CreditCard): CreditCard {
        val creditCardOnBase = find(id)

        creditCard.id = creditCardOnBase.id

        return creditCardRepository.save(creditCard)
    }

    fun delete(id: Long) {
        val creditCard = find(id)

        creditCardRepository.delete(creditCard)
    }

    fun find(id: Long) : CreditCard {
        val creditCard = creditCardRepository.findById(id)

        return creditCard.orElseThrow { throw NotFoundException(
            String.format(
                messageLocator.getMessage(NOT_FOUND),
                id
            )
        )
        }
    }
}