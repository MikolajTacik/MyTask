package com.example.mytask.presentation.util

 sealed class Screen(val route: String) {
     object TaskScreen: Screen("task_screen")
     object AddEditTaskScreen: Screen("add_edit_task_screen")
 }