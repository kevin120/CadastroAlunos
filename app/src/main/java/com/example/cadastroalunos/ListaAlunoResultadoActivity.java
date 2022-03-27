package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.adapters.AlunoResultadoAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.AvaliacaoAlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AvaliacaoAluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.RecyclerItemClickListener;

import java.util.List;

public class ListaAlunoResultadoActivity extends AppCompatActivity {
    private RecyclerView rvListaAlunosResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno_resultado);

        atualizaListaAluno();
    }

    public void atualizaListaAluno() {

        List<AvaliacaoAluno> listaAvaliacaoAlunos = AvaliacaoAlunoDAO.retornaAvaliacaoAlunos("", new String[]{}, null);

        for (AvaliacaoAluno aa : listaAvaliacaoAlunos) {
            Disciplina disciplina = DisciplinaDAO.getDisciplina(aa.getIdDisciplina());
            if (disciplina != null)
                aa.setDisciplina(disciplina);

            Aluno aluno = AlunoDAO.getAluno(aa.getIdAluno());
            if (aluno != null)
                aa.setAluno(aluno);

            Turma turma = TurmaDAO.getTurma(aa.getIdTurma());
            if (turma != null)
                aa.setTurma(turma);

        }


        rvListaAlunosResultado = findViewById(R.id.rvListaAlunosResultado);
        AlunoResultadoAdapter adapter = new AlunoResultadoAdapter(listaAvaliacaoAlunos, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunosResultado.setLayoutManager(llm);
        rvListaAlunosResultado.setAdapter(adapter);


    }
}