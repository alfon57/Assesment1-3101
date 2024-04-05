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
            R.drawable.lingkaran_, R.string.lingkaran,
            R.string.persegi,
            R.string.lingkaran)
        )
        data.add(Soal
            (2.toLong(),
            R.drawable.segitiga_, R.string.segitiga,
            R.string.segitiga,
            R.string.persegi_panjang)
        )
        data.add(Soal
            (3.toLong(),
            R.drawable.persegi_, R.string.persegi,
            R.string.persegi,
            R.string.persegi_panjang)
        )
        data.add(Soal
            (4.toLong(),
            R.drawable.persegi_panjang_, R.string.persegi_panjang,
            R.string.segitiga,
            R.string.persegi_panjang)
        )
        return data
    }

}