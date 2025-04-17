package com.argaed.pruebaedgar.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argaed.pruebaedgar.R
import com.argaed.pruebaedgar.common.preferences.getUsername
import com.argaed.pruebaedgar.login.LoginActivity
import com.argaed.pruebaedgar.main.MainActivity
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaEdgarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    val context = LocalContext.current
                    LaunchedEffect(key1 = true) {

                        if (context.getUsername() == "12345") {
                            delay(3000L)
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                            this@SplashActivity.finish()
                        } else {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }

                    }
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        SplashScreen()
                    }

                }
            }
        }
    }
}

@Composable
fun SplashScreen() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val infiniteTransition = rememberInfiniteTransition(label = "image animation")

        val alpha by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alpha animation"
        )

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.9f,
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "scale animation"
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    this.scaleX = scale
                    this.scaleY = scale
                }

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Edgar Arana, Test",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PruebaEdgarTheme {
        SplashScreen()
    }
}
