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
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let{ produtoCarregado ->
            title = "Alterar Produto"
            idProduto = produtoCarregado.id
            url = produtoCarregado.imagem
            binding.activityFormularioProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            binding.activityFormularioProdutoNome.setText(produtoCarregado.nome)
            binding.activityFormularioProdutoDescricao.setText(produtoCarregado.descricao)
            binding.activityFormularioProdutoValor.setText(produtoCarregado.valor.toPlainString())
        }

    }

    private fun configuraBotaoSalvar() {
        val db = AppDatabase.getInstance(this)

        val produtoDao = db.produtoDao()
        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if(idProduto >0){
                produtoDao.update(produtoNovo)
            }
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






