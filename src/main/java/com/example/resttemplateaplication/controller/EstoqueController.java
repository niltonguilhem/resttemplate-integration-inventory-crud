package com.example.resttemplateaplication.controller;

import com.example.resttemplateaplication.model.Estoque;
import com.example.resttemplateaplication.model.EstoqueRequest;
import com.example.resttemplateaplication.model.EstoqueResponse;
import com.example.resttemplateaplication.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping()
    public ResponseEntity<List<EstoqueResponse>> getAllEstoque() {
        List<Estoque> estoqueList = Arrays.asList(service.findAllEstoques());
        List<EstoqueResponse> estoqueResponseList = estoqueList.stream().map(estoque -> new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante())).collect(Collectors.toList());

        return new ResponseEntity<>(estoqueResponseList, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public Estoque get(@PathVariable("id") Long id){
        return service.getEstoqueById(id);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> getId(@PathVariable("id") Long id) {
        Estoque estoque = service.getEstoqueById(id);
        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<List<EstoqueResponse>> getFabricante(@PathVariable("fabricante") String fabricante) {
        List<Estoque> estoqueList = Arrays.asList(service.getEstoqueByFabricante(fabricante));
        List<EstoqueResponse> response = estoqueList.stream().map(estoque -> new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante())).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> postEstoque(@RequestBody EstoqueRequest estoqueRequest){

        Estoque estoque = service.save(new Estoque()
                .withBuilderDescricao(estoqueRequest.getDescricao())
                .withBuilderFabricante(estoqueRequest.getFabricante()));

        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante());

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Estoque> put(@PathVariable("id") Long id, @RequestBody Estoque estoque) {
        Estoque estoqueDto = new Estoque();
        estoqueDto.setDescricao(estoque.getDescricao());
        estoqueDto.setFabricante(estoque.getFabricante());
        estoqueDto.setId(id);

        Estoque e = service.update(estoque,id);

        return new ResponseEntity(e, HttpStatus.ALREADY_REPORTED);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> putEstoque (@PathVariable("id")Long id,
                                                       @RequestBody EstoqueRequest estoqueRequest){


        Estoque estoqueUpdate = new Estoque()
                .withBuilderId(id)
                .withBuilderDescricao(estoqueRequest.getDescricao())
                .withBuilderFabricante(estoqueRequest.getFabricante());


        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoqueUpdate.getId())
                .withBuilderDescricao(estoqueUpdate.getDescricao())
                .withBuilderFabricante(estoqueUpdate.getFabricante());

        Estoque estoqueEntity = service.update(estoqueUpdate,id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

