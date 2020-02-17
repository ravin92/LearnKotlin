package com.example.pmiindonesia.Database

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pmiindonesia.Model.ModelRegistration
import com.example.pmiindonesia.R

class ListAdapter(private val context: Activity,private val modelRegistration: ModelRegistration)
    : ArrayAdapter<String>(context, R.layout.custom_list) {

    //private var List<ModelRegistration> listmodelRegis

    override fun getView(position: Int, view: View?, parent: ViewGroup):View{
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list,null,true)


        val name = rowView.findViewById(R.id.tv_cl_name) as TextView
        val gender = rowView.findViewById(R.id.tv_cl_gender) as TextView
        val email = rowView.findViewById(R.id.tv_cl_email) as TextView
        val password = rowView.findViewById(R.id.tv_cl_password) as TextView


        /*name.text = "name : ${name[position]}"
        gender.text = "gender : ${gender[position]}"
        email.text ="email : ${email[position]}"
        password.text = "password : ${password[position]}"*/
        return rowView
    }
}
