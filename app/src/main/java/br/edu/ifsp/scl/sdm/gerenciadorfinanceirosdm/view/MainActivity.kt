package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.utils.Fragments
import java.util.*


class MainActivity : AppCompatActivity() {

    object Constantes{
        val CADASTRA_CONTA_REQUEST_CODE = 0
        val CADASTRA_TRANSACAO_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contaDao = ContaDAO(applicationContext)
        val contas: ArrayList<Conta> = contaDao.listaContas()
        atualizaView(contas)
    }

    fun atualizaView(contas: ArrayList<Conta>){
        if (contas.size == 0)
            updateFragment(NovaContaFragment(),Fragments.NovaConta.tag)
        else
            updateFragment(ContaFragment(this, contas),Fragments.Conta.tag)
    }

    fun cadastrarConta(view: View) {
        val intent = Intent(this, CadastraContaActivity::class.java)
        startActivityForResult(intent, Constantes.CADASTRA_CONTA_REQUEST_CODE)
    }

    fun cadastrarTransacao(view: View) {
        val intent = Intent(this, CadastraTransacaoActivity::class.java)
        startActivityForResult(intent, Constantes.CADASTRA_TRANSACAO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constantes.CADASTRA_CONTA_REQUEST_CODE &&
            resultCode == RESULT_OK){
            val contaDao = ContaDAO(applicationContext)
            val contas: ArrayList<Conta> = contaDao.listaContas()
            atualizaView(contas)
        }
        if (requestCode == Constantes.CADASTRA_TRANSACAO_REQUEST_CODE &&
            resultCode == RESULT_OK){

        }
    }

    fun updateFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment, tag)
        if (fragment is TransacaoFragment)
            transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        if (fragments.size > 1) {
            if (fragments[0] is TransacaoFragment) {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.remove(fragments[0])
                fragmentTransaction.commit()
                val contaDao = ContaDAO(applicationContext)
                val contas: ArrayList<Conta> = contaDao.listaContas()
                atualizaView(contas)
            }
        } else
            super.onBackPressed()
    }
}
