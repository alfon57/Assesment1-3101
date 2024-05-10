package org.d3if3101.assesment1.ui.screen

import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import org.d3if3101.assesment1.database.ToDoDB
import org.d3if3101.assesment1.ui.theme.Assesment1Theme
import org.d3if3101.assesment1.util.ViewModelFactory

const val KEY_ID_TODOLIST = "idToDoList"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null)
{
    val context = LocalContext.current
    val db = ToDoDB.getInstance(context)
    val factory = ViewModelFactory(db.dao)

    val viewModel: DetailViewModel = viewModel(factory = factory)

    var judul by rememberSaveable {
        mutableStateOf("")
    }
    var isi by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf("")
    }

    var prioritas by remember {
        mutableStateOf("")
    }

    var apakahEdit by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(true){
        if (id == null) return@LaunchedEffect
        val data = viewModel.getToDoList(id) ?:return@LaunchedEffect

            judul = data.judul
            isi = data.isi
            status = data.status
            prioritas = data.prioritas


    }

    //Jika bukan edit radio button tidak akan muncul
    if (id == null){
        apakahEdit = false
    }

    //Kalau Status selesai otomatis prioritasnya jadi 1
    if (status == "Selesai"){
        prioritas = "0"
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
                        Text(text = stringResource(id = R.string.tambah_todolist))
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
                    IconButton(onClick = {
                        if (judul == "" || isi == "" || prioritas == ""){
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }else if(id != null && status != "Selesai"){
                            if(prioritas == "0"){
                                Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                                return@IconButton
                            }
                        }
                        if(id == null){
                            viewModel.insert(judul, isi, prioritas)
                        }else{
                            viewModel.update(id, judul, isi, prioritas, status)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null){
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
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
            onStatusChange = {status = it},
            prioritas = prioritas,
            onPrioritasChange = {prioritas = it},
            apakahEdit = apakahEdit,//Jika bukan edit radio button tidak akan muncul
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormToDoList(
    judul: String, onJudulChange: (String) -> Unit,
    isi: String, onIsiChange: (String) -> Unit,
    status: String, onStatusChange: (String) -> Unit,
    prioritas: String, onPrioritasChange: (String) -> Unit,
    apakahEdit: Boolean,//Jika bukan edit radio button tidak akan muncul
    modifier: Modifier
){

    val radioOptions = listOf(
        stringResource(id = R.string.status_selesai),
        stringResource(id = R.string.status_blmselesai),
    )

    val prioritasOptions = listOf(
        stringResource(id = R.string.opsi_1),
        stringResource(id = R.string.opsi_2),
        stringResource(id = R.string.opsi_3),
        stringResource(id = R.string.opsi_4),
        stringResource(id = R.string.opsi_5)
    )


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

            Text(text = "Prioritas")

            Column(
                modifier=Modifier.border(1.dp, Color.Gray)
            ){

                prioritasOptions.forEach{ text ->
                    StatusOption(
                        label = text,
                        isSelected = prioritas == text,
                        modifier = Modifier.selectable(
                            selected = prioritas == text,
                            onClick = {onPrioritasChange(text)},
                            role = Role.RadioButton
                        ), onPrioritasChange
                    )
                }
            }

            Text(text = "Status")

            Column(
                modifier=Modifier.border(1.dp, Color.Gray)
            ){
                radioOptions.forEach{ text ->
                    StatusOption(
                        label = text,
                        isSelected = status == text,
                        modifier = Modifier.selectable(
                            selected = status == text,
                            onClick = {onStatusChange(text)},
                            role = Role.RadioButton
                        ), onStatusChange
                    )
                }
            }
        }
        else{
            Text(text = "Prioritas")

            Column(
                modifier=Modifier.border(1.dp, Color.Gray)
            ){
                
                prioritasOptions.forEach{ text ->
                    StatusOption(
                        label = text,
                        isSelected = prioritas == text,
                        modifier = Modifier.selectable(
                            selected = prioritas == text,
                            onClick = {onPrioritasChange(text)},
                            role = Role.RadioButton
                        ), onPrioritasChange
                    )
                }
            }

        }

    }
}

@Composable
fun StatusOption(label: String, isSelected: Boolean, modifier: Modifier, onRadioChange: (String) -> Unit) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = { onRadioChange(label)})
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit){
    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {expended = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expended = false
                    delete()
                }
            )
        }
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