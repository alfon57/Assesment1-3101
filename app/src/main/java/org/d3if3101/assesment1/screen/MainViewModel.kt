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
            (1.toLong(),//id
            R.drawable.lingkaran_,//gambar
            R.string.lingkaran,//nama gambar
            R.string.persegi,// opsi1
            R.string.lingkaran,// opsi 2
                    false,//Apakah benar
                    1//poinSoal
                    )
        )
        data.add(Soal
            (2.toLong(),
            R.drawable.segitiga_,
            R.string.segitiga,
            R.string.segitiga,
            R.string.persegi_panjang,
                    false,
                    1
                    )
        )
        return data
    }

}