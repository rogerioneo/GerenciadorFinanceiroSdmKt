package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.data.ContaDAO
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta

import android.app.SearchManager
import android.content.Intent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.core.content.ContextCompat
import android.widget.Toast
import android.content.Context
import android.graphics.*
import android.view.View
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {

    internal var contas: MutableList<Conta> = ArrayList()
    internal var dao: ContaDAO = ContaDAO(this)
    lateinit var recyclerView: RecyclerView


    companion object {
        lateinit var adapter: ContaAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.layout.activity_main)
        //var toolbar = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.contaview)
        //setSupportActionBar(toolbar)

        dao = ContaDAO(this)

        recyclerView = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.contaview)
        val layout = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layout)

        contas = dao.listaContatos()

        adapter = ContaAdapter(contas)

        recyclerView.setAdapter(adapter)

        adapter.setClickListener(object : ContaAdapter.ItemClickListener() {
            override fun onItemClick(position: Int) {
                val c = adapter.getContasListFiltered().get(position)

                val i = Intent(applicationContext, DetalheActivity::class.java)
                i.putExtra("contato", c)
                startActivityForResult(i, 2)

            }
        })

        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val c = adapter.getContasListFiltered().get(viewHolder.adapterPosition)
                dao.excluirContato(c)
                adapter.apagaContatoAdapter(c)
                Toast.makeText(applicationContext, "Contato apagado", Toast.LENGTH_LONG).show()

            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap
                val p = Paint()

                val itemView = viewHolder.itemView
                val height = itemView.bottom as Float - itemView.top as Float
                val width = height / 3

                p.setColor(ContextCompat.getColor(baseContext, android.R.color.holo_orange_light))

                val background = RectF(
                    itemView.left as Float,
                    itemView.top as Float,
                    dX,
                    itemView.bottom as Float
                )

                c.drawRect(background, p)

                icon = BitmapFactory.decodeResource(resources, android.R.drawable.ic_delete)

                val icon_dest = RectF(
                    itemView.left as Float + width, itemView.top as Float + width,
                    itemView.left as Float + 2 * width, itemView.bottom as Float - width
                )

                c.drawBitmap(icon, null, icon_dest, null)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }


        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val fab = findViewById(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.fab)
        fab.setOnClickListener(object : View.OnClickListener() {
            override fun onClick(view: View) {
                val i = Intent(applicationContext, CadastroActivity::class.java)
                startActivityForResult(i, 1)

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.menu.menu_main, menu)

        val searchView: SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = menu.findItem(br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setMaxWidth(Integer.MAX_VALUE)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.getFilter().filter(query)

                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item)
    }
/*
    companion object {
        internal var adapter: ContaAdapter()
    }*/
}