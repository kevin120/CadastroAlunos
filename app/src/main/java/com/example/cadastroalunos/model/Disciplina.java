package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class Disciplina extends SugarRecord {

    private String descricao;
    private String duracao;
    private int idProfessor;

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

    @NonNull
    @Override
    public String toString() {
        return this.descricao;
    }
}
