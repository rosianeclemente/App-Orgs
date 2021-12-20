package com.example.apporgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.apporgs.databinding.FormularioImagemBinding
import com.example.apporgs.extensions.tentaCarregarImagem


class FormularioImagemDialog(private val context: Context) {
    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageview.tentaCarregarImagem(it)
                formularioImagemUrl.setText(it)
            }
            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregarImagem(url)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->


                }
                .show()
        }


    }


}

