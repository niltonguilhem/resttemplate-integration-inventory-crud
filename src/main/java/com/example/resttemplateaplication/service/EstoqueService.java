package com.example.resttemplateaplication.service;

import com.example.resttemplateaplication.config.RestTemplateIntegration;
import com.example.resttemplateaplication.model.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstoqueService {

    @Autowired

    private RestTemplateIntegration restTemplate;
    @Value("${inventory-crud.host}")
    private String host;
    @Value("${inventory-crud.path}")
    private String path;
    @Value("${inventory-crud.path-fabricante}")
    private String pathFabricante;

    public List<Estoque> findAllEstoques(){
        List<Estoque> estoque = restTemplate.exchange(host+path,HttpMethod.GET,null,new ParameterizedTypeReference<List<Estoque>>(){}).getBody();
        return estoque;
    }

    public Estoque getEstoqueById(Long id){
        Estoque estoque = restTemplate.getForObject(host+path+id,Estoque.class);
        return estoque;
    }

    public List<Estoque> getEstoqueByFabricante(String fabricante){
        List<Estoque> estoque = restTemplate.exchange(host+pathFabricante+fabricante,HttpMethod.GET,null,new ParameterizedTypeReference<List<Estoque>>(){}).getBody();
        return estoque;
    }

    public Estoque save(Estoque estoque){
        ResponseEntity<Estoque> estoqueEntity =
                restTemplate.postForEntity(host+path,estoque,Estoque.class);
        return estoqueEntity.getBody();
    }

    public Estoque update(Estoque estoque, Long id) {
        HttpEntity requesEntity = new HttpEntity<>(estoque);
        ResponseEntity<Estoque> estoqueEntity =
                restTemplate.exchange(host+path+id,HttpMethod.PUT,requesEntity,Estoque.class);
        return estoqueEntity.getBody();

    }

    public void delete(Long id){
       ResponseEntity<Estoque> estoqueEntity = restTemplate.exchange(host+path+id,
                HttpMethod.DELETE,null,Estoque.class);

    }
}
