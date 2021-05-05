package ru.otus.java.ageev.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@ToString(of = {"number"})
public class PhoneDataSet {
    @Id
    private Long id;
    private String number;
    private Client client;

    public PhoneDataSet(String number) {
        this.number = number;
    }
}
