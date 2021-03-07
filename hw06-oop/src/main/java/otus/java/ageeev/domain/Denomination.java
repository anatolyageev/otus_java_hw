package otus.java.ageeev.domain;

public enum Denomination {
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final Integer value;

   Denomination(Integer value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
