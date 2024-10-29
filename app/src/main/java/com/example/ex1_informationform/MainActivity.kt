package com.example.ex1_informationform

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ex1_informationform.helper.AddressHelper

class MainActivity : AppCompatActivity() {

    private lateinit var addressHelper: AddressHelper
    private lateinit var selectedDOB: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addressHelper = AddressHelper(resources)

        // Calendar toggle
        val btnToggleCalendar = findViewById<Button>(R.id.btnToggleCalendar)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        btnToggleCalendar.setOnClickListener {
            val newVisibility = if (calendarView.visibility == View.GONE) {
                View.VISIBLE
            } else {
                View.GONE
            }
            calendarView.visibility = newVisibility
            Log.d("MainActivity", "Calendar visibility changed: $newVisibility")
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDOB = "$dayOfMonth/${month + 1}/$year"
            Log.d("MainActivity", "Selected Date: $selectedDOB")
        }

        // Populate Spinners
        populateSpinners()

        // Submit button listener
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            handleSubmit()
        }
    }

    private fun populateSpinners() {
        val provinces = addressHelper.getProvinces()
        // Setup adapter and handle district and ward updates based on province selection
        // ...
    }

    private fun handleSubmit() {
        val mssv = findViewById<EditText>(R.id.etMSSV).text.toString()
        val fullName = findViewById<EditText>(R.id.etFullName).text.toString()
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()
        val agreeTerms = findViewById<CheckBox>(R.id.cbAgreeTerms).isChecked

        if (mssv.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || !agreeTerms) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Additional checks and submission logic
    }
}
