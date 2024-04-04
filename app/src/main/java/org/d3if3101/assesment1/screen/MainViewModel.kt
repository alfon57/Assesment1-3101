package org.d3if3101.assesment1.screen

import androidx.lifecycle.ViewModel
import org.d3if3101.assesment1.R
import org.d3if3101.assesment1.model.Soal

class MainViewModel : ViewModel ()
{
    val data = getDataDummy()

    private fun getDataDummy(): List<Soal>
    {
        //Semua variable data ada di file Soal.kt

        val data = mutableListOf<Soal>()

        data.add(Soal
            (1.toLong(),
            R.drawable.lingkaran_,
            "Lingkaran",
            R.string.lingkaran,
            R.string.persegi)
        )
        data.add(Soal
            (2.toLong(),
            R.drawable.segitiga_,
            "Segitiga",
            R.string.segitiga,
            R.string.persegi_panjang)
        )
        return data
    }

}