package com.example.petcareapp.screens.create_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petcareapp.common.composable.ActionToolbar
import com.example.petcareapp.common.composable.BasicField
import com.example.petcareapp.R.drawable as AppIcon
import com.example.petcareapp.R.string as AppText
import com.example.petcareapp.common.composable.*
import com.example.petcareapp.common.ext.card
import com.example.petcareapp.common.ext.fieldModifier
import com.example.petcareapp.common.ext.spacer
import com.example.petcareapp.common.ext.toolbarActions
import com.example.petcareapp.getTimeWithAmPm
import com.example.petcareapp.model.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
@ExperimentalMaterialApi
fun CreateTaskScreen(
    popUpScreen: () -> Unit,
    viewModel: CreateTaskScreenViewModel = hiltViewModel()
) {
    val task by viewModel.task
    val activity = LocalContext.current as AppCompatActivity

    CreateTaskScreenContent(
        task = task,
        onDoneClick = { viewModel.onDoneClick(popUpScreen) },
        onTaskNameChange = viewModel::onTaskNameChange,
        onPetNameChange = viewModel::onPetNameChange,
        onAssigneeChange = viewModel::onAssigneeChange,
        onDueDateChange = viewModel::onDueDateChange,
        onTimeChange = viewModel::onTimeChange,
        activity = activity
    )
}
@Composable
@ExperimentalMaterialApi
fun CreateTaskScreenContent(
    modifier: Modifier = Modifier,
    task: Task,
    onDoneClick: () -> Unit,
    onTaskNameChange: (String) -> Unit,
    onPetNameChange: (String) -> Unit,
    onAssigneeChange: (String) -> Unit,
    onDueDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    activity: AppCompatActivity?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.create_task,
            modifier = Modifier.toolbarActions(),
            primaryActionIcon = AppIcon.ic_check,
            primaryAction = { onDoneClick() }
        )
        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.task_name, task.taskName, onTaskNameChange, fieldModifier)
        BasicField(AppText.assignee, task.assignee, onAssigneeChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())

        DatePickerButton(task, onDueDateChange)

        TimePickerButton(task, onTimeChange)

        CardSelectors(task, onPetNameChange)

        Spacer(modifier = Modifier.spacer())
    }
}

@Composable
@ExperimentalMaterialApi
private fun CardSelectors(
    task: Task,
    onPetNameChange: (String) -> Unit,
) {
    val options = listOf("Pet 1", "Pet 2", "Pet 3","bob")
    val petSelection = task.petName
    // TODO: replace options with users pets from db
    CardSelector(AppText.pet_name, options, petSelection, Modifier.card()) {
            newValue ->
        onPetNameChange(newValue)
    }
}

@Composable
fun DatePickerButton(
    task: Task,
    onDueDateChange: (Long) -> Unit,
) {
    val months =
        listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec")
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthInd, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthInd)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val formattedDate = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(calendar.time)
            val milliseconds = calendar.timeInMillis

            // Invoke the callback with the long value representing the date
            onDueDateChange(milliseconds)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )

    val btnText = if (task.dueDate.isBlank()) "Choose Date" else task.dueDate
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = btnText,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun TimePickerButton(
    task: Task,
    onTimeChange: (Int, Int) -> Unit
) {
    val context = LocalContext.current
    val cal = Calendar.getInstance()
    val mHour = cal[Calendar.HOUR_OF_DAY]
    val mMinute = cal[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        context, { _, mHour: Int, mMinute: Int ->
            onTimeChange(mHour, mMinute)
        }, mHour, mMinute, false
    )
    val btnText =
        if (task.dueTime == "") "Choose Time" else getTimeWithAmPm(task.dueTime)

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { mTimePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ){
            Text(
                text = btnText,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
