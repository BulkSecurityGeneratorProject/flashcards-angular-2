package ee.tenman.flashcards.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Card.
 */
@Entity
@Table(name = "card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 300)
    @Column(name = "front", length = 300, nullable = false)
    private String front;

    @NotNull
    @Size(max = 1000)
    @Column(name = "back", length = 1000, nullable = false)
    private String back;

    @NotNull
    @Column(name = "known", nullable = false)
    private Boolean known;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public Card front(String front) {
        this.front = front;
        return this;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public Card back(String back) {
        this.back = back;
        return this;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Boolean isKnown() {
        return known;
    }

    public Card known(Boolean known) {
        this.known = known;
        return this;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        if (card.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Card{" +
            "id=" + id +
            ", front='" + front + "'" +
            ", back='" + back + "'" +
            ", known='" + known + "'" +
            '}';
    }
}
