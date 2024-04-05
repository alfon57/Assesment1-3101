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
            "1",
            R.drawable.lingkaran_,//gambar
            R.string.lingkaran,//nama gambar
            R.string.persegi,// opsi1
            R.string.lingkaran,// opsi 2
                    false,//Apakah benar
                    1,
                    false//poinSoal
                    )
        )
        data.add(Soal
            (2.toLong(),//id,
            "2",
            R.drawable.segitiga_,//gambar
            R.string.segitiga,//nama gambar
            R.string.segitiga,// opsi1
            R.string.lingkaran,// opsi 2
                    false,//Apakah benar
                    1,
                    false//poinSoal
                    )
        )
        data.add(Soal
            (3.toLong(),//id,
            "3",
            R.drawable.persegi_,//gambar
            R.string.persegi,//nama gambar
            R.string.persegi_panjang,// opsi1
            R.string.persegi,// opsi 2
                    false,//Apakah benar
                    1,
                    false//poinSoal
                    )
        )
        data.add(Soal
            (4.toLong(),//id,
            "4",
            R.drawable.persegi_panjang_,//gambar
            R.string.persegi_panjang,//nama gambar
            R.string.persegi_panjang,// opsi1
            R.string.persegi,// opsi 2
                    false,//Apakah benar
                    1,
                    false//poinSoal
                    )
        )

        return data
    }

}