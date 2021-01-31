package com.example.desafiofireabse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiofireabse.R
import kotlinx.android.synthetic.main.activity_jogo.*

class JogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        //Click back
        ivBack.setOnClickListener {
            finish()
        }

        //Click edit
        fbEditJogo.setOnClickListener {
            val intent = Intent(this, AdicionarJogoActivity::class.java)
//            intent.putExtra("imagem", catalogo[position].img)
//            intent.putExtra("nome", catalogo[position].nome)
//            intent.putExtra("ano", catalogo[position].ano)
//            intent.putExtra("descricao", catalogo[position].descricao)
            startActivity(intent)
        }

    }
}