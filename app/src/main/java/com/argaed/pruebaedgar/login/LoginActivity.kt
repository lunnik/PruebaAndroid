package com.argaed.pruebaedgar.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.argaed.pruebaedgar.R
import com.argaed.pruebaedgar.main.MainActivity
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaEdgarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var user by remember { mutableStateOf("") }
    val isValid by remember { mutableStateOf(true) }
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .weight(.7f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.height(60.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(120.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Enter user ID",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = user,
                        onValueChange = { user = it },
                        label = { Text("Input text") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (user=="12345") isValid

                }
            }
        }

        Box(
            modifier = Modifier
                .weight(.3f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (!isValid && user == "12345") {
                        Toast.makeText(context, "El user debe ser 12345", Toast.LENGTH_LONG).show()
                    } else {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }

                }
            ) {
                Text("Login")
            }
        }
    }


}

@Composable
fun ValidationLoginScreen(isValid: Boolean, user: String) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    PruebaEdgarTheme {
        LoginScreen()
    }
}