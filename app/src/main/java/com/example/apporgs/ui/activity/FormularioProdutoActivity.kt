package com.example.apporgs.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import coil.load

import com.example.apporgs.dao.ProdutosDao
import com.example.apporgs.databinding.ActivityFormularioProdutoBinding
import com.example.apporgs.databinding.FormularioImagemBinding
import com.example.apporgs.extensions.tentaCarregarImagem
import com.example.apporgs.model.Produto
import com.example.apporgs.ui.dialog.FormularioImagemDialog

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
        title = "Cadastrar Produto"


        binding.imageView2.setOnClickListener {
          FormularioImagemDialog(this).mostra{imagem ->
                url = imagem
                binding.imageView2.tentaCarregarImagem(url)

          }

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

