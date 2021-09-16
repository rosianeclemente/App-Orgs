package com.example.apporgs.ui.recyclerView

import com.example.apporgs.databinding.ProdutoItemBinding
import com.example.apporgs.model.Produto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apporgs.R
import com.example.apporgs.extensions.tentaCarregarImagem
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = formataMoeda(produto.valor)
            valor.text = valorEmMoeda


//           val visibilidade = if(produto.imagem != null){
//                View.VISIBLE
//            }else{
//                View.GONE
//            }
//            binding.imageView.visibility = visibilidade

            //outra forma de implementação
            binding.imageView.tentaCarregarImagem(produto.imagem)


        }

        private fun formataMoeda(valor: BigDecimal): String {
            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorEmMoeda = formatador.format(valor)
            return valorEmMoeda
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
