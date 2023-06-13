package com.ssu.taskmaster.components.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ssu.taskmaster.models.Task

class TaskInfoDialogFragment(private val task: Task) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Информация о задаче")
            .setMessage(getTaskInfoString(task))
            .setPositiveButton("ОК") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        return builder.create()
    }

    private fun getTaskInfoString(task: Task): String {
        val sb = StringBuilder()
        sb.append("ID: ${task.id}\n")
        sb.append("Название: ${task.name}\n")
        sb.append("Описание: ${task.description}\n")
        sb.append("Начало: ${task.startDate}\n")
        sb.append("Конец: ${task.endDate}\n")
        sb.append("Уведомления: ${if (task.isNotify) "Включены" else "Выключены"}\n")
        sb.append("Время уведомления: ${task.notifyTime}\n")
        sb.append("Завершена: ${if (task.isCompleted) "Да" else "Нет"}")
        return sb.toString()
    }
}
