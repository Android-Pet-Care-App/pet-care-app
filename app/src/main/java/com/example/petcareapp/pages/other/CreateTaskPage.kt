package com.example.petcareapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskPage(onBack: () -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var chosenPet by remember { mutableStateOf("Choose a Pet") }
    var chosenTask by remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf("") }
    val recurringChecked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TopAppBar(
            title = { Text("Create Task") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back")
                }
            }
        )

        TaskNameInput(taskName) { newTskName -> taskName = newTskName }
        DropDownSelectPet(chosenPet) { newOpt -> chosenPet = newOpt }
        SelectMenuComponent(chosenTask) { newTask -> chosenTask = newTask }
        HeadingTextForCreateTask(heading = "Due")
        Row {
            DatePickerButton(selectedDate)
            Spacer(modifier = Modifier.padding(8.dp))
            TimePickerButton(selectedTime)
        }
        RecurringCheckBox(recurringChecked)

        SubmitButton(onBack)
    }
}

@Composable
fun HeadingTextForCreateTask(heading: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = heading,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun TaskNameInput(taskName: String, onTaskNameChange: (String) -> Unit) {
    HeadingTextForCreateTask("Task Name")
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = taskName,
        onValueChange = { onTaskNameChange(it) },
        placeholder = { Text(text = "e.g. Walk Johnny the Pet") },
    )
}

@Composable
fun DropDownSelectPet(selectedOption: String, changeSelectedOpt: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Pet 1", "Pet 2", "Pet 3")

    HeadingTextForCreateTask("Select Pet")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(34.dp)
            .background(Color("#dde3ea".toColorInt()))
            .wrapContentSize(Alignment.TopStart)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .clickable(onClick = { expanded = true }),
            horizontalArrangement = Arrangement.SpaceBetween, // This ensures space distribution between elements
            verticalAlignment = Alignment.CenterVertically // This centers the elements vertically in the Row
        ) {
            Text(
                text = selectedOption,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "down arrow",
                modifier = Modifier.size(24.dp) // Ensures the Icon has a specific size, making it more likely to be visible
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
            content = {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            // selectedIndex = index
                            changeSelectedOpt(option)
                            expanded = false
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun SelectMenuComponent(selectedOption: String, changeSelectedOpt: (String) -> Unit) {
    val options = listOf("Walk", "Feed", "Change Litter")

    HeadingTextForCreateTask("Select Task")
    Column {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                Button(
                    onClick = { changeSelectedOpt(option) },
                    modifier = Modifier
                        .padding(1.dp),
                    // Change the background color if this option is selected
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) Color.Blue else Color.LightGray
                    )
                ) {
                    Text(
                        text = option,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }


        var inputColor =
            if ((selectedOption !in options) && (selectedOption != "")) Color("#dde3ea".toColorInt()) else Color.Transparent
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(inputColor)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            BasicTextField(
                value = selectedOption,
                onValueChange = {
                    //customTask = it
                    changeSelectedOpt(it)
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DatePickerButton(selectedDate: MutableState<String>) {
    val months =
        listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec")
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthInd, dayOfMonth ->
            selectedDate.value = months[monthInd].plus(" $dayOfMonth, $year")
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    )
    val btnText = if (selectedDate.value == "") "Choose Date" else selectedDate.value
    Button(
        content = { Text(text = btnText, color = Color.Black) },
        onClick = { datePickerDialog.show() },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color("#dde3ea".toColorInt()))
    )
}

@Composable
fun TimePickerButton(selectedTime: MutableState<String>) {
    val context = LocalContext.current
    val cal = Calendar.getInstance()
    val mHour = cal[Calendar.HOUR_OF_DAY]
    val mMinute = cal[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        context, { _, mHour: Int, mMinute: Int ->
            selectedTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )
    val btnText = if (selectedTime.value == "") "Choose Time" else selectedTime.value
    Button(
        content = { Text(text = btnText, color = Color.Black) },
        onClick = { mTimePickerDialog.show() },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color("#dde3ea".toColorInt())),
    )
}

@Composable
fun RecurringCheckBox(recurringChecked: MutableState<Boolean>) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row {
        Checkbox(
            checked = recurringChecked.value,
            onCheckedChange = { recurringChecked.value = it }
        )
        Text(text = "Recurring",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(fontSize = 24.sp)
        )
    }
}

@Composable
fun SubmitButton(goBack: () -> Unit){
    val context = LocalContext.current
    Spacer(modifier = Modifier.padding(4.dp))
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        shape = RoundedCornerShape(4.dp),
        content = {Text(text = "Create Task")},
        onClick = {
            // Toast.makeText(context,"Task Created",Toast.LENGTH_LONG) // y toast not work
            goBack()
        },
    )
}
