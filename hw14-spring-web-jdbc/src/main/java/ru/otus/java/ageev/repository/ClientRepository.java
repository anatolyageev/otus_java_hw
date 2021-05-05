package ru.otus.java.ageev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.java.ageev.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
