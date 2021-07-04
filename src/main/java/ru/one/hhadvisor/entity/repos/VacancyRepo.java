package ru.one.hhadvisor.entity.repos;

import org.springframework.data.repository.CrudRepository;
import ru.one.hhadvisor.output.Vacancy;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {


}
