package pdm.demos.demoshostapplication.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.login.domain.FakeLoginService
import pdm.demos.demoshostapplication.login.domain.isValidCredentialsData
import pdm.demos.demoshostapplication.ui.TopBar

/**
 * Composable function representing the Login Screen demo.
 */
@Composable
fun LoginScreen(
    onBackNavigationIntent: () -> Unit,
    viewModel: LoginScreenViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.login_title),
                onBackIntent = onBackNavigationIntent,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val observedState = viewModel.currentState
        LaunchedEffect(observedState) {
            if (observedState is LoginScreenState.LoginSuccess) {
                onBackNavigationIntent()
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 48.dp)
                .imePadding()
        ) {
            LoginForm(
                loading = observedState is LoginScreenState.LoginInProgress,
                error = if (observedState is LoginScreenState.LoginError) observedState.errorMessage else "",
                onLogin = { tentativeCredentials -> viewModel.login(tentativeCredentials) },
                validateCredentials = ::isValidCredentialsData,
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onBackNavigationIntent = {},
        viewModel = LoginScreenViewModel(service = FakeLoginService())
    )
}