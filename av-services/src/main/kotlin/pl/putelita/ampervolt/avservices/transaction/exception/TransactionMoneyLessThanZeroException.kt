package pl.putelita.ampervolt.avservices.transaction.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.putelita.ampervolt.avservices.exception.AvServiceException
import pl.putelita.ampervolt.avservices.exception.ErrorCode

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TransactionMoneyLessThanZeroException: AvServiceException(ErrorCode.TRANSACTION_MONEY_LESS_THAN_ZERO) {
}