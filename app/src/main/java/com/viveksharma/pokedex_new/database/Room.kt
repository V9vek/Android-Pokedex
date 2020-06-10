package com.viveksharma.pokedex_new.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
@TypeConverters(GsonTypeConverter::class)
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonList: List<Pokemon>)

    @Query("select * from pokemon_table")
    fun getPokemonList(): LiveData<List<Pokemon>>

    @Query("select * from pokemon_table where id = :pokemonId")
    fun getPokemonDetails(pokemonId: Int): LiveData<Pokemon>
}


@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(GsonTypeConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
}

private lateinit var INSTANCE: PokemonDatabase

fun getDatabase(context: Context): PokemonDatabase {
    synchronized(PokemonDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "videos_database"
            ).build()
        }
    }

    return INSTANCE
}