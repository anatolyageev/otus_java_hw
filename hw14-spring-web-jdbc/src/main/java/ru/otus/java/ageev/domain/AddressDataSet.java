package ru.otus.java.ageev.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@ToString(of = {"street"})
public class AddressDataSet {
    @Id
    private Long id;
    private String street;


    private Client client;

    public AddressDataSet(String street) {
        this.street = street;
    }
}
