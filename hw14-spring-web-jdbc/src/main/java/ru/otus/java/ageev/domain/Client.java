package ru.otus.java.ageev.domain;

import javax.annotation.Nonnull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("client")
public class Client implements Cloneable {
    @Id
    private Long id;
    @Nonnull
    private String login;
    @Nonnull
    private String password;
    @Nonnull
    private String name;

//    private AddressDataSet addressDataSet;
//
//    private List<PhoneDataSet> phoneDataSets;
//
//    @Override
//    public Client clone() {
//        return new Client(this.id,this.login, this.password, this.name, this.addressDataSet, this.phoneDataSets);
//    }

    public Client (String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
    }

    @PersistenceConstructor
    public Client (Long id, String login, String password, String name){
        this(login,password,name);
        this.id = id;
    }
}
