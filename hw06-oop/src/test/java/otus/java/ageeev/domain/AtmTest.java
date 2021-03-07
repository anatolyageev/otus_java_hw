package otus.java.ageeev.domain;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class AtmTest {

   private Context context;
   private Atm atm;

   @Before
    public void before(){
       context = new Context(initCassetteRep());
       atm = context.getAtmInstance();
   }

    @Test
   public void withrawSum_SholdBeSuccsess(){
       Long totalCashBefor = context.getAtmCassetteRepository().getTotalMoneyAmount();
       atm.withdrawCash(100_000L);
       Long shouldBeValue = totalCashBefor - 100_000L;
       assertEquals(shouldBeValue,context.getAtmCassetteRepository().getTotalMoneyAmount());
    }



    private SortedMap<Integer, AtmCassette> initCassetteRep(){
        SortedMap<Integer, AtmCassette> cassetteRepository = new TreeMap<>(Comparator.reverseOrder());
        cassetteRepository.put(100,new AtmCassette(Denomination.ONE_HUNDRED,2500));
        cassetteRepository.put(200,new AtmCassette(Denomination.TWO_HUNDRED,2500));
        cassetteRepository.put(500,new AtmCassette(Denomination.FIVE_HUNDRED,2500));
        cassetteRepository.put(1000,new AtmCassette(Denomination.ONE_THOUSAND,2500));
        cassetteRepository.put(2000,new AtmCassette(Denomination.TWO_THOUSAND,2500));
        cassetteRepository.put(5000,new AtmCassette(Denomination.FIVE_THOUSAND,2500));
        return cassetteRepository;
    }
}