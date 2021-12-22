package com.example.apporgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.room.Room
import com.example.apporgs.database.AppDatabase

import com.example.apporgs.databinding.ActivityListaProdutosActivityBinding
import com.example.apporgs.model.Produto
import com.example.apporgs.ui.recyclerView.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity() {

    private val dao = com.example.apporgs.dao.ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaProdutosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

        val db = Room.databaseBuilder(
            this, AppDatabase::class.java, "appOrgs.db"
        ).allowMainThreadQueries()
            .build()
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.getAll())

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        val db = Room.databaseBuilder(
            this, AppDatabase::class.java, "appOrgs.db"
        ).allowMainThreadQueries()
            .build()
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.getAll())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }

}