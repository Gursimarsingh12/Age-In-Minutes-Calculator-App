package com.example.ageinminutescalculatorapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// getInstance() methods used to obtain instances of database helpers or API service interfaces.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val tvSelectedDate:TextView = findViewById(R.id.tvSelectedDate)
        val tvOutput:TextView = findViewById(R.id.tvOutput)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Toast.makeText(this, "Your selected date was $selectedDayOfMonth-${selectedMonth+1}-$selectedYear", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)  // .parse() function for various purposes, such as parsing strings into different data types or objects
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000    // 60 s = 60000 millisec
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))    //When working with date and time objects, you can use the .format() function to convert them into formatted strings
                currentDate?.let{
                    val currentDateInMinutes = currentDate.time / 60000
                    val difference = currentDateInMinutes - selectedDateInMinutes
                    tvOutput.text = difference.toString()
                    }
                }
            }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}