package ru.one.hhadvisor.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.one.hhadvisor.entity.Vacancy;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {

}
