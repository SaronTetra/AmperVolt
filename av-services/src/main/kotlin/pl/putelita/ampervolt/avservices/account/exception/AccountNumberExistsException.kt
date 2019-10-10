package pl.putelita.ampervolt.avservices.account.exception

import pl.putelita.ampervolt.avservices.exception.AvServiceException
import pl.putelita.ampervolt.avservices.exception.ErrorCode

class AccountNumberExistsException: AvServiceException(ErrorCode.ACCOUNT_NUMBER_EXISTS) {
}