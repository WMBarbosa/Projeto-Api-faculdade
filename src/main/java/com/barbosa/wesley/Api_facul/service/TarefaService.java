package com.barbosa.wesley.Api_facul.service;

import com.barbosa.wesley.Api_facul.entities.Tarefa;
import com.barbosa.wesley.Api_facul.repositories.TarefaRepository;
import com.barbosa.wesley.Api_facul.service.exceptions.DatabaseException;
import com.barbosa.wesley.Api_facul.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll(){;
        return tarefaRepository.findAll();
    }

    public Tarefa findById(Long id){
        return tarefaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Tarefa insert(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public void delete(Long id){
        try{
            tarefaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }catch (EntityNotFoundException e){
            throw new DatabaseException("Tarefa não encontrada");
        }
    }

    public Tarefa update(Long id, Tarefa tarefa) {
        Tarefa entity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(entity, tarefa);
        return tarefaRepository.save(entity);
    }

    private void updateData(Tarefa entity, Tarefa tarefa) {
        entity.setNome(tarefa.getNome());
        entity.setDataEntrega(tarefa.getDataEntrega());
        entity.setResponsavel(tarefa.getResponsavel());
    }
}
