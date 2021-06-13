package ru.otus.java.ageev.dto;

import lombok.*;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;

import java.util.Collections;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClientMessageDto extends ClientDto {
    private Long id;

    public ClientMessageDto(Client client){
        super(client.getLogin(), client.getPassword(), client.getName(), client.getAddressDataSet().toString(), client.getPhoneDataSets().toString());
        this.id = client.getId();

    }

    @Override
    public Client getClient (){
        return new Client(id,
                super.getLogin()
                ,super.getPassword()
                ,super.getName()
                ,new AddressDataSet(super.getAddress())
                ,new HashSet<>(Collections.singletonList(new PhoneDataSet(super.getPhone())))
        );
    }

    @Override
    public String toString() {
        return "ClientMessageDto{" +
                "id=" + id +
                "} " + super.toString();
    }
}
