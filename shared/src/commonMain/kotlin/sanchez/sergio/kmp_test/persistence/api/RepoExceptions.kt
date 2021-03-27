package sanchez.sergio.kmp_test.persistence.api

/**
 * Repo Error Common Exception
 * @param cause
 */
class RepoErrorException(cause: Throwable): Exception(cause)

/**
 * Repo No Result Exception
 * @param cause
 */
class RepoNoResultException(cause: Throwable): Exception(cause)