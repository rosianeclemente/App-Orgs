package com.example.apporgs.extensions

import android.widget.ImageView
import coil.load
import com.example.apporgs.R

fun ImageView.tentaCarregarImagem(url: String? = null,
fallback: Int = R.drawable.imagem_padrao) {
    load(url){
        fallback(fallback) // caso nao tenha imagem
        error(R.drawable.ic_baseline_priority_high_24)
            placeholder(R.drawable.ic_baseline_vertical_align_bottom_24)

    }
}