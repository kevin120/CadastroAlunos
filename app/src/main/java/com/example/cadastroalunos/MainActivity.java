package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.lnMain);
    }

    public void cadastrarAluno(View view) {

        List<Turma> turmaList = TurmaDAO.retornaTurma("", null, "1");

        if (turmaList == null || turmaList.size() == 0) {
            Util.customSnackBar(mainLayout, "Antes de cadastrar um aluno, você deve cadastrar uma turma", 0);
            return;
        }

        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void cadastrarProfessor(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void cadastrarDisciplina(View view) {

        List<Professor> professorList =  ProfessorDAO.retornaProfessores("", null, "1");

        if (professorList == null || professorList.size() == 0) {
            Util.customSnackBar(mainLayout, "Antes de cadastrar uma disciplina, você deve cadastrar um professor", 0);
            return;
        }

        Intent intent = new Intent(this, ListaDisciplinaActivity.class);
        startActivity(intent);
    }

    public void cadastrarTurma(View view) {

        List<Disciplina> disciplinaList =  DisciplinaDAO.retornaDisciplinas("", null, "1");

        if (disciplinaList == null || disciplinaList.size() == 0) {
            Util.customSnackBar(mainLayout, "Antes de cadastrar uma turma, você deve cadastrar uma disciplina", 0);
            return;
        }

        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void lancarNotas(View view) {

        List<Aluno> alunoList =  AlunoDAO.retornaAlunos("", null, "1");

        if (alunoList == null || alunoList.size() == 0) {
            Util.customSnackBar(mainLayout, "Antes de lanças as notas, você deve cadastrar um aluno", 0);
            return;
        }

        Intent intent = new Intent(this, ListaAlunoAvaliacaoActivity.class);
        startActivity(intent);
    }

    public void ConferirNotas(View view) {
        Intent intent = new Intent(this, ListaAlunoResultadoActivity.class);
        startActivity(intent);
    }
}