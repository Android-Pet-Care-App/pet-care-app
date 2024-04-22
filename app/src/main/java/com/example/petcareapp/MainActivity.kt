package com.example.petcareapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.makeitso.common.composable.PermissionDialog
import com.example.makeitso.common.composable.RationaleDialog
import com.example.petcareapp.common.snackbar.SnackbarManager
import com.example.petcareapp.data.TaskData
import com.example.petcareapp.ui.theme.PetCareAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import android.content.res.Resources
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.makeitso.screens.login.LoginScreen
import com.example.petcareapp.screens.home.HomeScreen
import com.example.petcareapp.screens.landing.LandingScreen
import com.example.petcareapp.screens.sign_up.SignUpScreen

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            PetCareAppTheme {
//                val fakeTasks = listOf(
//                    TaskData(
//                        taskName = "Walk Johnny",
//                        petName = "Pet 1",
//                        assignee = "John Doe",
//                        dueDate = "May 15, 2024",
//                        dueTime = "10:00 AM"
//                    ),
//                    TaskData(
//                        taskName = "Feed Fido",
//                        petName = "Pet 2",
//                        assignee = "Jane Smith",
//                        dueDate = "May 16, 2024",
//                        dueTime = "12:00 PM"
//                    ),
//                    TaskData(
//                        taskName = "Clean litter box",
//                        petName = "Pet 3",
//                        assignee = "Alex Johnson",
//                        dueDate = "May 17, 2024",
//                        dueTime = "3:00 PM"
//                    )
//                )
//
//                AppContent(fakeTasks)
//            }
            PetCareAppUi()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(tasks: List<TaskData>) {
    var currPageInd by remember { mutableIntStateOf(1) }
    Scaffold(
        bottomBar = { BottomNavigationBar(currPageInd) { index -> currPageInd = index } }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding), currPageInd, tasks)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyContent(modifier: Modifier = Modifier, pageIndex: Int, tasks: List<TaskData>) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (pageIndex) {
            0 -> ProfilePage()
            1 -> HomePage(tasks = tasks)
            2 -> PetsPage(title = "My Pets")
        }
    }
}

@Composable
fun BottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Profile", "Home", "Pets")
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (index == 0) Icons.Filled.Person
                        else if (index == 1) Icons.Filled.Home
                        else Icons.Filled.ShoppingCart,
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier.padding(24.dp), color = Color.Black)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PetCareAppTheme {
        val fakeTasks = listOf(
            TaskData(
                taskName = "Walk Johnny",
                petName = "Pet 1",
                assignee = "John Doe",
                dueDate = "May 15, 2024",
                dueTime = "10:00 AM"
            ),
            TaskData(
                taskName = "Feed Fido",
                petName = "Pet 2",
                assignee = "Jane Smith",
                dueDate = "May 16, 2024",
                dueTime = "12:00 PM"
            ),
            TaskData(
                taskName = "Clean litter box",
                petName = "Pet 3",
                assignee = "Alex Johnson",
                dueDate = "May 17, 2024",
                dueTime = "3:00 PM"
            )
        )

        AppContent(fakeTasks)
    }
}

// NEW APP STRUCTURE
@Composable
@ExperimentalMaterialApi
fun PetCareAppUi() {
    PetCareAppTheme {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            RequestNotificationPermissionDialog()
//        }

        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold(
                topBar = { /* Define your top app bar */ },
                bottomBar = { /* Define your bottom navigation bar */ },
                floatingActionButton = { /* Define your floating action button */ },
                floatingActionButtonPosition = FabPosition.End,
                snackbarHost = {
                    SnackbarHost(
                        hostState = appState.scaffoldState.snackbarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                        }
                    )
                },
                content = { innerPadding ->
                    // Define your navigation graph within the content area
                    NavHost(
                        navController = appState.navController,
                        startDestination = LANDING_SCREEN, // Update with your start destination
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Add composables defined in your navigation graph
                        petCareAppGraph(appState = appState)
                    }
                }
            )
        }
    }
}
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun RequestNotificationPermissionDialog() {
//    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
//
//    if (!permissionState.status.isGranted) {
//        if (permissionState.status.shouldShowRationale) RationaleDialog()
//        else PermissionDialog { permissionState.launchPermissionRequest() }
//    }
//}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        PetCareAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.petCareAppGraph(appState: PetCareAppState) {
    composable(LANDING_SCREEN) {
        LandingScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(HOME_SCREEN) {
        HomeScreen()
    }

//    composable(STATS_SCREEN) {
//        StatsScreen()
//    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

//    composable(TASKS_SCREEN) { TasksScreen(openScreen = { route -> appState.navigate(route) }) }
//
//    composable(
//        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
//        arguments = listOf(navArgument(TASK_ID) {
//            nullable = true
//            defaultValue = null
//        })
//    ) {
//        EditTaskScreen(
//            popUpScreen = { appState.popUp() }
//        )
//    }
}
