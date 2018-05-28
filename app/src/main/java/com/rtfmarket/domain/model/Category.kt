package com.rtfmarket.domain.model

data class Category(val id: String,
                    val name: String,
                    val title: String,
                    val description: String,
                    val products: List<Product>? = null)