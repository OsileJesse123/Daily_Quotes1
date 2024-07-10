package com.hfad.dailyquotes.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.dailyquotes.dao.QuoteDao

import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.getEnglishQuotes
import com.hfad.dailyquotes.getLoveQuotes
import com.hfad.dailyquotes.getMotivationQuotes
import com.hfad.dailyquotes.getPrePopulatedData
import com.hfad.dailyquotes.getSuccessQuotes
import com.hfad.dailyquotes.getAngryQuotes
import com.hfad.dailyquotes.getHappyQuotes
import com.hfad.dailyquotes.getTiredQuotes
import com.hfad.dailyquotes.getSadQuotes
import com.hfad.dailyquotes.getFunnyQuotes
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
                                    Log.e("Insert", "I inserted")
                                    getInstance(context).motDao().insert(getPrePopulatedData())
//                                        getInstance(context).motDao().insert(getLoveQuotes())
//                                        getInstance(context).motDao().insert(getMotivationQuotes())
//                                        getInstance(context).motDao().insert(getSuccessQuotes())
//                                        getInstance(context).motDao().insert(getTiredQuotes())
//                                        getInstance(context).motDao().insert(getSadQuotes())
//                                        getInstance(context).motDao().insert(getFunnyQuotes())
//                                        getInstance(context).motDao().insert(getHappyQuotes())
//                                        getInstance(context).motDao().insert(getAngryQuotes())
//                                        getInstance(context).motDao().insert(getEnglishQuotes())
                                }
                            }
                        }
                        )
                        .build()

                }
            }
            return INSTANCE!!
        }
    }
}