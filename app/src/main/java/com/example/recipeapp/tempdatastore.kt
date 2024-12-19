package com.example.recipeapp

data class datastoreTemp(val image : Int)

class datastore{
    val list=listOf<datastoreTemp>(
        datastoreTemp(R.drawable.sandwich_ex),
        datastoreTemp(R.drawable.dish1),
        datastoreTemp(R.drawable.dish2),
        datastoreTemp(R.drawable.sandwich_ex),
        datastoreTemp(R.drawable.dish1),
        datastoreTemp(R.drawable.dish2),
    )
}