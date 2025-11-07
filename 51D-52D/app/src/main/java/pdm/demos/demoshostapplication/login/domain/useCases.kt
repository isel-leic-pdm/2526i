package pdm.demos.demoshostapplication.login.domain


/**
 * Type alias representing the login use case function signature.
 * @param credentials The user's credentials.
 * @param loginService The service to be used for login.
 * @param authInfoRepo The repository to store the authentication information.
 */
typealias LoginUseCase = suspend (
    credentials: UserCredentials,
    loginService: LoginService,
    authInfoRepo: AuthInfoRepo
) -> AuthInfo

/**
 * Performs the implementation of the login use case.
 * @param credentials The user's credentials.
 * @param loginService The service to be used for login.
 * @param authInfoRepo The repository to store the authentication information.
 * @return The authentication information upon successful login.
 */
suspend fun performLogin(
    credentials: UserCredentials,
    loginService: LoginService,
    authInfoRepo: AuthInfoRepo
): AuthInfo {
    val authToken = loginService.login(credentials)
    val authInfo = AuthInfo(userEmail = credentials.email, authToken = authToken)
    authInfoRepo.saveAuthInfo(authInfo)
    return authInfo
}

