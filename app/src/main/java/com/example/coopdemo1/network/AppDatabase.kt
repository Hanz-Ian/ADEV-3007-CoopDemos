package com.example.coopdemo1.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coopdemo1.model.Character

/**
 * AppDatabase class for managing the Room database.
 * Defines the database configuration and serves as the main access point for the app's data.
 * @entities the list of entities (tables) in the database.
 * @version the version of the database schema.
 * @exportSchema whether to export the database schema for version control.
 */
@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides access to the DAO (Data Access Object) for the Character entity.
     * @return the CharacterDao instance.
     */
    abstract fun characterDao(): CharacterDao

    companion object {
        // Volatile instance to ensure visibility of changes across threads
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Retrieves the singleton instance of the AppDatabase.
         * If the instance does not exist, it creates a new one.
         * @param context the application context.
         * @return the AppDatabase instance.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create a new database instance if it doesn't exist
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Name of the database file
                ).build()
                INSTANCE = instance // Assign the instance to the singleton
                instance // Return the instance
            }
        }
    }
}