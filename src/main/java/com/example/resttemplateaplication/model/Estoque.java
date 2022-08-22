package com.example.resttemplateaplication.model;

public class Estoque {

    private Long id;
    private String descricao;
    private String fabricante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Estoque withBuilderDescricao(String descricao){
        setDescricao(descricao);
        return this;
    }

    public Estoque withBuilderFabricante(String fabricante){
        setFabricante(fabricante);
        return this;
    }

    public Estoque withBuilderId(Long id){
        setId(id);
        return this;
    }
}
