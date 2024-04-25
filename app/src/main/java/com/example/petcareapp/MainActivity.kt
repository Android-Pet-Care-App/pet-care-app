package com.example.petcareapp

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.petcareapp.data.tasks.TaskDatabase
import com.example.petcareapp.data.tasks.TaskViewModel

import com.example.petcareapp.data.pets.PetDatabase
import com.example.petcareapp.data.pets.PetViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petcareapp.common.snackbar.SnackbarManager
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
import androidx.navigation.navArgument
import com.example.petcareapp.screens.bottom_navigation.BottomNavigation
import com.example.petcareapp.screens.create_pet.CreatePetScreen
import com.example.petcareapp.screens.login.LoginScreen
import com.example.petcareapp.screens.create_task.CreateTaskScreen
import com.example.petcareapp.screens.home.HomeScreen
import com.example.petcareapp.screens.landing.LandingScreen
import com.example.petcareapp.screens.pets.PetScreen
import com.example.petcareapp.screens.profile.ProfileScreen
import com.example.petcareapp.screens.sign_up.SignUpScreen

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetCareAppTheme {
                PetCareAppUi()
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun PetCareAppUi() {
    PetCareAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()
            Scaffold(
                topBar = { /* Define your top app bar */ },
                bottomBar = {
                    BottomNavigation(openScreen = { route -> appState.navigate(route) })
                },
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
//                        startDestination = EDIT_TASK_SCREEN, // Update with your start destination
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
        HomeScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(PROFILE_SCREEN) {
        ProfileScreen(
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(PET_SCREEN) {
        PetScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(
        route = "$CREATE_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(TASK_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        CreateTaskScreen(
            popUpScreen = { appState.popUp() }
        )
    }

    composable(
        route = "$CREATE_PET_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(PET_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        CreatePetScreen(
            popUpScreen = { appState.popUp() }
        )
    }
}
