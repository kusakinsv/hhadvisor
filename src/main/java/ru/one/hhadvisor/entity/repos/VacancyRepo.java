package ru.one.hhadvisor.entity.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.one.hhadvisor.output.Vacancy;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {

    @Modifying
    @Query(
            value = "truncate table vacancy",
            nativeQuery = true
    )
    void truncateMyTable();
}
