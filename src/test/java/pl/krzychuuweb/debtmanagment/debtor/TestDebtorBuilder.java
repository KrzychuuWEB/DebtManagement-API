package pl.krzychuuweb.debtmanagment.debtor;

public class TestDebtorBuilder {

    public static String DEFAULT_FIRSTNAME = "ExampleFirstName";

    private Long id = 1L;
    private String firstName = "ExampleFirstName";
    private String lastName = "ExampleLastName";
    private boolean isEnabled = true;

    public static TestDebtorBuilder newDebtor() {
        return new TestDebtorBuilder();
    }

    public TestDebtorBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    TestDebtorBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    TestDebtorBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    TestDebtorBuilder withIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public TestDebtorBuilder but() {
        return TestDebtorBuilder
                .newDebtor()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withIsEnabled(isEnabled);
    }

    public Debtor build() {
        Debtor debtor = new Debtor();
        debtor.setId(id);
        debtor.setFirstName(firstName);
        debtor.setLastName(lastName);
        debtor.setEnabled(isEnabled);
        return debtor;
    }
}
