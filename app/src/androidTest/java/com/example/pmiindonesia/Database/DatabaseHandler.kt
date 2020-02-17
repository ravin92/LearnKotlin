package com.example.pmiindonesia.Database

import Model.ModelRegistration
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "RavDatabase"
        private val TABLE_CONTACTS = "RegisTable"
        private val KEY_NAME = "name"
        private val KEY_GENDER = "gender"
        private val KEY_EMAIL = "email"
        private val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
        + KEY_NAME + " INTEGER PRIMARY KEY," + KEY_GENDER + " TEXT,"
        + KEY_EMAIL + " TEXT" + KEY_PASSWORD + "TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)


    }

    fun addRegistration(rgs: ModelRegistration):Any{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, rgs.name)
        contentValues.put(KEY_GENDER, rgs.gender) // EmpModelClass Name
        contentValues.put(KEY_EMAIL,rgs.email ) // EmpModelClass Phone
        contentValues.put(KEY_PASSWORD,rgs.password)
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun viewEmployee():List<ModelRegistration>{
        val rgsList:ArrayList<ModelRegistration> = ArrayList<ModelRegistration>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        var userGender: String
        var userEmail: String
        var userPassword : String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userGender = cursor.getString(cursor.getColumnIndex("gender"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                userPassword = cursor.getString(cursor.getColumnIndex("password"))
                val rgs = ModelRegistration()
                //val rgs= ModelRegistration(userName = userName, userGender = userGender, userEmail = userEmail,userPassword = userPassword)
                rgsList.add(rgs)
            } while (cursor.moveToNext())
        }
        return rgsList
    }
    //method to update data
    fun updateEmployee(rgs: ModelRegistration): Any {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, rgs.name) // EmpModelClass Name
        contentValues.put(KEY_GENDER,rgs.gender)
        contentValues.put(KEY_EMAIL,rgs.email ) // EmpModelClass Email
        contentValues.put(KEY_PASSWORD,rgs.password)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"name="+rgs.name,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteEmployee(rgs: ModelRegistration):Any{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, rgs.name) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"name="+rgs.name,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}