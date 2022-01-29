package com.savinoordine.medicinecompose.screen.core


interface ScreenState {
    val state: State
}

enum class State {
    IDLE,
    LOADING,
    ERROR,
    SUCCESS
}