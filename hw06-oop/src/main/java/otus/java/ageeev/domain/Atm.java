package otus.java.ageeev.domain;

import otus.java.ageeev.service.AtmCassetteService;

public class Atm {
    private AtmCassetteService cassetteService;

    public Atm(AtmCassetteService cassetteService) {
        this.cassetteService = cassetteService;
    }

    public Long withdrawCash(Long sum){
       return cassetteService.getSum(sum);
    }


}
