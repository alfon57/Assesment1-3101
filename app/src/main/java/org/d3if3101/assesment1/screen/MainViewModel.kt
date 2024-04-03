package org.d3if3101.assesment1.screen

import androidx.lifecycle.ViewModel
import org.d3if3101.assesment1.model.Pertanyaan

class MainViewModel : ViewModel()
{
    val data = getDataDummy()

    private fun getDataDummy(): List<Pertanyaan>
    {
        val data = mutableListOf<Pertanyaan>()
        data.add(
            Pertanyaan(
                1.toLong(),
                "Ini Gambar",
                "Ini Soal 1",
                "Ini Jawaban"
            )
        )
        data.add(
            Pertanyaan(
                2.toLong(),
                "Ini Gambar",
                "Ini Soal 2",
                "Ini Jawaban"
            )
        )
        data.add(
            Pertanyaan(
                3.toLong(),
                "Ini Gambar",
                "Ini Soal 3",
                "Ini Jawaban"
            )
        )
        data.add(
            Pertanyaan(
                4.toLong(),
                "Ini Gambar",
                "Ini Soal 4",
                "Ini Jawaban"
            )
        )
        return data
    }
}