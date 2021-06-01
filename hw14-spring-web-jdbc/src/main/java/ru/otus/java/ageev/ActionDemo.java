package ru.otus.java.ageev;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;
import ru.otus.java.ageev.repository.ClientRepository;
import ru.otus.java.ageev.service.ClientService;

@Component("actionDemo")
public class ActionDemo {
    private static final Logger log = LoggerFactory.getLogger(ActionDemo.class);

//    private final ClientRepository clientRepository;
    private final ClientService clientService;


    @Autowired
    public ActionDemo(ClientService clientService) {
//        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }


    void action() {
        AddressDataSet addressDataSet1 = new AddressDataSet("lllll");
        AddressDataSet addressDataSet2 = new AddressDataSet("lllll");
        AddressDataSet addressDataSet3 = new AddressDataSet("lllll");
        AddressDataSet addressDataSet4 = new AddressDataSet("lllll");
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("12341551");
        PhoneDataSet phoneDataSet2= new PhoneDataSet("12341552");
        PhoneDataSet phoneDataSet3= new PhoneDataSet("12341553");
        PhoneDataSet phoneDataSet4 = new PhoneDataSet("12341554");

        Set set1 = new HashSet();
        set1.add(phoneDataSet1);
        set1.add(phoneDataSet2);

        Set set2 = new HashSet();
        set2.add(phoneDataSet3);
        set2.add(phoneDataSet4);

        var firstClient = clientService.saveClient(new Client("dbServiceFirst" + System.currentTimeMillis(),
                "pass" + System.currentTimeMillis(),
                "name " + System.currentTimeMillis(),addressDataSet1,set1));
        log.info("clientSecondSelected:{}", firstClient);
        var clientSecond = clientService.saveClient(new Client("dbServiceSecond" + System.currentTimeMillis(),
                "pass" + System.currentTimeMillis(),
                "name " + System.currentTimeMillis(), addressDataSet2,set2));
        var clientSecondSelected = clientService.getClient(clientSecond.getId());
//                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);
    }
}
