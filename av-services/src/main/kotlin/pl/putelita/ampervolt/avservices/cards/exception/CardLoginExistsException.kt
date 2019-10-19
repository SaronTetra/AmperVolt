package pl.putelita.ampervolt.avservices.cards.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.putelita.ampervolt.avservices.exception.AvServiceException
import pl.putelita.ampervolt.avservices.exception.ErrorCode

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CardNumberExistsException: AvServiceException(ErrorCode.CARD_NUMBER_EXISTS) {}