package com.example.listacompras

data class ItemModel(val name: String, val onRemove:(ItemModel) -> Unit);