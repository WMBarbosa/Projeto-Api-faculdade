package com.barbosa.wesley.Api_facul.repositories;

import com.barbosa.wesley.Api_facul.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
