package com.example.resttemplateaplication.controller;

import com.example.resttemplateaplication.model.Estoque;
import com.example.resttemplateaplication.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping()
    public Estoque[] get(){
        return service.getEstoque();
    }

    @GetMapping("/{id}")
    public Estoque get(@PathVariable("id") Long id){
        return service.getEstoqueById(id);
    }

    @GetMapping("/fabricante/{fabricante}")
    public Estoque get(@PathVariable("fabricante") String fabricante){
        return service.getEstoqueByFabricante(fabricante);
    }

    @PostMapping
    public ResponseEntity<Estoque> post(@RequestBody Estoque estoque){
        Estoque e = service.insert(estoque);

        return new ResponseEntity(e, HttpStatus.CREATED);// O HttpStatus é responsavel pelo 201.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> put(@PathVariable("id") Long id, @RequestBody Estoque estoque) {

        Estoque e = service.update(id);

        return new ResponseEntity(e, HttpStatus.ALREADY_REPORTED);
    }

    @DeleteMapping("/{id}")
    //public Estoque delete(@PathVariable("id")Long id) {return service.delete(id);

    /*public ResponseEntity<Estoque> delete(@PathVariable ("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }*/

    public void delete(@PathVariable("id") Long id) {
        service.delete(id);


    }
}

