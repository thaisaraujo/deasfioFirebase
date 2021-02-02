package com.example.desafiofireabse

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_adicionar_jogo.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_jogo.*

class AdicionarJogoActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000 //para recuperar a imagem

    private val viewModel by viewModesl<AdicionarJogoViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AdicionarJogoViewModel(cr) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_jogo)
        config()

        fbMais.setOnClickListener {
            getRes()
        }
        btnSaveGame.setOnClickListener {
            if(etNomeJogoAdd.text?.isNotBlank()!! && etCreateJogoAdd.text?.isNotBlank()!! && etDescJogoAdd.text?.isNotBlank()!! ){
                viewModel.enviar(Jogo(etNomeJogoAdd.text.toString(),
                                        etCreateJogoAdd.text.toString(),
                                        etDescJogoAdd.text.toString(),
                                        viewModel.imgJogo.value))

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else{
                Toast.makeText(this, "Preencha todas as informações!", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun config(){
        var numero = (0..100).random()
        var castString = numero.toString()
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        storageReference = FirebaseStorage.getInstance().getReference(castString)
    }

    //ira pegar um recurso
    fun getRes(){
        val intent = Intent()
        intent.type = "image/"  //tipo de arquivo que ira receber
        intent.action = Intent.ACTION_GET_CONTENT //retorno da imagem
        startActivityForResult(Intent.createChooser(intent, "Captura Imagem"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //verificar imagem para envia para firebase
        if(requestCode == CODE_IMG){
            alertDialog.show()
            val uploadFile = data?.data?.let {
                storageReference.putFile(data!!.data!!)
            }
            val task = uploadFile?.continueWithTask{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Imagem carregada com sucesso!", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val downloadUri = task.result
                    val url = downloadUri!!.toString().substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("URL da Imagem", url)
                    alertDialog.dismiss()
                    Picasso.get().load(url).into(fbFoto)
                }
            }
        } else {
            alertDialog.dismiss()
            Toast.makeText(this, "Nenhum imagem foi carregada!", Toast.LENGTH_SHORT).show()

        }
    }


}