package org.d3if3101.assesment1.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3101.assesment1.R
import org.d3if3101.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavHostController)
{
    Scaffold(topBar = {
        TopAppBar(title =
        { Text(text = stringResource(id = R.string.app_name))
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            navigationIcon =
            {
                IconButton(onClick = {navController.popBackStack()})
                {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.kembali),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }
    ) { padding ->
        QuizScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun QuizScreenContent(modifier: Modifier)
{
    var nama by remember{ mutableStateOf("") }
    val radioOptions = listOf(
        stringResource(id = R.string.lingkaran),
        stringResource(id = R.string.segitiga)
    )

    var jawaban1 by remember {
        mutableStateOf(radioOptions[0])
    }

    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        //spacedBy untuk kasi space bagi setiap komponen di dalam kolom
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(value = nama,
            onValueChange = {nama = it},
            label = { Text(text = stringResource(id = R.string.nama))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row (modifier = Modifier.padding(top = 20.dp))
        {
            radioOptions.forEach{text ->
                Pilihan1(label = text,
                    isSelected = jawaban1 == text,
                    modifier = Modifier
                        .selectable(
                            selected = jawaban1 == text,
                            onClick = { jawaban1 = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Button(onClick = {},
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        )
        {
            Text(text = stringResource(id = R.string.submit))
        }
    }
}

@Composable
fun Pilihan1(label: String, isSelected: Boolean, modifier: Modifier)
{
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
        )
    {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun QuizScreenPreview()
{
    Assesment1Theme {
        QuizScreen(rememberNavController())
    }
}