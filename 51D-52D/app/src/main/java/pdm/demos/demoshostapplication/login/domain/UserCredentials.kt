package pdm.demos.demoshostapplication.login.domain

import android.util.Patterns

/**
 * Data class representing user credentials as they are collected in the client application.
 * @param email The user's email address.
 * @param password The user's password.
 */
data class UserCredentials(val email: String, val password: String) {
    init {
        require(value = isValidCredentialsData(email, password)) { "Invalid user credentials: $this"}
    }
}

/**
 * Validates if the provided string is a valid email address. Validity in the client application is
 * determined simply by verifying if the string matches the expected pattern for email addresses.
 * In the overall solution, the complete validation is performed by the backend service.
 * @return True if the string is a valid email, false otherwise.
 */
fun String.isValidEmail(): Boolean =
    isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * Validates if the provided string is a valid password. Validity in the client application is
 * determined simply by verifying that the string is not blank. In the overall solution, the complete
 * validation is performed by the backend service.
 * @return True if the string is a valid password, false otherwise.
 */
fun String.isValidPassword(): Boolean = isNotBlank()

/**
 * Validates if the user credentials are valid. Validity in the client application is determined
 * by verifying both the email and password using their respective validation functions. In the
 * overall solution, the complete validation is performed by the backend service.
 * @param email The user's email address.
 * @param password The user's password.
 * @return True if both email and password are valid, false otherwise.
 */
fun isValidCredentialsData(email: String, password: String): Boolean = email.isValidEmail() && password.isValidPassword()

