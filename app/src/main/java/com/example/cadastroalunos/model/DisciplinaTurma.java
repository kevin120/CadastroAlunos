package com.example.cadastroalunos.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class DisciplinaTurma extends SugarRecord {
    private Long idDisciplina;
    private Long idTurma;
    @Ignore
    private String descricaoDisciplina;

    public DisciplinaTurma(Long idDisciplina, Long idTurma) {
        this.idDisciplina = idDisciplina;
        this.idTurma = idTurma;
    }

    public DisciplinaTurma() {
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getDescricaoDisciplina() {
        return descricaoDisciplina;
    }

    public void setDescricaoDisciplina(String descricaoDisciplina) {
        this.descricaoDisciplina = descricaoDisciplina;
    }

    @Override
    public String toString() {
        return this.descricaoDisciplina;
    }
}
