package com.example.pmiindonesia.Model

class ModelRegistration {
    var name : String = ""
        get() = field
        set(value) {
            field = value
        }

    var gender : String = ""
        get() = field
        set(value) {
            field = value
        }

    var email : String = ""
        get() = field
        set(value) {
            field = value
        }
    var password : String = ""
        get() = field
        set(value) {
            field = value
        }



    constructor(name:String,gender:String,email:String,password:String){
        this.name = name
        this.gender = gender
        this.email = email
        this.password = password
    }


}