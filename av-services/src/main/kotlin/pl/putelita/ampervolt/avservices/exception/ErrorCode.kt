package pl.putelita.ampervolt.avservices.exception

enum class ErrorCode {
    CLIENT_NOT_FOUND,
    CLIENT_NAME_EXISTS,
    ACCOUNT_NOT_FOUND,
    ACCOUNT_NUMBER_EXISTS,
    TRANSACTION_NOT_FOUND,
    TRANSACTION_MONEY_LESS_THAN_ZERO
}