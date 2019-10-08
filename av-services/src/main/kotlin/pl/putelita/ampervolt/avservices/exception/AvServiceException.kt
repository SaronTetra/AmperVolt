package pl.putelita.ampervolt.avservices.exception

abstract class AvServiceException(exceptionCode: ErrorCode): RuntimeException(exceptionCode.toString()) {
}