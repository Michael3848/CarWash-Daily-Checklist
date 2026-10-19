package com.example.carwash_daily_checklist

import android.app.AlertDialog
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.carwash_daily_checklist.data.AppDatabase
import com.example.carwash_daily_checklist.data.Task
import com.example.carwash_daily_checklist.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    private lateinit var taskCountText: TextView
    private lateinit var taskContainer: LinearLayout
    private lateinit var newTaskButton: Button
    private lateinit var filterGroup: RadioGroup
    private lateinit var taskDao: TaskDao

    private var currentTasks: List<Task> = emptyList()
    private var selectedFilterId: Int = R.id.filterAll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left + dpToPx(20),
                systemBars.top + dpToPx(20),
                systemBars.right + dpToPx(20),
                systemBars.bottom + dpToPx(20)
            )

            insets
        }

        taskCountText = findViewById(R.id.tvTaskCount)
        taskContainer = findViewById(R.id.taskContainer)
        newTaskButton = findViewById(R.id.btnNewTask)
        filterGroup = findViewById(R.id.filterGroup)

        val database = AppDatabase.getDatabase(this)
        taskDao = database.taskDao()

        newTaskButton.setOnClickListener {
            showTaskFormDialog()
        }
        filterGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedFilterId = checkedId
            displayFilteredTasks()
        }

        insertInitialTasksIfNecessary()
        observeTasks()
    }

    private fun insertInitialTasksIfNecessary() {
        lifecycleScope.launch {
            val taskCount = withContext(Dispatchers.IO) {
                taskDao.getTaskCount()
            }

            if (taskCount == 0) {
                val initialTasks = listOf(
                    Task(
                        title = "Revisar inventario de jabón y cera"
                    ),
                    Task(
                        title = "Organizar herramientas de trabajo"
                    ),
                    Task(
                        title = "Limpiar el área de trabajo"
                    ),
                    Task(
                        title = "Verificar el funcionamiento de la hidrolavadora"
                    )
                )

                withContext(Dispatchers.IO) {
                    initialTasks.forEach { task ->
                        taskDao.insertTask(task)
                    }
                }
            }
        }
    }

    private fun observeTasks() {
        lifecycleScope.launch {
            taskDao.getAllTasks().collectLatest { tasks ->
                currentTasks = tasks
                updatePendingTaskCount(tasks)
                displayFilteredTasks()
            }
        }
    }

    private fun displayFilteredTasks() {
        val filteredTasks = when (selectedFilterId) {
            R.id.filterPending -> {
                currentTasks.filter { !it.isCompleted }
            }

            R.id.filterCompleted -> {
                currentTasks.filter { it.isCompleted }
            }

            else -> {
                currentTasks
            }
        }

        displayTasks(filteredTasks)
    }
    private fun displayTasks(tasks: List<Task>) {
        taskContainer.removeAllViews()

        if (tasks.isEmpty()) {
            val emptyMessage = TextView(this).apply {
                text = when (selectedFilterId) {
                    R.id.filterPending ->
                        "No hay tareas pendientes."

                    R.id.filterCompleted ->
                        "Todavía no hay tareas completadas."

                    else ->
                        "No hay tareas registradas."
                }

                textSize = 16f
                setTextColor(0xFF566573.toInt())
                gravity = android.view.Gravity.CENTER
                setPadding(
                    dpToPx(12),
                    dpToPx(24),
                    dpToPx(12),
                    dpToPx(24)
                )
            }

            taskContainer.addView(emptyMessage)
            return
        }

        tasks.forEachIndexed { index, task ->
            if (index > 0) {
                taskContainer.addView(createDivider())
            }

            val checkBox = createTaskCheckBox(task)
            taskContainer.addView(checkBox)
        }
    }

    private fun createTaskCheckBox(task: Task): CheckBox {
        return CheckBox(this).apply {
            id = View.generateViewId()
            text = formatTaskText(
                task.title,
                task.description
            )
            textSize = 16f
            setTextColor(0xFF212F3D.toInt())
            minHeight = dpToPx(52)
            setPadding(
                0,
                dpToPx(4),
                0,
                dpToPx(4)
            )

            isChecked = task.isCompleted
            updateTaskStyle(this, task.isCompleted)

            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != task.isCompleted) {
                    updateTaskCompletion(
                        task = task,
                        isCompleted = isChecked
                    )
                }
            }

            setOnLongClickListener {
                showTaskOptionsDialog(task)
                true
            }
        }
    }

    private fun showTaskFormDialog(
        taskToEdit: Task? = null
    ) {
        val dialogContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(
                dpToPx(24),
                dpToPx(8),
                dpToPx(24),
                0
            )
        }

        val titleInput = EditText(this).apply {
            hint = "Título de la tarea"
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            maxLines = 2
            setText(taskToEdit?.title.orEmpty())
        }

        val descriptionInput = EditText(this).apply {
            hint = "Descripción opcional"
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or
                    InputType.TYPE_TEXT_FLAG_MULTI_LINE
            minLines = 2
            maxLines = 4
            setText(taskToEdit?.description.orEmpty())
        }

        dialogContainer.addView(titleInput)
        dialogContainer.addView(descriptionInput)

        val dialogTitle = if (taskToEdit == null) {
            "Agregar nueva tarea"
        } else {
            "Editar tarea"
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(dialogTitle)
            .setView(dialogContainer)
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Guardar", null)
            .create()

        dialog.setOnShowListener {
            val saveButton =
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)

            saveButton.setOnClickListener {
                val title = titleInput.text.toString().trim()
                val description =
                    descriptionInput.text.toString().trim()

                if (title.isEmpty()) {
                    titleInput.error =
                        "Escribe el título de la tarea"
                    titleInput.requestFocus()
                } else {
                    if (taskToEdit == null) {
                        insertTask(title, description)
                    } else {
                        updateTaskInformation(
                            task = taskToEdit,
                            title = title,
                            description = description
                        )
                    }

                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    private fun insertTask(
        title: String,
        description: String
    ) {
        lifecycleScope.launch {
            val newTask = Task(
                title = title,
                description = description
            )

            withContext(Dispatchers.IO) {
                taskDao.insertTask(newTask)
            }

            Toast.makeText(
                this@MainActivity,
                "Tarea agregada correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateTaskInformation(
        task: Task,
        title: String,
        description: String
    ) {
        lifecycleScope.launch {
            val updatedTask = task.copy(
                title = title,
                description = description
            )

            withContext(Dispatchers.IO) {
                taskDao.updateTask(updatedTask)
            }

            Toast.makeText(
                this@MainActivity,
                "Tarea actualizada correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateTaskCompletion(
        task: Task,
        isCompleted: Boolean
    ) {
        lifecycleScope.launch {
            val updatedTask = task.copy(
                isCompleted = isCompleted
            )

            withContext(Dispatchers.IO) {
                taskDao.updateTask(updatedTask)
            }
        }
    }

    private fun showTaskOptionsDialog(task: Task) {
        val options = arrayOf(
            "Editar tarea",
            "Eliminar tarea"
        )

        AlertDialog.Builder(this)
            .setTitle("Opciones de la tarea")
            .setItems(options) { _, selectedOption ->
                when (selectedOption) {
                    0 -> showTaskFormDialog(task)
                    1 -> showDeleteConfirmation(task)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showDeleteConfirmation(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar tarea")
            .setMessage(
                "¿Deseas eliminar la tarea \"${task.title}\"?"
            )
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Eliminar") { _, _ ->
                deleteTask(task)
            }
            .show()
    }

    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskDao.deleteTask(task)
            }

            Toast.makeText(
                this@MainActivity,
                "Tarea eliminada correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updatePendingTaskCount(
        tasks: List<Task>
    ) {
        val pendingTasks = tasks.count { !it.isCompleted }

        taskCountText.text = when (pendingTasks) {
            0 -> "Todas las tareas están completadas"
            1 -> "1 tarea por completar"
            else -> "$pendingTasks tareas por completar"
        }
    }

    private fun updateTaskStyle(
        checkBox: CheckBox,
        isCompleted: Boolean
    ) {
        if (isCompleted) {
            checkBox.paintFlags =
                checkBox.paintFlags or
                        Paint.STRIKE_THRU_TEXT_FLAG

            checkBox.alpha = 0.55f
        } else {
            checkBox.paintFlags =
                checkBox.paintFlags and
                        Paint.STRIKE_THRU_TEXT_FLAG.inv()

            checkBox.alpha = 1.0f
        }
    }

    private fun createDivider(): View {
        return View(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dpToPx(1)
            )

            setBackgroundColor(
                0xFFE5E7E9.toInt()
            )
        }
    }

    private fun formatTaskText(
        title: String,
        description: String
    ): String {
        return if (description.isEmpty()) {
            title
        } else {
            "$title\n$description"
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (
                dp * resources.displayMetrics.density
                ).toInt()
    }
}