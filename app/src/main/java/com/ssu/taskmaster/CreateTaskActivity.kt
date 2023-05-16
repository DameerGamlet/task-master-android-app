package com.ssu.taskmaster

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ssu.taskmaster.databinding.ActivityCreateTaskBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task
import java.util.*


class CreateTaskActivity : AppCompatActivity() {
    private lateinit var startDateTextView: TextView
    private lateinit var endDateTextView: TextView

    private lateinit var time_date: TextView

    private lateinit var binding: ActivityCreateTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val taskDb = TaskHelperDb.getConnect(this);

        init()

        binding.saveTask.setOnClickListener{
            val task = Task(
                null,
                binding.taskName.text.toString(),
                binding.taskDescription.text.toString(),
                binding.startDateTextCurrent.text.toString(),
                binding.endDateTextCurrent.text.toString(),
                binding.dailyNotificationCheckbox.isChecked,
                binding.timeTextView.text.toString()
            );

            Thread {
                taskDb.getDao().insertTask(task)
            }.start()
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.startDateButton.setOnClickListener {
            showDatePickerDialog(startDateTextView)
        }

        binding.endDateButton.setOnClickListener {
            showDatePickerDialog(endDateTextView)
        }

        val timeDateButton = findViewById<Button>(R.id.time_date)

        timeDateButton.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun init() {
        startDateTextView = findViewById(R.id.start_date_text_current)
        endDateTextView = findViewById(R.id.end_date_text_current)

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