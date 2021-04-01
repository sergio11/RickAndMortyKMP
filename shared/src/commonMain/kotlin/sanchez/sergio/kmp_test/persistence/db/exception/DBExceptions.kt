package sanchez.sergio.kmp_test.persistence.db.exception

/**
 * DB Common Error Exception
 */
class DBErrorException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

class DBNoResultException(message: String? = null, cause: Throwable? = null): Exception(message, cause)