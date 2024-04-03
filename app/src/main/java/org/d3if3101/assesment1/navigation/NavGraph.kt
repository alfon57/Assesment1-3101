package org.d3if3101.assesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3101.assesment1.screen.AboutScreen
import org.d3if3101.assesment1.screen.MainScreen
import org.d3if3101.assesment1.screen.QuizScreen


@Composable
fun SetUpNavGraph(navController: NavHostController = rememberNavController())
{
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    )
    {
        composable(route = Screen.Home.route)
        {
            MainScreen(navController)
        }
        composable(route = Screen.About.route)
        {
            AboutScreen(navController)
        }
        composable(route = Screen.Quiz.route)
        {
            QuizScreen(navController)
        }
    }
}
