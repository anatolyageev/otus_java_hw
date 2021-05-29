package ru.otus.java.ageev.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Table("phone_data")
@ToString(of = {"number"})
public class PhoneDataSet {
    @Id
    private Long id;
    private String number;
    private Long clientId;

    @PersistenceConstructor
    public PhoneDataSet(Long id, String number, Long clientId) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
    }

    public PhoneDataSet(String number) {
        this(null, number, null);
    }

}
