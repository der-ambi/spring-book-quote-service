package de.bashburg.springbook.quoteservice

import de.bashburg.springbook.quoteservice.domain.Genre
import de.bashburg.springbook.quoteservice.domain.Quote
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuoteServiceApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun whenAllQuotesThenReturn() {
        webTestClient.get().uri("/quotes")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBodyList(Quote::class.java)
    }

    @Test
    fun whenRandomQuoteThenReturn() {
        webTestClient.get().uri("/quotes/random")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(Quote::class.java)
    }

    @Test
    fun whenRandomQuoteByGenreThenReturn() {
        webTestClient.get().uri("/quotes/random/FANTASY")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(Quote::class.java)
            .value { quote -> Assertions.assertThat(quote.genre == Genre.FANTASY) }
    }
}
