package ee.tenman.flashcards.web.rest;

import com.codahale.metrics.annotation.Timed;
import ee.tenman.flashcards.domain.Card;
import ee.tenman.flashcards.service.CardService;
import ee.tenman.flashcards.web.rest.util.HeaderUtil;
import ee.tenman.flashcards.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Card.
 */
@RestController
@RequestMapping("/api")
public class CardResource {

    private final Logger log = LoggerFactory.getLogger(CardResource.class);

    private static final String ENTITY_NAME = "card";
        
    private final CardService cardService;

    public CardResource(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * POST  /cards : Create a new card.
     *
     * @param card the card to create
     * @return the ResponseEntity with status 201 (Created) and with body the new card, or with status 400 (Bad Request) if the card has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cards")
    @Timed
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) throws URISyntaxException {
        log.debug("REST request to save Card : {}", card);
        if (card.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new card cannot already have an ID")).body(null);
        }
        Card result = cardService.save(card);
        return ResponseEntity.created(new URI("/api/cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cards : Updates an existing card.
     *
     * @param card the card to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated card,
     * or with status 400 (Bad Request) if the card is not valid,
     * or with status 500 (Internal Server Error) if the card couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cards")
    @Timed
    public ResponseEntity<Card> updateCard(@Valid @RequestBody Card card) throws URISyntaxException {
        log.debug("REST request to update Card : {}", card);
        if (card.getId() == null) {
            return createCard(card);
        }
        Card result = cardService.save(card);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, card.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cards : get all the cards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cards in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cards")
    @Timed
    public ResponseEntity<List<Card>> getAllCards(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Cards");
        Page<Card> page = cardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cards/:id : get the "id" card.
     *
     * @param id the id of the card to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the card, or with status 404 (Not Found)
     */
    @GetMapping("/cards/{id}")
    @Timed
    public ResponseEntity<Card> getCard(@PathVariable Long id) {
        log.debug("REST request to get Card : {}", id);
        Card card = cardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(card));
    }

    /**
     * DELETE  /cards/:id : delete the "id" card.
     *
     * @param id the id of the card to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cards/{id}")
    @Timed
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        log.debug("REST request to delete Card : {}", id);
        cardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
