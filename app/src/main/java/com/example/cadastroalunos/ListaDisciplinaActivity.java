package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.cadastroalunos.adapters.ProfessorAdapter;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplinas;
    private LinearLayout lnListaDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

        lnListaDisciplinas = findViewById(R.id.lnListaDisciplinas);

        atualizaListaDisciplinas();
    }

    public void atualizaListaDisciplinas() {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        listaDisciplinas = DisciplinaDAO.retornaDisciplinas(null, null, "descricao asc");
        //Log.e("PHS", "Tamanho da lista: " + listaProfessores.size());

        rvListaDisciplinas = findViewById(R.id.rvListaDisciplinas);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplinas, this);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListaDisciplinas.setLayoutManager(llm);
        rvListaDisciplinas.setAdapter(adapter);
    }
}