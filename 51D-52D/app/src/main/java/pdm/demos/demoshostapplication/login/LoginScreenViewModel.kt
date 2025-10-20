package pdm.demos.demoshostapplication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pdm.demos.demoshostapplication.login.domain.InvalidCredentialsException
import pdm.demos.demoshostapplication.login.domain.LoginService
import pdm.demos.demoshostapplication.login.domain.UserCredentials

/**
 * Represents the state of the login screen.
 */
interface LoginScreenState {
    object Idle : LoginScreenState
    data class LoginInProgress(val tentativeCredentials: UserCredentials) : LoginScreenState
    data class LoginSuccess(val authToken: String) : LoginScreenState
    data class LoginError(val errorMessage: String) : LoginScreenState
}

/**
 * ViewModel for the Login screen.
 * @property service The login service to authenticate users.
 */
class LoginScreenViewModel(private val service: LoginService) : ViewModel() {

    companion object {
        /**
         * Returns a factory to create a [LoginScreenViewModel] with the provided [LoginService].
         * @param service The service to be used to login.
         */
        fun getFactory(service: LoginService) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
                    LoginScreenViewModel(service) as T
                }
                else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /**
     * The current state of the login screen.
     */
    var currentState: LoginScreenState by mutableStateOf(LoginScreenState.Idle)

    /**
     * Initiates the login process with the provided [credentials].
     * @param credentials The user credentials to be used for login.
     */
    fun login(credentials: UserCredentials) {

        if (currentState is LoginScreenState.LoginInProgress || currentState is LoginScreenState.LoginSuccess) {
            return
        }

        currentState = LoginScreenState.LoginInProgress(tentativeCredentials = credentials)

        viewModelScope.launch {
            currentState = try {
                LoginScreenState.LoginSuccess(authToken = service.login(credentials))
            } catch (e: InvalidCredentialsException) {
                LoginScreenState.LoginError(errorMessage = e.message ?: "Authentication error")
            }
        }
    }
}