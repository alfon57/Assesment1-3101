package org.d3if3101.assesment1.navigation

sealed class Screen (val route: String)
{
    data object Home: Screen("mainscreen")
    data object About: Screen("aboutScreen")
    data object Quiz: Screen("quizScreen")
}