package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.EditText

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta


class DetalheActivity : AppCompatActivity() {

    lateinit var c: Conta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.layout.activity_detalhe)

        if (intent.hasExtra("contato")) {
            this.c = intent.getSerializableExtra("contato") as Conta

            val nome = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextNome)
            nome.setText(c.descricao)

            val fone = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextTel)
            fone.setText(c.saldoFinal)

            val email = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextEmail)
            email.setText(c.id)

        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.menu.menu_detalhe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        if (id == br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.action_alterarContato) {
            val dao = ContaDAO(this)

            val descricao = (findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextNome) as EditText).text.toString()
            val saldoFinal = (findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextTel) as EditText).text.toString()
            val id = (findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.editTextEmail) as EditText).text.toString()

            c.descricao=descricao
            c.saldoFinal=saldoFinal
            c.id=id

            dao.alterarSaldo(c)
            Log.d("ID: ", Integer.toString(c.getId()))
            Log.d("NOME: ", c.getNome())

            MainActivity.adapter.atualizaContatoAdapter(c)

            Toast.makeText(applicationContext, "Contato alterado", Toast.LENGTH_LONG).show()

            finish()
        }

        if (id == br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.action_excluirContato) {
            val dao = ContaDAO(this)
            dao.excluirContato(c)
            MainActivity.adapter.apagaContatoAdapter(c)

            Toast.makeText(applicationContext, "Contato excluído", Toast.LENGTH_LONG).show()
            finish()

        }


        return super.onOptionsItemSelected(item)
    }


}
