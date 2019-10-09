package pl.putelita.ampervolt.avservices.account.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.putelita.ampervolt.avservices.exception.AvServiceException
import pl.putelita.ampervolt.avservices.exception.ErrorCode

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException: AvServiceException(ErrorCode.ACCOUNT_NOT_FOUND) {}