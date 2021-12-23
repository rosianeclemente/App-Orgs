package com.example.apporgs.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.apporgs.database.AppDatabase
import com.example.apporgs.databinding.ActivityFormularioProdutoBinding
import com.example.apporgs.extensions.tentaCarregarImagem
import com.example.apporgs.model.Produto
import com.example.apporgs.ui.dialog.FormularioImagemDialog

import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private  var url: String? = null
    private var idProduto = 0L
    private var produtoId: Long = 0L
    private val produtoDao by lazy { AppDatabase.getInstance(this).produtoDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        title = "Cadastrar Produto"
        configuraBotaoSalvar()

        binding.activityFormularioProdutoImagem.setOnClickListener {
          FormularioImagemDialog(this).mostra{imagem ->
                url = imagem
                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
          }
        }
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }
    override fun onResume() {
        super.onResume()
        produtoDao.getId(produtoId)?.let{
            preencheCampos(it)
        }

    }

    private fun preencheCampos(produto: Produto) {
        title = "Alterar Produto"
        idProduto = produto.id
        url = produto.imagem
        binding.activityFormularioProdutoImagem.tentaCarregarImagem(produto.imagem)
        binding.activityFormularioProdutoNome.setText(produto.nome)
        binding.activityFormularioProdutoDescricao.setText(produto.descricao)
        binding.activityFormularioProdutoValor.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {

        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
//            if(idProduto >0){
//                produtoDao.update(produtoNovo)
//            }
//            produtoDao.insert(produtoNovo)
            produtoDao.insert(produtoNovo)
            finish()
        }
    }


    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = idProduto,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }

    }






