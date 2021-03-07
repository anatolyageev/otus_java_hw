package otus.java.ageeev.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import otus.java.ageeev.domain.AtmCassette;
import otus.java.ageeev.domain.Denomination;
import otus.java.ageeev.exeption.OutOfMaxCapacityInCassette;


import static otus.java.ageeev.constatns.AppConstance.MAX_CAPACITY_CASSETTE;

public class AtmCassetteRepository {
    private SortedMap<Integer, AtmCassette> cassetteRepository;

    public AtmCassetteRepository(SortedMap<Integer, AtmCassette> cassetteRepository) {
        this.cassetteRepository = cassetteRepository;
    }

    public AtmCassette atmCassette(Denomination denomination) {
        return cassetteRepository.get(denomination.getIntValue());
    }

    public void putCash(Denomination denomination, int noteNumber) {
        AtmCassette atmCassette = atmCassette(denomination);
        if ((atmCassette.getCurrentCapacity() + noteNumber) <= MAX_CAPACITY_CASSETTE) {
            atmCassette.setCurrentCapacity(atmCassette.getCurrentCapacity() + noteNumber);
        } else {
            throw new OutOfMaxCapacityInCassette("Cassette is full call to the bank");
        }
    }

    public Long getSumFromCassette(Denomination denomination, int noteNumber) {
        AtmCassette atmCassette = atmCassette(denomination);
        if ((atmCassette.getCurrentCapacity() - noteNumber) >= 0) {
            atmCassette.setCurrentCapacity(atmCassette.getCurrentCapacity() - noteNumber);
        } else {
            throw new OutOfMaxCapacityInCassette("Cassette is empty call to the bank");
        }
        return (long) (denomination.getIntValue() * noteNumber);
    }


    public Map<Denomination, Integer> getFromRepo(Long sum) {
        Map<Integer, AtmCassette> sortedCassettes = Collections.unmodifiableSortedMap(cassetteRepository);
        Map<Denomination, Integer> result = new HashMap<>();
        var es = sortedCassettes.entrySet();
        for (var entry : es) {
            int noteAmount = 0;
            if (entry.getKey() < sum) {
                noteAmount = (int) (sum/entry.getKey());
                result.put(entry.getValue().getDenomination(), noteAmount);
                sum-=noteAmount*entry.getKey();
            }
        }
        return result;
    }

    public Long getTotalMoneyAmount() {
        Long result = Long.valueOf(0);
        var es = cassetteRepository.entrySet();
        for (var entry : es) {
            result += (long) entry.getKey() * entry.getValue().getCurrentCapacity();
        }
        return result;
    }
}
