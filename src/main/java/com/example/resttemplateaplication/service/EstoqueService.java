package com.example.resttemplateaplication.service;

import com.example.resttemplateaplication.config.RestTemplateIntegration;
import com.example.resttemplateaplication.model.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private RestTemplateIntegration restTemplate;

    public Estoque[] getEstoque(){
        Estoque[] estoque = restTemplate.getForObject("http://localhost:8080/api/v1/estoques",Estoque[].class);
        return estoque;
    }

    public Estoque getEstoqueById(Long id){
        Estoque estoque = restTemplate.getForObject("http://localhost:8080/api/v1/estoques/" +id,Estoque.class);
        return estoque;
    }

    public Estoque getEstoqueByFabricante(String fabricante){
        Estoque estoque = restTemplate.getForObject("http://localhost:8080/api/v1/estoques/fabricante/" +fabricante,Estoque.class);
        return estoque;
    }

    public Estoque insert(Estoque estoque){
        //return restTemplate.postForEntity("http://localhost:8080/api/v1/estoques", estoque, Estoque.class).getBody();
        ResponseEntity<Estoque> estoqueEntity =
                restTemplate.postForEntity("http://localhost:8080/api/v1/estoques",estoque,Estoque.class);
        return estoqueEntity.getBody();
    }

    public Estoque update(Long id) {
        ResponseEntity<Estoque> estoqueEntity = restTemplate.put("http://localhost:8080/api/v1/estoques/");
        return estoqueEntity.getBody();

    }

    public void delete(Long id){
       //Estoque estoque = restTemplate.delete("http://localhost:8080/api/v1/estoques/"+id);


        ResponseEntity<Estoque> estoqueEntity = restTemplate.exchange("http://localhost:8080/api/v1/estoques/fabricante/"+id,
                HttpMethod.DELETE,null,Estoque.class);

    }
}
