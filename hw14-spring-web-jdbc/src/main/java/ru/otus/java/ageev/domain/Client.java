package ru.otus.java.ageev.domain;

import java.util.Set;
import javax.annotation.Nonnull;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@ToString
@Table("client")
public class Client {
    @Id
    private final Long id;
    @Nonnull
    private final String login;
    @Nonnull
    private final String password;
    @Nonnull
    private final String name;
    @MappedCollection(idColumn = "client_id")
    private AddressDataSet addressDataSet;
    @MappedCollection(idColumn = "client_id")
    private final Set<PhoneDataSet> phoneDataSets;

    public Client(String login, String password, String name, AddressDataSet addressDataSet, Set<PhoneDataSet> phoneDataSets) {
        this.id = null;
        this.login = login;
        this.password = password;
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phoneDataSets = phoneDataSets;
    }

    @PersistenceConstructor
    public Client(Long id, String login, String password, String name, AddressDataSet addressDataSet, Set<PhoneDataSet> phoneDataSets) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phoneDataSets = phoneDataSets;
    }
}
