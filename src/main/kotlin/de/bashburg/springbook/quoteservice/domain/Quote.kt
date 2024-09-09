package de.bashburg.springbook.quoteservice.domain

data class Quote(
    val content: String,
    val author: String,
    val genre: Genre
)
