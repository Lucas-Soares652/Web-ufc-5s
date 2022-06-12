package com.example.praticaspring2.repositories;

import com.example.praticaspring2.entities.Date;
import org.springframework.data.repository.CrudRepository;

public interface DateRepository extends CrudRepository<Date, Long> {
    Date findByHorario(java.util.Date horario);
}
