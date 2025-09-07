package com.barbosa.wesley.Api_facul.controller;

import com.barbosa.wesley.Api_facul.entities.Tarefa;
import com.barbosa.wesley.Api_facul.repositories.TarefaRepository;
import com.barbosa.wesley.Api_facul.service.TarefaService;
import com.barbosa.wesley.Api_facul.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        List<Tarefa> list = tarefaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id){
        Tarefa tarefa = tarefaService.findById(id);
        return ResponseEntity.ok().body(tarefa);
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa){
        tarefa = tarefaService.insert(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(tarefa);
    }

    @DeleteMapping
    public ResponseEntity<Tarefa> delete(Long id){
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id ,@RequestBody Tarefa tarefa){
        tarefa = tarefaService.update(id, tarefa);
        return ResponseEntity.ok().body(tarefa);
    }



}
