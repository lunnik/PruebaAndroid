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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.argaed.pruebaedgar.details.EmployeeDetailsActivity
import com.argaed.pruebaedgar.main.MainActivity
import com.argaed.pruebaedgar.model.EmployeeData
import com.argaed.pruebaedgar.ui.theme.PruebaEdgarTheme

@Composable
fun EmployeeLayout(employeesData:List<EmployeeData>) {
    val context = LocalContext.current
    Box(modifier = Modifier.padding(10.dp)) {
        LazyColumn(){
            items(employeesData){
                Column(modifier = Modifier.clickable {
                    val intent = Intent(context, EmployeeDetailsActivity::class.java)
                    intent.putExtra(MainActivity.ID_EMPLOYEE, it.id.toString())
                    context.startActivity(intent)
                }) {
                    Row {
                        Text(text = it.id.toString())
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = it.employeeName, modifier = Modifier.weight(1f))
                        Text(text = it.employeeAge.toString())
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun EmployeeLayoutPreview() {
    PruebaEdgarTheme {
        EmployeeLayout(arrayListOf())
    }
}