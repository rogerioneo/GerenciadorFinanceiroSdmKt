package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import android.util.Log
import android.view.View
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    object Constantes{
        val CADASTRA_CONTA_REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contaDao: ContaDAO = ContaDAO(applicationContext)
        val contas: ArrayList<Conta> = contaDao.listaContas()
        atualizaView(contas)
    }

    fun atualizaView(contas: ArrayList<Conta>){
        if (contas.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_conta, NovaContaFragment())
                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_conta, ContaFragment(this, contas))
                .commit()
        }
    }

    fun cadastrarConta(view: View) {
        val intent = Intent(this, CadastraConta::class.java)
        startActivityForResult(intent, Constantes.CADASTRA_CONTA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constantes.CADASTRA_CONTA_REQUEST_CODE &&
            resultCode == AppCompatActivity.RESULT_OK){
            val contaDao: ContaDAO = ContaDAO(applicationContext)
            val contas: ArrayList<Conta> = contaDao.listaContas()
            atualizaView(contas)
        }

    }
}
