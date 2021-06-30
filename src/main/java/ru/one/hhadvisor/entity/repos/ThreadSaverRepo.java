package ru.one.hhadvisor.entity.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.one.hhadvisor.output.Vacancy;
@Repository
public interface ThreadSaverRepo extends CrudRepository<Vacancy, Long> {

}
