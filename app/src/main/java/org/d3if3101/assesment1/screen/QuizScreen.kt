package org.d3if3101.assesment1.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3101.assesment1.R
import org.d3if3101.assesment1.model.Soal
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
    val context = LocalContext.current
    //view model untuk ambil data di MainViewModel
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    var nama by rememberSaveable{ mutableStateOf("") }
    var nilai by rememberSaveable{ mutableIntStateOf(0) }
    var keluarinTombol by rememberSaveable {
        mutableStateOf(false)
    }
    var tombolSubmit by rememberSaveable {
        mutableStateOf(true)
    }
    var namaError by rememberSaveable {
        mutableStateOf(false)
    }

    //Kolom Halaman
    Column(modifier = Modifier.fillMaxWidth())
    {
        //Kolom Nama
        Column (modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(value = nama,
                onValueChange = {nama = it},
                label = { Text(text = stringResource(id = R.string.nama))},
                isError = namaError,
                trailingIcon = { IconPicker(isError = namaError, unit = "")},
                supportingText = { ErrorHint(isError = namaError)},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                enabled = tombolSubmit,
                modifier = Modifier.fillMaxWidth()
            )
        }

        //Kolom soal
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            items(data) { soal ->
                ListSoal(soal = soal, keluarinTombol)

                //bikin tombol submit muncul di soal paling terakhir
                if (soal.id.toInt() == data.size)
                {
                    //Tombol submit
                    Button(
                        onClick = {
                            namaError = (nama == "")
                            if (namaError || !soal.apakahDijawab) return@Button
                            // melihat apakah tiap soal ada jawaban benar atau tidak
                            nilai = data.sumOf { if (it.apakahBenar) it.poinSoal else 0 }
                            keluarinTombol = true
                            tombolSubmit = false
                        },
                        enabled = tombolSubmit,
                        modifier = Modifier.padding(16.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.submit))
                    }

                    // Display Button share,reset, dan text total nilai
                    if (keluarinTombol)
                    {
                        Column(
                            modifier = Modifier.padding(bottom = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {

                                // Display total nilai text
                                Text(textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.total_nilai_bagus, nama,nilai, stringResource(id = R.string.app_name)
                                    ),
                                    modifier = Modifier.padding(bottom = 16.dp),
                                    style = MaterialTheme.typography.bodyLarge,

                                )


                            //Tombol Share dan Reset
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp))
                            {
                                //Reset
                                Button(onClick = {nama = ""
                                                 keluarinTombol = false
                                    tombolSubmit = true
                                                 },
                                    contentPadding = PaddingValues(horizontal = 32.dp,
                                        vertical = 16.dp)
                                )
                                {
                                    Text(text = stringResource(id = R.string.reset))
                                }

                                //Share
                                Button(onClick = {
                                    shareData(
                                        context = context,
                                        message = context.getString(R.string.pesan_share,nilai.toString(),context.getString(R.string.app_name).uppercase()
                                        )
                                    )
                                },
                                    contentPadding = PaddingValues(horizontal = 32.dp,
                                        vertical = 16.dp)
                                )
                                {
                                    Text(text = stringResource(id = R.string.share))
                                }
                            }
                        }
                    }
                }
                Divider()
            }
        }
    }
}


//Compose Soal
@Composable
fun ListSoal(soal: Soal, cekSubmit: Boolean) {

    //context untuk jadiin soal.namaGambar string
    val context = LocalContext.current

    // Data Pilihan
    val radioOptions = listOf(
        stringResource(id = soal.jawaban1),
        stringResource(id = soal.jawaban2)
    )

    //Save jawaban
    var jawaban by rememberSaveable {
        mutableStateOf("")
    }

    //Kolom konten
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Soal
        Text(text = stringResource(id = R.string.soal, soal.id))

        // Gambar
        Image(
            painter = painterResource(id = soal.gambar),
            contentDescription = stringResource(id = R.string.gambar, soal.namaGambar),
            modifier = Modifier.size(132.dp)
        )

        // Pilihan
        Row {
            radioOptions.forEach { text ->
                //cek submit itu parameter dari function nya
                //jadi cekSubmit bisa dirubah dari function QuizScreenContent
                //karena function listSoal dipakai disana
                //dia berubah jika button submit di QuizScreenContent ditekan
                //kalau true jadi engga bisa milih lagi
                val isOptionEnabled = !cekSubmit

                Pilihan(
                    label = text,
                    isSelected = jawaban == text,
                    modifier = Modifier
                        .selectable(
                            selected = jawaban == text,
                            onClick = {
                                jawaban = text

                                if (text == context.getString(soal.namaGambar))
                                {
                                    soal.apakahBenar = true
                                    soal.apakahDijawab = true
                                }
                                else {
                                    soal.apakahBenar = false
                                    soal.apakahDijawab = true
                                }
                            },

                            enabled = isOptionEnabled, //Bikin tidak bisa milih kalau udh submit
                            role = Role.RadioButton,
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String)
{
    if (isError)
    {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else{
        Text(text = unit)}
}

@Composable
fun ErrorHint(isError: Boolean)
{
    if(isError)
    {
        Text(text = stringResource(id = R.string.harus_diisi))
    }
}

//Style Radio Button
@Composable
fun Pilihan(label: String, isSelected: Boolean, modifier: Modifier)
{
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
        )
    {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}

private fun shareData(context: Context, message: String)
{
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,message)
    }
    if(shareIntent.resolveActivity(context.packageManager) != null)
    {
        context.startActivity(shareIntent)
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