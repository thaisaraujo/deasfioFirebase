package com.example.desafiofireabse

import java.io.Serializable

class Jogo (val nome: String, val ano: String, val descricao: String, val img: String?) {
    var id = 0

    override fun toString(): String {
        return "Jogo(nome='$nome', ano='$ano', descricao='$descricao', img=$img, id=$id)"
    }

}