package com.example.desafiofireabse.domain

import java.io.Serializable

class Catalogo (val arrayList: ArrayList<Jogo>){

    override fun toString(): String {
        return "Catalogo - $arrayList "
    }
}