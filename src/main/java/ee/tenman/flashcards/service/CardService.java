package ee.tenman.flashcards.service;

import ee.tenman.flashcards.domain.Card;
import ee.tenman.flashcards.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Card.
 */
@Service
@Transactional
public class CardService {

    private final Logger log = LoggerFactory.getLogger(CardService.class);
    
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Save a card.
     *
     * @param card the entity to save
     * @return the persisted entity
     */
    public Card save(Card card) {
        log.debug("Request to save Card : {}", card);
        Card result = cardRepository.save(card);
        return result;
    }

    /**
     *  Get all the cards.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Card> findAll(Pageable pageable) {
        log.debug("Request to get all Cards");
        Page<Card> result = cardRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one card by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Card findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        Card card = cardRepository.findOne(id);
        return card;
    }

    /**
     *  Delete the  card by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.delete(id);
    }
}
