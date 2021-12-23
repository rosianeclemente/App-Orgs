package com.example.apporgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.apporgs.R
import com.example.apporgs.database.AppDatabase
import com.example.apporgs.databinding.ActivityDetalhesProdutosBinding
import com.example.apporgs.extensions.formataParaMoedaBrasileira
import com.example.apporgs.extensions.tentaCarregarImagem
import com.example.apporgs.model.Produto


class DetalhesProdutoActivity : AppCompatActivity() {

    private var produto: Produto? = null
    private var produtoId: Long = 0L
    private val binding by lazy { ActivityDetalhesProdutosBinding.inflate(layoutInflater) }
    private val produtoDao by lazy { AppDatabase.getInstance(this).produtoDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()

    }

    override fun onResume() {
        super.onResume()
        produto = produtoDao.getId(produtoId)
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_detalhe_remover -> {
                produto?.let { produtoDao.delete(it) }
                finish()
            }
            R.id.menu_detalhes_editar -> {
                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}