package ru.one.hhadvisor.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.one.hhadvisor.entity.Vacancy;

@Repository
public interface ThreadSaverRepo extends CrudRepository<Vacancy, Long> {

}
