package pl.krzychuuweb.debtmanagment.debt;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isDevoted = false;
    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private Debtor debtor;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public boolean isDevoted() {
        return isDevoted;
    }

    public void setDevoted(final boolean devoted) {
        isDevoted = devoted;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(final Debtor debtor) {
        this.debtor = debtor;
    }
}
