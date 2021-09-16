package com.example.apporgs.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.imageLoader
import coil.load
import com.example.apporgs.R
import com.example.apporgs.dao.ProdutosDao
import com.example.apporgs.databinding.ActivityFormularioProdutoBinding
import com.example.apporgs.databinding.FormularioImagemBinding
import com.example.apporgs.extensions.tentaCarregarImagem
import com.example.apporgs.model.Produto

import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private  var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()


        binding.imageView2.setOnClickListener {
           val bindinfFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindinfFormularioImagem.botaoCarregar.setOnClickListener {
                val url = bindinfFormularioImagem.formularioImagemUrl.text.toString()
                bindinfFormularioImagem.imageDialog.tentaCarregarImagem(url)

            }
           AlertDialog.Builder(this)

               .setView(bindinfFormularioImagem.root)
               .setPositiveButton("confirmar") {_,_ ->
                   url = bindinfFormularioImagem.formularioImagemUrl.text.toString()
                   binding.imageView2.load(url)
               }
               .setNegativeButton("cancelar"){_, _ ->
               }
               .show()
        }


    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }


    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }



        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url,

        )
    }




}

