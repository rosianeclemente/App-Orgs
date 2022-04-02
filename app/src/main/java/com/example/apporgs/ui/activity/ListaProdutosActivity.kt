package com.example.apporgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apporgs.database.AppDatabase
import com.example.apporgs.databinding.ActivityListaProdutosActivityBinding
import com.example.apporgs.ui.recyclerView.ListaProdutosAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListaProdutosActivity : AppCompatActivity() {


    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy { ActivityListaProdutosActivityBinding.inflate(layoutInflater) }
    private val produtoDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.produtoDao()
    }
    private val usuarioDao by lazy { AppDatabase.getInstance(this).usuarioDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
        lifecycleScope.launch {
            launch {
                produtoDao.buscaTodos().collect { produtos ->
                    adapter.atualiza(produtos)
                }

                    }
            intent.getStringExtra("CHAVE_USUARIO_ID")?.let { usuarioId ->
                usuarioDao.buscarPorId(usuarioId).collect {
                    Log.i("ListaProdutos", "onCreate: $it")

                }
            }
        }

    }

    //    override fun onResume() {
//        super.onResume()
//
//        lifecycleScope.launch {
//            val produtos = dao.buscaTodos()
//            adapter.atualiza(produtos)
//        }
//
//    }
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
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }

}