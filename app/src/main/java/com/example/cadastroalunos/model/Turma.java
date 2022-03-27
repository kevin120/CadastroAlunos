package com.example.cadastroalunos.model;

import com.orm.SugarRecord;


public class Turma extends SugarRecord {
    private String descricao;
    private String periodo;
    private String regime;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
