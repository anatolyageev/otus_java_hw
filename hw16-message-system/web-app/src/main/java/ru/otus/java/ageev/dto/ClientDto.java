package ru.otus.java.ageev.dto;

import java.util.Collections;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String login;
    private String password;
    private String name;
    private String address;
    private String phone;

    public Client getClient (){
       return new Client(login,password,name, new AddressDataSet(address), new HashSet<>(Collections.singletonList(new PhoneDataSet(phone))));
    }
}
