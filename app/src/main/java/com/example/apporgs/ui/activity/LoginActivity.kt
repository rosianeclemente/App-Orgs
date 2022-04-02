package com.example.apporgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.lifecycleScope
import com.example.apporgs.database.AppDatabase
import com.example.apporgs.databinding.ActivityLoginBinding
import com.example.apporgs.extensions.vaiPara
import com.example.apporgs.preferences.dataStore
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityLoginBinding.inflate(layoutInflater)}
    private val usuarioDao by lazy {
        AppDatabase.getInstance(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        cinfiguraBotaoEntrar()

    }

    private fun cinfiguraBotaoEntrar() {
       binding.activityLoginBotaoEntrar.setOnClickListener {
           val usuario = binding.activityLoginUsuario.text.toString()
           val senha = binding.activityLoginSenha.text.toString()
       Log.i("LoginActivity", "onCreate: $usuario - $senha")
           lifecycleScope.launch{
               usuarioDao.autentica(usuario, senha)?.let{ usuario ->
                   dataStore.edit{

                   }
                   vaiPara(ListaProdutosActivity::class.java){
                       putExtra("CHAVE_USUARIO_ID", usuario.id)
                   }
               } ?: Toast.makeText(this@LoginActivity, "Falha na autenticação", Toast.LENGTH_LONG).show()
           }

       }
    }



    private fun configuraBotaoCadastrar() {
       binding.activityLoginBotaoCadastrar.setOnClickListener {
           vaiPara(FormularioCadastroUsuarioActivity::class.java)
       }
    }
}