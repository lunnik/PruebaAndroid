package com.argaed.pruebaedgar.common

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.argaed.pruebaedgar.details.EmployeeDetailsActivity
import com.argaed.pruebaedgar.model.EmployeeDetails
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme

@Composable
fun EmployeeDetailsLayout(employeeDetails: EmployeeDetails) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .padding(10.dp)
        .clickable {
            val intent = Intent(context, EmployeeDetailsActivity::class.java)
            context.startActivity(intent)
        }) {
        Column {
            Row {
                Text(text = employeeDetails.id.toString(), modifier = Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = employeeDetails.employeeName, modifier = Modifier
                    .weight(1f)
                    .size(20.dp))
                Text(text = employeeDetails.employeeAge.toString(), modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = employeeDetails.employeeSalary.toString(), modifier = Modifier.size(30.dp), color = Color.Green)
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
