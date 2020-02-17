package com.example.pmiindonesia.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pmiindonesia.Database.Database
import com.example.pmiindonesia.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button
        var btn_next = findViewById(R.id.btn_next) as Button
        var tv_signUp = findViewById(R.id.tv_signUp) as TextView

        btn_reset.setOnClickListener(View.OnClickListener {
            et_user_name.setText("")
            et_password.setText("")
        })

        btn_submit.setOnClickListener(View.OnClickListener {
            val user_name = et_user_name.text
            val password = et_password.text

            val dbHandler =
                Database(this, null)
            dbHandler.addName(com.example.pmiindonesia.Model.ModelUser(user_name.toString()))



            Toast.makeText(this@MainActivity, user_name, Toast.LENGTH_SHORT).show()
        })

        btn_next.setOnClickListener(View.OnClickListener {
            /*  intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)*/
            val dbHandler =
                Database(this, null)
            val cursor = dbHandler.getAllName()
            cursor!!.moveToFirst()
            while (cursor.moveToNext()) {
                val userNameDb = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME))
                cursor.close()
                Toast.makeText(this@MainActivity, userNameDb, Toast.LENGTH_SHORT).show()
            }
        })

        tv_signUp.setOnClickListener(View.OnClickListener {
            intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        })

    }
}


