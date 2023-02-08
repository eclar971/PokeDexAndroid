package com.example.pokedex.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.JsonPokemon
import com.example.pokedex.pokemon


class HomeViewModel : ViewModel() {
    private val _currentPoke = MutableLiveData<pokemon>()
    val currentPoke: MutableLiveData<pokemon> = _currentPoke
    private val _pokePallet = MutableLiveData<Int>()
    val pokePallet: MutableLiveData<Int> = _pokePallet
}