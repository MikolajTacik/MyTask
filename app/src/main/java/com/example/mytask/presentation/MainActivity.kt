package com.example.mytask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytask.presentation.add_edit_task.components.AddEditTaskScreen
import com.example.mytask.presentation.tasks.TaskScreen
import com.example.mytask.presentation.util.Screen
import com.example.mytask.ui.theme.MyTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaskTheme {
                Surface(
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TaskScreen.route) {
                        composable(route = Screen.TaskScreen.route) {
                            TaskScreen(navController = navController)
                        }
                        composable(route = Screen.AddEditTaskScreen.route + "?taskId={taskId}&taskColor={taskColor}",
                        arguments = listOf(
                            navArgument(
                                name = "taskId"
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "taskColor"
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                            ) {
                            val color = it.arguments?.getInt("taskColor") ?: -1
                            AddEditTaskScreen(navController = navController, taskColor = color)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTaskTheme {

    }
}