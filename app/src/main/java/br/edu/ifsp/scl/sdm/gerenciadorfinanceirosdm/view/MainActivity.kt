package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contaDao: ContaDAO = ContaDAO(applicationContext)
        val contas: List<Conta> = contaDao.listaContas()
        atualizaView(contas)
    }

    private fun atualizaView(contas: List<Conta>){
        if (contas.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_conta, NovaContaFragment())
                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_conta, ContaFragment(contas))
                .commit()
        }
    }
}
