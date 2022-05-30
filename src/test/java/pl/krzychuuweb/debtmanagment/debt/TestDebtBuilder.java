package pl.krzychuuweb.debtmanagment.debt;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

import java.math.BigDecimal;

class TestDebtBuilder {

    private Long id = 1L;
    private String name = "ExampleDebtName";
    private String description = "ExampleDebtDescription";
    private BigDecimal price = BigDecimal.valueOf(1.00);
    private boolean isDevoted = false;
    private Debtor debtor;

    public static TestDebtBuilder newDebt() {
        return new TestDebtBuilder();
    }

    TestDebtBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    TestDebtBuilder withName(String name) {
        this.name = name;
        return this;
    }

    TestDebtBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    TestDebtBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    TestDebtBuilder withIsDevoted(boolean isDevoted) {
        this.isDevoted = isDevoted;
        return this;
    }

    TestDebtBuilder withDebtor(Debtor debtor) {
        this.debtor = debtor;
        return this;
    }

    TestDebtBuilder but() {
        return TestDebtBuilder
                .newDebt()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withIsDevoted(isDevoted)
                .withDebtor(debtor)
                ;
    }

    Debt build() {
        Debt debt = new Debt();
        debt.setId(id);
        debt.setName(name);
        debt.setDescription(description);
        debt.setPrice(price);
        debt.setDevoted(isDevoted);
        debt.setDebtor(debtor);

        return debt;
    }
}
