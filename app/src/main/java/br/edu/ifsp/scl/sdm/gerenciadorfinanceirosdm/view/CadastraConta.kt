package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import java.util.logging.Logger

class CadastraConta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_conta)

        if (intent.hasExtra("conta")) {
            val conta: Conta = intent.getSerializableExtra("conta") as Conta

            val descricao: EditText = findViewById(R.id.editTextDescricao)
            descricao.setText(conta.descricao)
            val saldoInicial: EditText = findViewById(R.id.editTextSaldoInicial)
            saldoInicial.setText(conta.saldoInicial.toString())
        }
    }

    fun incluirConta(view: View):Conta{
        val descricao: EditText = findViewById(R.id.editTextDescricao)
        val saldoInicial: EditText = findViewById(R.id.editTextSaldoInicial)
        var conta = Conta(0, descricao.text.toString(), saldoInicial.text.toString().toDouble())
        val contaDao = ContaDAO(this)
        conta.id = contaDao.incluir(conta)
        Toast.makeText(
            applicationContext, "Conta " + conta.descricao + " incluida",
            Toast.LENGTH_LONG
        ).show()
        return conta
    }
}
