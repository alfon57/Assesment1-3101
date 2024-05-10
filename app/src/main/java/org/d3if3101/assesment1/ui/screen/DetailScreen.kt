package org.d3if3101.assesment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3101.assesment1.R
import org.d3if3101.assesment1.ui.theme.Assesment1Theme

const val KEY_ID_TODOLIST = "idToDoList"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null)
{
    val viewModel: DetailViewModel = viewModel()

    var judul by remember {
        mutableStateOf("")
    }
    var isi by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf("")
    }

    var prioritas by remember {
        mutableIntStateOf(0)
    }

    var apakahEdit by remember {
        mutableStateOf(true)
    }

    if(id != null) {
        val data = viewModel.getToDoList(id)

        if (data != null) {
            judul = data.judul
            isi = data.isi
            status = data.status
            prioritas = data.prioritas
        }

    }

    //Jika bukan edit radio button tidak akan muncul
    if (id == null){
        apakahEdit = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if(id == null){
                        Text(text = stringResource(id = R.string.tambah_data))
                    }
                    else{
                        Text(text = stringResource(id = R.string.edit_data))
                    }}
                , colors = TopAppBarDefaults.mediumTopAppBarColors(
                    //Warna Bar
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    //Warna Title
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) {padding ->
        FormToDoList(
            judul = judul,
            onJudulChange = {judul = it},
            isi = isi,
            onIsiChange = {isi = it},
            status = status,
            prioritas = prioritas,
            apakahEdit = apakahEdit,//Jika bukan edit radio button tidak akan muncul
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormToDoList(
    judul: String, onJudulChange: (String) -> Unit,
    isi: String, onIsiChange: (String) -> Unit,
    status: String,
    prioritas: Int,
    apakahEdit: Boolean,//Jika bukan edit radio button tidak akan muncul
    modifier: Modifier
){

    val radioOptions = listOf(
        stringResource(id = R.string.status_selesai),
    )

    val prioritasOptions = listOf(
        1,
        2,
        3,
        4,
        5,
    )

    var statusBaru by remember {
        mutableStateOf(status)
    }

    var prioritasBaru by remember {
        mutableIntStateOf(prioritas)
    }


    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value =judul,
            onValueChange ={onJudulChange(it)},
            label = { Text(text = stringResource(id = R.string.judul))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value =isi,
            onValueChange ={onIsiChange(it)},
            label = { Text(text = stringResource(id = R.string.isi))},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        //Jika bukan edit radio button tidak akan muncul
        if (apakahEdit){
            Column(
                modifier=Modifier.border(1.dp, Color.Gray)
            ){
                radioOptions.forEach{ text ->
                    StatusOption(
                        label = text,
                        isSelected = statusBaru == text,
                        modifier = Modifier.selectable(
                            selected = statusBaru == text,
                            onClick = {statusBaru = text},
                            role = Role.RadioButton
                        )
                    )
                }
            }
        }
        else{
            Text(text = "Prioritas")

            Column(
                modifier=Modifier.border(1.dp, Color.Gray)
            ){
                
                prioritasOptions.forEach{ int ->
                    PrioritasOption(
                        label = int.toString(),
                        isSelected = prioritasBaru == int,
                        modifier = Modifier.selectable(
                            selected = prioritasBaru == int,
                            onClick = {prioritasBaru = int},
                            role = Role.RadioButton
                        )
                    )
                }
            }

            statusBaru = "Tidak"
        }

    }
}

@Composable
fun StatusOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun PrioritasOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Assesment1Theme {
        DetailScreen(rememberNavController())
    }
}