package ru.otus.java.ageev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.otus.java.ageev.domain.Client;
import ru.otus.messagesystem.client.ResultDataType;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class ClientList extends ResultDataType {
    private final List<Client> clientList;
}
