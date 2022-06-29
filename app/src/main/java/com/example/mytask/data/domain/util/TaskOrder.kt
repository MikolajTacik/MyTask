package com.example.mytask.data.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TaskOrder(orderType)
    class Date(orderType: OrderType): TaskOrder(orderType)
    class Color(orderType: OrderType): TaskOrder(orderType)
}