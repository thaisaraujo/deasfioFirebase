package com.example.desafiofireabse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiofireabse.R
import com.example.desafiofireabse.domain.Catalogo
import com.example.desafiofireabse.domain.CatalogoAdapter
import com.example.desafiofireabse.domain.Jogo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CatalogoAdapter.OnItemClickListener {
    private var catalogo = getListJogos()

    private fun getListJogos(): ArrayList<Jogo> {
        return arrayListOf<Jogo>(
            Jogo("MOTAL KOMBAT X", "2018", "ajraorjaorjiajrajraorjaor", 1),
            Jogo("GOD OF WAR", "2015", "ajraorjaorjiajrajraorjaor", 1),
            Jogo("HITMAN", "2016", "ajraorjaorjiajrajraorjaor", 1),
            Jogo("HITMAN", "2019", "ajraorjaorjiajrajraorjaor", 1),
        )
    }

    private var adapter = CatalogoAdapter(catalogo, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCatalogo.adapter = adapter
        rvCatalogo.layoutManager = GridLayoutManager(this,2)
        rvCatalogo.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, JogoActivity::class.java)
        intent.putExtra("imagem", catalogo[position].img)
        intent.putExtra("nome", catalogo[position].nome)
        intent.putExtra("ano", catalogo[position].ano)
        intent.putExtra("descricao", catalogo[position].descricao)
        startActivity(intent)
    }

}