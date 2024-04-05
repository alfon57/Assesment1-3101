package org.d3if3101.assesment1.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3101.assesment1.R
import org.d3if3101.assesment1.navigation.Screen
import org.d3if3101.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController)
{
    Scaffold(topBar = {
        TopAppBar(title =
        { Text(text = stringResource(id = R.string.app_name))
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions =
            {
                IconButton(onClick = {navController.navigate(Screen.About.route)})
                {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(id = R.string.tentang_aplikasi),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }
    ) { padding ->
        ScreenContent(Modifier.padding(padding), navController)
    }


}

@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController)
{
    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = stringResource(id = R.string.intro))

        Button(modifier = Modifier.padding(top = 16.dp),
            onClick =
            {
                navController.navigate(Screen.Quiz.route)
            },
        )
        {
            Text(text = stringResource(id = R.string.mulai))
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assesment1Theme {
        MainScreen(rememberNavController())
    }
}