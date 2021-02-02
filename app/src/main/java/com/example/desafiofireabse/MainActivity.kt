package com.example.desafiofireabse


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JogoAdapter.OnItemClickListener {
    private lateinit var bind: ActivityMainBinding
    lateinit var adapter: JogoAdapter

    val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(cr) as T
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.retornoJogo.observe(this){

            adapter = JogoAdapter(it, this)
            rvCatalogo.adapter = adapter
            rvCatalogo.layoutManager = GridLayoutManager(this,2)
        }

        viewModel.addJogo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = ActivityMainBinding.inflate(layoutInflater)

        fbMais.setOnClickListener {
            val intent = Intent(this, AdicionarJogoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun jogoClick(position: Int, id: Int, nomeJogo: String, anoJogo: String) {
        var intent = Intent(this, JogoActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("nome", nomeJogo)
        intent.putExtra("ano", anoJogo)
        startActivity(intent)

    }

}