package otus.java.ageeev.domain;

import otus.java.ageeev.exeption.OutOfMaxCapacityInCassette;
import static otus.java.ageeev.constatns.AppConstance.MAX_CAPACITY_CASSETTE;

public class AtmCassette {
    private Denomination denomination;
    private Integer currentCapacity;

    public AtmCassette(Denomination denomination, Integer initialCapacity) {
        this.denomination = denomination;
        initCashAmount(initialCapacity);
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        initCashAmount(currentCapacity);
    }

    private void initCashAmount(Integer initialCapacity) {
        if(initialCapacity <= MAX_CAPACITY_CASSETTE) {
            this.currentCapacity = initialCapacity;
        }
        else {
            throw new OutOfMaxCapacityInCassette("Initializing cassette field due to maximum capacity was overcome");
        }
    }
}
