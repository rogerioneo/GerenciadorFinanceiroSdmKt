package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta

class CadastraConta() : AppCompatActivity() {

    var conta: Conta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_conta)

        if (intent.hasExtra("conta")) {
            conta = intent.getSerializableExtra("conta") as Conta

            val descricao: EditText = findViewById(R.id.editTextDescricao)
            descricao.setText(conta!!.descricao)
            val saldoInicial: EditText = findViewById(R.id.editTextSaldoInicial)
            saldoInicial.setText(conta!!.saldoInicial.toString())
        }
    }

    fun btnSaveClick(view: View) {
        val descricao: EditText = findViewById(R.id.editTextDescricao)
        val saldoInicial: EditText = findViewById(R.id.editTextSaldoInicial)
        if (conta == null) {
            val novaConta = Conta(0, descricao.text.toString(), saldoInicial.text.toString().toDouble())
            incluirConta(novaConta)
        } else {
            conta?.descricao = descricao.toString()
            conta?.saldoInicial = saldoInicial.text.toString().toDouble()
            alterarConta(this.conta!!)
        }
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun incluirConta(conta: Conta) {
        val contaDao = ContaDAO(this)
        conta.id = contaDao.incluir(conta)
        Toast.makeText(
            applicationContext, "Conta " + conta.descricao + " incluida",
            Toast.LENGTH_LONG
        ).show()
    }

    fun alterarConta(conta: Conta){
        val contaDao = ContaDAO(this)
        contaDao.alterarSaldoInicial(conta)
        Toast.makeText(
            applicationContext, "Conta " + conta.descricao + " incluida",
            Toast.LENGTH_LONG
        ).show()
    }


}
