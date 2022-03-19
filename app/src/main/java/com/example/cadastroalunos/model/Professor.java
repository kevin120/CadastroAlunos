package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class Professor extends SugarRecord {

    private String nome;
    private String cpf;
    private String dtContrato;
    private String dtNascimento;

    public Professor() {
    }

    public Professor(String nome, String cpf, String dtContrato, String dtNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtContrato = dtContrato;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtContrato() {
        return dtContrato;
    }

    public void setDtContrato(String dtContrato) {
        this.dtContrato = dtContrato;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }
}
