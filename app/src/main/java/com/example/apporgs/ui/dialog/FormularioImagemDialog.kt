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
    fun mostra( quandoImagemCarregada: (imagem: String) -> Unit){
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))




        binding.botaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.imageDialog.tentaCarregarImagem(url)

        }
        AlertDialog.Builder(context)

            .setView(binding.root)
            .setPositiveButton("confirmar") {_,_ ->
                val url = binding.formularioImagemUrl.text.toString()
                Log.i("FormularioImagemDialog", "mostra: $url")
                quandoImagemCarregada(url)

            }
            .setNegativeButton("cancelar"){_, _ ->
            }
            .show()
    }


}

