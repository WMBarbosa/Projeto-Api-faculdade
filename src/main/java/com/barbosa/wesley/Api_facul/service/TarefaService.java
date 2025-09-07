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

    public Tarefa update(Long id, Tarefa tarefa){
        try{
            Tarefa entity = tarefaRepository.getReferenceById(id);
            updateData(id, tarefa);
            return tarefaRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Long id, Tarefa tarefa){
        tarefa.setNome(tarefa.getNome());
        tarefa.setDataEntrega(tarefa.getDataEntrega());
        tarefa.setResponsavel(tarefa.getResponsavel());
    }
}
