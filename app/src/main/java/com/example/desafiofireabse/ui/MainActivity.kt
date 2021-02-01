package com.example.desafiofireabse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiofireabse.R
import com.example.desafiofireabse.adapter.MainAdapter
import com.example.desafiofireabse.domain.Jogo
import com.example.desafiofireabse.view.MainViewModel
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private val collectionReference: CollectionReference

    val viewModelMain by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(collectionReference) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fbMais.setOnClickListener {
            val intent = Intent(this, AdicionarJogoActivity::class.java)
            startActivity(intent)
        }

        viewModelMain.collectionReference.get()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, JogoActivity::class.java)
        startActivity(intent)
    }

}