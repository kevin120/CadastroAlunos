package com.example.cadastroalunos.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class AvaliacaoAluno extends SugarRecord {

    private Long idAluno;
    private Long idDisciplina;
    private Long idTurma;
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double nota4;
    private Double frequencia;
    @Ignore
    private Disciplina disciplina;
    @Ignore
    private Aluno aluno;
    @Ignore
    private Turma turma;


    public AvaliacaoAluno() {
    }

    public AvaliacaoAluno(Long idAluno, Long idDisciplina, Long idTurma, Double nota1, Double nota2, Double nota3, Double nota4, Double frequencia) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.idTurma = idTurma;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.frequencia = frequencia;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
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

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public Double getNota4() {
        return nota4;
    }

    public void setNota4(Double nota4) {
        this.nota4 = nota4;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
