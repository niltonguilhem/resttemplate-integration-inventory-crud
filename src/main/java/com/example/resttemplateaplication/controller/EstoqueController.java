package com.example.resttemplateaplication.controller;

import com.example.resttemplateaplication.model.Estoque;
import com.example.resttemplateaplication.model.EstoqueRequest;
import com.example.resttemplateaplication.model.EstoqueResponse;
import com.example.resttemplateaplication.service.EstoqueService;
import com.example.resttemplateaplication.utils.EstoqueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/estoques")
public class EstoqueController {

    private static final Logger logger = LoggerFactory.getLogger(EstoqueController.class);
    @Autowired
    private EstoqueService service;

    @GetMapping()
    public ResponseEntity<List<EstoqueResponse>> getAllEstoque() {
        logger.info("m=getAllEstoque - status=start");
        List<Estoque> estoqueList = service.findAllEstoques();
        List<EstoqueResponse> estoqueResponseList = estoqueList.stream().map(estoque -> new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante())).collect(Collectors.toList());
        logger.info("m=getAllEstoque - status=finish");
        return new ResponseEntity<>(estoqueResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> getIdEstoque(@PathVariable("id") Long id) {
        logger.info("m=getIdEstoque - status=start " + id);
        Estoque estoque = service.getEstoqueById(id);
        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante());
        logger.info("m=getIdEstoque - status=finish " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<List<EstoqueResponse>> getFabricante(@PathVariable("fabricante") String fabricante) {
        logger.info("m=getFabricante - status=start " + fabricante);
        List<Estoque> estoqueList = service.getEstoqueByFabricante(fabricante);
        List<EstoqueResponse> response = estoqueList.stream().map(estoque -> new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante())).collect(Collectors.toList());
        logger.info("m=getFabricante - status=finish " + fabricante);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> postEstoque(@RequestBody EstoqueRequest estoqueRequest,
                                                       @RequestHeader String partner) throws Exception {
        EstoqueUtils.validatedHeader(partner);
        logger.info("m=postEstoque - status=start " + partner);
        Estoque estoque = service.save(new Estoque()
                .withBuilderDescricao(estoqueRequest.getDescricao())
                .withBuilderFabricante(estoqueRequest.getFabricante()),partner);

        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoque.getId())
                .withBuilderDescricao(estoque.getDescricao())
                .withBuilderFabricante(estoque.getFabricante());
        logger.info("m=postEstoque - status=finish " + partner);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> putEstoque (@PathVariable("id")Long id,
                                                       @RequestBody EstoqueRequest estoqueRequest,
                                                       @RequestHeader String partner) throws Exception {
        EstoqueUtils.validatedHeader(partner);
        logger.info("m=putEstoque - status=start " + id + " " + partner);
        Estoque estoqueUpdate = new Estoque()
                .withBuilderId(id)
                .withBuilderDescricao(estoqueRequest.getDescricao())
                .withBuilderFabricante(estoqueRequest.getFabricante());


        EstoqueResponse response = new EstoqueResponse()
                .withBuilderId(estoqueUpdate.getId())
                .withBuilderDescricao(estoqueUpdate.getDescricao())
                .withBuilderFabricante(estoqueUpdate.getFabricante());

        Estoque estoqueEntity = service.update(estoqueUpdate,id,partner);
        logger.info("m=putEstoque - status=finish " + id + " " + partner);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("m=delete - status=start " + id);
        service.delete(id);
        logger.info("m=delete - status=finish " + id);
    }
}

