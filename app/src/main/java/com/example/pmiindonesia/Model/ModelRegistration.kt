package com.example.pmiindonesia.Model

class ModelRegistration {

    var id : Int = 0

    var name : String = ""
/*        get() = field
        set(value) {
            field = value
        }*/

    var gender : String = ""
/*        get() = field
        set(value) {
            field = value
        }*/

    var email : String = ""
/*        get() = field
        set(value) {
            field = value
        }*/
    var password : String = ""
/*        get() = field
        set(value) {
            field = value
        }*/



    constructor(id:Int,name:String,gender:String,email:String,password:String){
        this.id = id
        this.name = name
        this.gender = gender
        this.email = email
        this.password = password
    }


}