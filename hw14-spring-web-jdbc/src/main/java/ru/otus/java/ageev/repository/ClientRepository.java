package ru.otus.java.ageev.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.java.ageev.domain.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
