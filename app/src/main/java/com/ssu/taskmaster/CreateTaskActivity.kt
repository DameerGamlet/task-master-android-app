package com.ssu.taskmaster

//noinspection SuspiciousImport
import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ssu.taskmaster.databinding.ActivityCreateTaskBinding
import com.ssu.taskmaster.db.TaskHelperDb
import com.ssu.taskmaster.models.Task
import java.util.Calendar

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTaskBinding

    private val tagList = "lichnoe,rabota,ucheba"
        .replace(" ", "")
        .split(",")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)

        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, tagList)

        binding.tags.setAdapter(adapter)

        binding.tags.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        setContentView(binding.root)

        val taskDb = TaskHelperDb.getConnect(this);

        binding.saveTask.setOnClickListener {
            val taskName = binding.taskName.text.toString()
            val taskDescription = binding.taskDescription.text.toString()
            val startDate = binding.startDateTextCurrent.text.toString()
            val endDate = binding.endDateTextCurrent.text.toString()
            val dailyNotification = binding.dailyNotificationCheckbox.isChecked
            val time = binding.timeDate.text.toString()

            if (taskName.isNotEmpty()) {
                val task = Task(
                    null,
                    taskName,
                    taskDescription,
                    startDate,
                    endDate,
                    dailyNotification,
                    time
                )

                Thread {
                    taskDb.getDao().insertTask(task)
                }.start()

                Toast.makeText(this, "Запись удачно создана!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Пожалуйста заполните все необходымые данные!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnClear.setOnClickListener {
            clearAll()
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.startDateButton.setOnClickListener {
            showDatePickerDialog(binding.startDateTextCurrent)
        }

        binding.endDateButton.setOnClickListener {
            showDatePickerDialog(binding.endDateTextCurrent)
        }

        binding.timeDate.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun clearAll() {
        binding.taskName.text = null
        binding.taskDescription.text = null
        binding.startDateTextCurrent.text = null
        binding.endDateTextCurrent.text = null
        binding.dailyNotificationCheckbox.isChecked = false
        binding.timeDate.text = null
    }

    private fun showTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val time = String.format("%02d:%02d", hourOfDay, minute)
                binding.timeDate.text = time
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

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                textView.text = date
            }, year, month, dayOfMonth
        )

        datePickerDialog.show()
    }
}