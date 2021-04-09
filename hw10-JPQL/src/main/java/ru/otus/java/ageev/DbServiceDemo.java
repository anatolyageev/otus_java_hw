package ru.otus.java.ageev;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;
import ru.otus.java.ageev.repositiry.DataTemplateHibernate;
import ru.otus.java.ageev.repositiry.HibernateUtils;
import ru.otus.java.ageev.service.DbServiceClientImpl;
import ru.otus.java.ageev.sessionmanager.TransactionManagerHibernate;

public class DbServiceDemo {
    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, AddressDataSet.class, PhoneDataSet.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);

        var clientTemplate = new DataTemplateHibernate<>(Client.class);

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        Client client1 = new Client();
        client1.setName("client1");
        AddressDataSet addressDataSet1 = new AddressDataSet("street1");
        client1.setAddressDataSet(addressDataSet1);
        List<PhoneDataSet> phoneDataSets = new ArrayList<>();
        PhoneDataSet pdOne1 = new PhoneDataSet("1234");
        pdOne1.setClient(client1);
        phoneDataSets.add(pdOne1);

        client1.setPhoneDataSets(phoneDataSets);

        Client client2 = new Client();
        client2.setName("client2");
        AddressDataSet addressDataSet2 = new AddressDataSet("street2");
        client2.setAddressDataSet(addressDataSet2);
        List<PhoneDataSet> phoneDataSets2 = new ArrayList<>();
        PhoneDataSet pdTwo1 = new PhoneDataSet("12342");
        pdTwo1.setClient(client2);
        phoneDataSets2.add(pdTwo1);
        PhoneDataSet pdTwo2 = new PhoneDataSet("123452");
        pdTwo2.setClient(client2);
        phoneDataSets2.add(pdTwo2);
        client2.setPhoneDataSets(phoneDataSets2);

        dbServiceClient.saveClient(client1);

        var clientSecond = dbServiceClient.saveClient(client2);
        var clientSecondSelected = dbServiceClient.getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);

        log.info("All clients");
        dbServiceClient.findAll().forEach(client -> log.info("client:{}", client));

        Client updateClient = clientSecondSelected;
        updateClient.setName("UpdatedName");
        dbServiceClient.saveClient(updateClient);

        log.info("All clients after update");
        dbServiceClient.findAll().forEach(client -> log.info("client:{}", client));
    }
}
