package com.example.resttemplateaplication.service;

import com.example.resttemplateaplication.config.RestTemplateIntegration;
import com.example.resttemplateaplication.model.Estoque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;


@Service
public class EstoqueService {

    private static final Logger logger = LoggerFactory.getLogger(EstoqueService.class);

    @Autowired

    private RestTemplateIntegration restTemplate;
    @Value("${inventory-crud.host}")
    private String host;
    @Value("${inventory-crud.path}")
    private String path;
    @Value("${inventory-crud.path-fabricante}")
    private String pathFabricante;

    public List<Estoque> findAllEstoques(){
        logger.info("m=findAllEstoque - status=start");
        List<Estoque> estoque = restTemplate
                .exchange(host+path,HttpMethod.GET,null,new ParameterizedTypeReference<List<Estoque>>(){}).getBody();
        logger.info("m=findAllEstoque - status=finish");
        return estoque;
    }

    public Estoque getEstoqueById(Long id){
        logger.info("m=getEstoqueById - status=start " + id);
        Estoque estoque = restTemplate.getForObject(host+path+id,Estoque.class);
        logger.info("m=getEstoqueById - status=finish " + id);
        return estoque;
    }

    public List<Estoque> getEstoqueByFabricante(String fabricante){
        logger.info("m=getEstoqueByFabricante - status=start " + fabricante);
        List<Estoque> estoque = restTemplate.exchange(host+pathFabricante+fabricante,HttpMethod.GET,null,new ParameterizedTypeReference<List<Estoque>>(){}).getBody();
        logger.info("m=getEstoqueByFabricante - status=finish " + fabricante);
        return estoque;
    }

    public Estoque save(Estoque estoque){
        logger.info("m=save - status=start ");
        HttpHeaders headers = new HttpHeaders();
        headers.add( "partner","Amazon");
        HttpEntity<Estoque> entity = new HttpEntity<>(estoque,headers);
        URI uri = URI.create(host + path);
        ResponseEntity<Estoque> estoqueEntity =
                restTemplate.exchange(uri,HttpMethod.POST,entity,Estoque.class);
        logger.info("m=save - status=finish");
        return estoqueEntity.getBody();
    }

    public Estoque update(Estoque estoque, Long id) {
        logger.info("m=update - status=start " + estoque.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("partner", "Amazon");
        HttpEntity<Estoque> entity = new HttpEntity<>(estoque,headers);
        URI uri = URI.create(host + path);
        ResponseEntity<Estoque> estoqueEntity =
                restTemplate.exchange(host+path+id,HttpMethod.PUT,entity,Estoque.class);
        logger.info("m=update - status=finish " + estoque.getId());
        return estoqueEntity.getBody();

    }

    public void delete(Long id){
       logger.info("m=delete - status=start " + id);
       ResponseEntity<Estoque> estoqueEntity = restTemplate
               .exchange(host+path+id,HttpMethod.DELETE,null,Estoque.class);
        logger.info("m=delete - status=finish " + id);
    }
}
