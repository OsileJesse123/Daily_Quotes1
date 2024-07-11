package com.hfad.dailyquotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.dailyquotes.dao.QuoteDao
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.getPrePopulatedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Quotedataclass::class], version = 1, exportSchema = false)
abstract class QuoteDataBase: RoomDatabase() {
    abstract fun motDao(): QuoteDao

    companion object{
        @Volatile
        private var INSTANCE : QuoteDataBase? = null

        fun getInstance(context: Context): QuoteDataBase{
            if (INSTANCE == null) {
                synchronized(QuoteDataBase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        QuoteDataBase::class.java, "quote_database")
                        .addCallback(
                            object :Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                CoroutineScope(Dispatchers.IO).launch {
                                    getInstance(context).motDao().insert(getPrePopulatedData())
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}