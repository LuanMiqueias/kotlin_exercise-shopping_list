package com.example.listacompras.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.listacompras.model.ItemModel

@Entity
class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)

fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel {
    return ItemModel(
        id = this.id,
        name = this.name,
        onRemove = onRemove,
    )
}