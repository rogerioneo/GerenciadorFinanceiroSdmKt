package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.R
import android.widget.EditText
import android.view.Menu
import android.view.MenuItem
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta


class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        if (id == R.id.action_salvarContato) {
            val dao = ContaDAO(this)

            val nome = (findViewById(R.id.editTextNome) as EditText).text.toString()
            val fone = (findViewById(R.id.editTextTel) as EditText).text.toString()
            val email = (findViewById(R.id.editTextEmail) as EditText).text.toString()

            val c = Conta(nome, fone, email)

            val idContato = dao.incluirContato(c) as Int
            c.setId(idContato)

            MainActivity.adapter.adicionaContatoAdapter(c)

            Toast.makeText(applicationContext, "Contato inserido", Toast.LENGTH_LONG).show()

            finish()


        }

        return super.onOptionsItemSelected(item)
    }
}
