package otus.java.ageeev.service;

import otus.java.ageeev.domain.Denomination;
import otus.java.ageeev.exeption.NotEnoughNotesInRepository;
import otus.java.ageeev.exeption.OutOfMaxCapacityInCassette;
import otus.java.ageeev.repository.AtmCassetteRepository;

public class AtmCassetteService {
    private AtmCassetteRepository cassetteRepository;

    public AtmCassetteService(AtmCassetteRepository cassetteRepository) {
        this.cassetteRepository = cassetteRepository;
    }

    private Long getCashDenomination(Denomination denomination, int notesNumber) {
        return cassetteRepository.getSumFromCassette(denomination, notesNumber);
    }

    public Long getSum(Long sum) {
        Long sumWithdraw = Long.valueOf(0);
        if (sum <= cassetteRepository.getTotalMoneyAmount()) {
            for (var entry : cassetteRepository.getFromRepo(sum).entrySet()) {
                sumWithdraw += getCashDenomination(entry.getKey(), entry.getValue());
            }
        } else {
            throw new NotEnoughNotesInRepository("Not enought notes");
        }
        return sumWithdraw;
    }

    public boolean depositCash(Denomination denomination, int notesNum) {
        try {
            cassetteRepository.putCash(denomination, notesNum);
        } catch (OutOfMaxCapacityInCassette ex) {
            return false;
        }
        return true;
    }

    public Long totalMoneyAmount() {
        return cassetteRepository.getTotalMoneyAmount();
    }

}
