package com.example.desafiofireabse.domain

import java.io.Serializable

class Jogo (val nome: String, val ano: String, val descricao: String, val img: Int): Serializable {

    override fun toString(): String {
        return "Jogo(nome='$nome', ano='$ano', descricao='$descricao', img=$img)"
    }

}