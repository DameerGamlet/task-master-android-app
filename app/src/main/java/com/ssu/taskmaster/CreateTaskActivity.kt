package com.ssu.taskmaster

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class CreateTaskActivity : AppCompatActivity() {
    private lateinit var startDateTextView: TextView
    private lateinit var endDateTextView: TextView
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button

    private lateinit var time_date: TextView
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        init()

        back.setOnClickListener {
            finish()
        }

        startDateButton.setOnClickListener {
            showDatePickerDialog(startDateTextView)
        }

        endDateButton.setOnClickListener {
            showDatePickerDialog(endDateTextView)
        }

        val timeDateButton = findViewById<Button>(R.id.time_date)

        timeDateButton.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun init() {
        back = findViewById(R.id.back)

        startDateTextView = findViewById(R.id.start_date_text_current)
        endDateTextView = findViewById(R.id.end_date_text_current)

        startDateButton = findViewById(R.id.start_date_button)
        endDateButton = findViewById(R.id.end_date_button)

        time_date = findViewById(R.id.time_text_view)
    }

    private fun showTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val time = String.format("%02d:%02d", hourOfDay, minute)
                time_date.text = time
            },
            0, 0, true
        )
        timePickerDialog.show()
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            textView.text = date
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }
}