package com.viveksharma.pokedex_new.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_table")
@TypeConverters(GsonTypeConverter::class)
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val num: String,
    val name: String,
    val height: String,
    val weight: String,
    val img: String,
    val candy: String,
    @SerializedName("spawn_time")
    val spawnTime: String,
    val type: List<String>,
    val weaknesses: List<String>,
    @SerializedName("next_evolution")
    val nextEvolution: List<NextEvolution>?,
    @SerializedName("prev_evolution")
    val prevEvolution: List<PrevEvolution>?
)

data class NextEvolution(
    val name: String
)

data class PrevEvolution(
    val name: String
)