package com.argaed.pruebaedgar.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.araged.jetpack.presentation.home.failure_manage.manageEmployeeFailure
import com.argaed.pruebaedgar.clean.core.Failure
import com.argaed.pruebaedgar.clean.core.presentation.Status
import com.argaed.pruebaedgar.common.EmployeeLayout
import com.argaed.pruebaedgar.common.FullScreenLoading
import com.argaed.pruebaedgar.common.preferences.clearUsername
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.login.LoginActivity
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
       const val  ID_EMPLOYEE = "id"
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaEdgarTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text("Mi Toolbar") },
                        navigationIcon = {
                            IconButton(onClick = { /* acción al hacer clic en el icono */ }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Atrás"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                context.clearUsername()
                                val intent = Intent(context, LoginActivity::class.java)
                                context.startActivity(intent)
                                Toast.makeText(context, "La sesión esta cerrada", Toast.LENGTH_LONG).show()
                                val mainActivity = context as? Activity
                                mainActivity?.finish()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Más opciones"
                                )
                            }
                        }
                    )
                }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        LoadEmployee()
                    }
                }
            }
        }
    }

    @Composable
    fun LoadEmployee(
        mainViewModel: MainViewModel = hiltViewModel(),
    ) {
        var firstCall by rememberSaveable { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
        val uiState by mainViewModel.uiState.collectAsStateWithLifecycle(
            initialValue = EmployeeState(),
            lifecycle = lifecycleOwner.lifecycle,
            minActiveState = lifecycleOwner.lifecycle.currentState,
            context = lifecycleOwner.lifecycleScope.coroutineContext
        )

        EmployeeScreen(uiState = uiState.employeeStatus)
        LaunchedEffect(key1 = Unit) {
            if (!firstCall) {
                scope.launch { mainViewModel.loadEmployee() }
                firstCall = true
            }

        }

    }

    @Composable
    fun EmployeeScreen(
        uiState: EmployeeStatus,
    ) {
        val context = LocalContext.current
        uiState.let { employeeState ->
            employeeState.also { stateActivity ->
                FullScreenLoading(false)
                when (stateActivity) {
                    is Status.Loading -> FullScreenLoading(true)
                    is Status.Failed -> manageLoadHomeFailure(stateActivity.failure, context)
                    is Status.Done -> EmployeeLayout(stateActivity.value.data)
                }
            }
        }

    }

    private fun manageLoadHomeFailure(failure: Failure, context: Context) =

        failure.let {
            when (it) {
                is GetEmployeeFailure -> {
                    context.manageEmployeeFailure(it)
                }

            }
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PruebaEdgarTheme {
            EmployeeScreen(uiState = Status.Loading())
        }
    }
}