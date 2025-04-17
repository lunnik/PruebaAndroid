package com.argaed.pruebaedgar.common

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.argaed.pruebaedgar.details.EmployeeDetailsActivity
import com.argaed.pruebaedgar.model.EmployeeDetails
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme

@Composable
fun EmployeeDetailsLayout(employeeDetails: EmployeeDetails) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                val intent = Intent(context, EmployeeDetailsActivity::class.java)
                context.startActivity(intent)
            },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row {
                Text(
                    text = "Employee ID:${employeeDetails.id}",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 50.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Name: ${employeeDetails.employeeName}", modifier = Modifier
                        .weight(.9f),
                    fontSize = 25.sp
                )
                Text(
                    text = "Age: ${employeeDetails.employeeAge}",
                    modifier = Modifier,
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Salary: ${employeeDetails.employeeSalary}",
                    color = Color.Green, fontSize = 30.sp
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun EmployeeDetailsPreview() {
    PruebaEdgarTheme {
        EmployeeDetailsLayout(EmployeeDetails())
    }
}
