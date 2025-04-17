package com.argaed.pruebaedgar.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.argaed.pruebaedgar.common.EmployeeDetailsLayout
import com.argaed.pruebaedgar.common.FullScreenLoading
import com.argaed.pruebaedgar.feature.domain.use_case.get_employee.GetEmployeeFailure
import com.argaed.pruebaedgar.main.MainActivity
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val idEmployee = intent.getStringExtra(MainActivity.ID_EMPLOYEE) ?: "1"
            Log.e("id",idEmployee )
            PruebaEdgarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        LoadEmployeeDetails(idEmployee)
                    }

                }
            }
        }
    }
}

@Composable
fun LoadEmployeeDetails(
    idEmployee: String,
    employeeDetailsViewModel: EmployeeDetailsViewModel = hiltViewModel(),
) {
    var firstCall by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val uiState by employeeDetailsViewModel.uiState.collectAsStateWithLifecycle(
        initialValue = EmployeeDetailsState(),
        lifecycle = lifecycleOwner.lifecycle,
        minActiveState = lifecycleOwner.lifecycle.currentState,
        context = lifecycleOwner.lifecycleScope.coroutineContext
    )

    EmployeeDetailsScreen(uiState = uiState.employeeDetailsStatus)
    LaunchedEffect(key1 = Unit) {
        if (!firstCall) {
            scope.launch { employeeDetailsViewModel.loadEmployeeDetails(id = idEmployee) }
            firstCall = true
        }

    }

}

@Composable
fun EmployeeDetailsScreen(
    uiState: EmployeeDetailsStatus,
) {
    val context = LocalContext.current
    uiState.let { employeeState ->
        employeeState.also { stateActivity ->
            FullScreenLoading(false)
            when (stateActivity) {
                is Status.Loading -> FullScreenLoading(true)
                is Status.Failed -> manageLoadDetailsFailure(stateActivity.failure, context)
                is Status.Done -> EmployeeDetailsLayout(stateActivity.value)
            }
        }
    }

}

private fun manageLoadDetailsFailure(failure: Failure, context: Context) =

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
        EmployeeDetailsScreen(uiState = Status.Loading() )
    }
}