package com.example.pmiindonesia.Database

import java.io.File

class DatabasePath(){

    val PATH = "/Android/data/com.example.pmiindonesia/regisDB.db/"
     fun getPathData():String?{
        var dbfile = File(PATH)
        if (!dbfile.exists())
            dbfile.mkdirs()
            return PATH
    }
}