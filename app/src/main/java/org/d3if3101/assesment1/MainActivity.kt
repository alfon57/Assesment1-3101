package org.d3if3101.assesment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.d3if3101.assesment1.model.BangunDatar
import org.d3if3101.assesment1.navigation.SetUpNavGraph
import org.d3if3101.assesment1.ui.theme.Assesment1Theme

class MainActivity : ComponentActivity() {

    private val data = getData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assesment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetUpNavGraph()
                }
            }
        }
    }

    private fun getData(): List<BangunDatar>
    {
        return listOf(
            BangunDatar(R.drawable.lingkaran_),
            BangunDatar(R.drawable.segitiga_),
            BangunDatar(R.drawable.persegi_),
            BangunDatar(R.drawable.persegi_panjang_),
        )
    }
}

