package com.example.pmiindonesia.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pmiindonesia.Model.ModelRegistration
import java.sql.SQLException

class DatabaseHandlerTest(context: Context,name:String?,factory: SQLiteDatabase.CursorFactory?,version: Int) :
    SQLiteOpenHelper(context,DATABSE_NAME,factory,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_REGISTRATION_TABLE = ("CREATE TABLE TABLE_REGIS " +
                "COLUMN_ID INTEGER PRIMARY KEY," +
                "COLUMN_NAME TEXT," +
                "COLUMN_GENDER TEXT," +
                "COLUMN_EMAIL TEXT," +
                "COLUMN_PASSWORD TEXT")
        db.execSQL(CREATE_REGISTRATION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_REGIS)
        onCreate(db)
    }

    //insert
    fun addRegistration(modelRegistration: ModelRegistration){
        val values =ContentValues()
        values.put(COLUMN_ID,modelRegistration.id)
        values.put(COLUMN_NAME,modelRegistration.name)
        values.put(COLUMN_GENDER,modelRegistration.gender)
        values.put(COLUMN_EMAIL,modelRegistration.email)
        values.put(COLUMN_PASSWORD,modelRegistration.password)

        val db = this.writableDatabase
        db.insert(TABLE_REGIS,null, values)
        db.close()
    }

    //view
    fun findRegistration(modelRegistration: String): ModelRegistration?{
        val query = "SELECT * FROM $TABLE_REGIS WHERE $COLUMN_ID = \"$modelRegistration\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query,null)
        var modelRegistration: ModelRegistration? = null

        if (cursor.moveToFirst()){
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val gender = cursor.getString(2)
            val email = cursor.getString(3)
            val password = cursor.getString(4)

            modelRegistration = ModelRegistration(id,name,gender,email,password)
            cursor.close()
        }
        db.close()
        return modelRegistration
    }

    //delete
    fun deleteRegistration(modelRegistration: String): Boolean{
        var result = false

        var queryDelete =
            "SELECT * FROM $TABLE_REGIS WHERE $COLUMN_ID = \"$modelRegistration\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(queryDelete,null)

        if(cursor.moveToFirst()){
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_REGIS, COLUMN_ID + "=?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    private fun checkDb():Boolean{
        var db:SQLiteDatabase? = null
        try {
            db = SQLiteDatabase.openDatabase(dbPath + DATABSE_NAME,null,SQLiteDatabase.OPEN_READONLY)
        }catch (e:SQLException){ }
        return db !=null
    }

    //https://discuss.kotlinlang.org/t/how-to-use-pre-populated-sqlite-database-using-kotlin/8068/3

    //constractor path db
    fun DatabaseHandlerTest(context: Context,databasePath: DatabasePath){
        //path belum kelar
    }

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABSE_NAME = "regisDB.db"
        val TABLE_REGIS = "registration"
        val dbPath = "/Android/data/com.example.pmiindonesia/regisDB.db/"


        val COLUMN_ID ="id"
        val COLUMN_NAME ="name"
        val COLUMN_GENDER ="gender"
        val COLUMN_EMAIL ="email"
        val COLUMN_PASSWORD ="password"
    }

}