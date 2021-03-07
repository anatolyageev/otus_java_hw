package otus.java.ageeev.domain;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import otus.java.ageeev.repository.AtmCassetteRepository;
import otus.java.ageeev.service.AtmCassetteService;

public class Context {
    //TODO в конструкторе реализовать инициализацию всех классов как  com.epam.anatolii.ageev.eshop.ComputerShop

    private AtmCassetteRepository atmCassetteRepository;
    private AtmCassetteService atmCassetteService;
    private Atm atm;

    public Context(SortedMap<Integer, AtmCassette> cassetteRepository) {
        this.atmCassetteRepository = new AtmCassetteRepository(cassetteRepository);;
        this.atmCassetteService = new AtmCassetteService(atmCassetteRepository);
        this.atm = new Atm(atmCassetteService);
    }

    public Atm getAtmInstance(){
        return atm;
    }

    public AtmCassetteRepository getAtmCassetteRepository() {
        return atmCassetteRepository;
    }

    public AtmCassetteService getAtmCassetteService() {
        return atmCassetteService;
    }
}
