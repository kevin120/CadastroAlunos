package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;

public class Disciplina extends SugarRecord implements Serializable {

    private String descricao;
    private String duracao;
    private int idProfessor;
    @Ignore
    private Boolean selecionado = false;
    @Ignore
    Long idDisciplina;

    public Disciplina() {
    }

    public Disciplina(String descricao, String duracao, int idProfessor) {
        this.descricao = descricao;
        this.duracao = duracao;
        this.idProfessor = idProfessor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @NonNull
    @Override
    public String toString() {
        return this.descricao;
    }
}
