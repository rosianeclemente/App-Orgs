package com.example.apporgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.apporgs.database.AppDatabase
import com.example.apporgs.databinding.ActivityFormularioCadastroUsuarioBinding
import com.example.apporgs.model.Usuario
import kotlinx.coroutines.launch
import java.lang.Exception

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }
    private val dao by lazy { AppDatabase.getInstance(this).usuarioDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }
    private fun configuraBotaoCadastrar(){
        binding.buttonConfirmar.setOnClickListener {
            val novoUsuario = criaUsuario()
            lifecycleScope.launch {
                try {
                    dao.salva(novoUsuario)
                    finish()
                }catch (e: Exception){
                    Toast.makeText(this@FormularioCadastroUsuarioActivity,
                        "Falha ao cadastrar usuario", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private  fun criaUsuario(): Usuario{
        val usuario = binding.tilNomeUsuario.editText.toString()
        val nome = binding.tilNome.editText.toString()
        val senha = binding.tilSenha.editText.toString()
        return Usuario(usuario, nome, senha)
    }
}