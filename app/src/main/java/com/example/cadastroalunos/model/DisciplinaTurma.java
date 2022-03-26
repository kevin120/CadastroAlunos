package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class DisciplinaTurma extends SugarRecord {
    private Long idDisciplina;
    private Long idTurma;

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
}
