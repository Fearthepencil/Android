package com.example.levi9application.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.levi9application.models.Cocktail

@Database(
    entities = [Cocktail::class],
    version = 1,
    exportSchema = false
)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun getDao(): CocktailDAO

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context): CocktailDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocktailDatabase::class.java,
                    "cocktail_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}