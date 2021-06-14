package ru.otus.java.ageev.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import ru.otus.messagesystem.client.ResultDataType;

@ToString(of = {"street"})
@Table("addressdataset")
@Getter
public class AddressDataSet extends ResultDataType {
    @Id
    private Long id;
    private String street;
    private final Long clientId;

    public AddressDataSet(String street) {
        this.street = street;
        this.clientId = null;
    }

    @PersistenceConstructor
    public AddressDataSet(Long id, String street, Long clientId) {
        this.id = id;
        this.street = street;
        this.clientId = clientId;
    }
}
