package de.bashburg.springbook.quoteservice.web

import de.bashburg.springbook.quoteservice.domain.Genre
import de.bashburg.springbook.quoteservice.domain.Quote
import de.bashburg.springbook.quoteservice.domain.QuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
class QuoteController(private val quoteService: QuoteService) {

    @get:GetMapping("/quotes")
    val allQuotes: Flux<Quote>
        get() = quoteService.getAllQuotes()

    @get:GetMapping("/quotes/random")
    val randomQuote: Mono<Quote>
        get() = quoteService.getRandomQuote()

    @GetMapping("/quotes/random/{genre}")
    fun getRandomQuote(@PathVariable genre: Genre): Mono<Quote> {
        return quoteService.getRandomQuoteByGenre(genre)
    }
}
