package com.example.firestore_demo

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firestore_demo.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val TAG = "firestore"

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Firebase.firestore

        val nametxt = binding.edtname.text.toString()
        val statetxt = binding.edtstate.text.toString()
        val countrytxt = binding.edtcountry.text.toString()

        val city = hashMapOf(
            "name" to nametxt,
            "state" to statetxt,
            "country" to countrytxt,
        )

        binding.submitbtn.setOnClickListener {
            db.collection("cities")
                .add(city)
                .addOnSuccessListener { Toast.makeText(this,"Database created on firebase", Toast.LENGTH_LONG).show() }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }

    }
}