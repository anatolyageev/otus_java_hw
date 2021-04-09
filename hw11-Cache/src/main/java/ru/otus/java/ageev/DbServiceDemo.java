package ru.otus.java.ageev;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.java.ageev.cachehw.HwCache;
import ru.otus.java.ageev.cachehw.HwListener;
import ru.otus.java.ageev.cachehw.MyCache;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;
import ru.otus.java.ageev.repositiry.DataTemplateHibernate;
import ru.otus.java.ageev.repositiry.HibernateUtils;
import ru.otus.java.ageev.service.DbServiceClientCashedImpl;
import ru.otus.java.ageev.sessionmanager.TransactionManagerHibernate;

public class DbServiceDemo {
    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, AddressDataSet.class, PhoneDataSet.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);

        var clientTemplate = new DataTemplateHibernate<>(Client.class);

        HwCache<String, Client> clientCache = new MyCache<>();

        HwListener<String, Client> listener = new HwListener<String, Client>() {
            @Override
            public void notify(String key, Client value, String action) {
                log.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };

        clientCache.addListener(listener);

        var dbServiceClient = new DbServiceClientCashedImpl(transactionManager, clientTemplate, clientCache);

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

        Client client3 = new Client();
        client3.setName("client3");
        AddressDataSet addressDataSet3 = new AddressDataSet("street3");
        client3.setAddressDataSet(addressDataSet3);
        List<PhoneDataSet> phoneDataSets3 = new ArrayList<>();
        PhoneDataSet pdThree1 = new PhoneDataSet("123423");
        pdTwo1.setClient(client3);
        phoneDataSets3.add(pdThree1);
        PhoneDataSet pdThree2 = new PhoneDataSet("1234523");
        pdTwo2.setClient(client3);
        phoneDataSets3.add(pdThree2);
        client2.setPhoneDataSets(phoneDataSets3);


        dbServiceClient.saveClient(client1);
        dbServiceClient.getClient(client1.getId());
        dbServiceClient.saveClient(client2);
        dbServiceClient.getClient(client2.getId());
        dbServiceClient.saveClient(client3);
        dbServiceClient.getClient(client3.getId());

    }
}
