package com.example.mytask.data.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
