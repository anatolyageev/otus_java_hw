package ru.otus.java.ageev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.java.ageev.domain.Client;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String login;
    private String password;
    private String name;

    public Client getClient() {
        return new Client(login, password, name);
    }
}
