package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "GerenciadorFinanceiro.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ContaDAO.creteTable())
        db.execSQL(ClassificacaoDAO.creteTable())
        db.execSQL(TransacaoDAO.creteTable())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}