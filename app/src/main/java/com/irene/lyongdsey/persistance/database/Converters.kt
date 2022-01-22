package com.irene.lyongdsey.persistance.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.*

class Converters {
    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}