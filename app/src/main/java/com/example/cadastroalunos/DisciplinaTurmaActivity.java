package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.adapters.DisciplinaTurmaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaTurmaActivity extends AppCompatActivity {
    private RecyclerView rvListaDisciplinaTurma;
    private List<Disciplina> listaDisciplinas;
    private DisciplinaTurmaAdapter adapter;
    private LinearLayout lnPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_turma);

        rvListaDisciplinaTurma = findViewById(R.id.rvListaDisciplinaTurma);
        lnPrincipal = findViewById(R.id.lnPrincipal);
        atualizaListaAluno();
    }

    public void atualizaListaAluno() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            listaDisciplinas = (List<Disciplina>) extras.get("listaDisciplinas");
        }

        rvListaDisciplinaTurma = findViewById(R.id.rvListaDisciplinaTurma);
        adapter = new DisciplinaTurmaAdapter(listaDisciplinas, this);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvListaDisciplinaTurma.setLayoutManager(llm);
        rvListaDisciplinaTurma.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void validaCampos() {
        Boolean temSelecionado = false;
        for (Disciplina d: listaDisciplinas) {
            if (d.getSelecionado()) {
                temSelecionado = true;
                break;
            }
        }
        if (!temSelecionado) {
            Util.customSnackBar(lnPrincipal, "É Obrigatório selecionar ao menos uma disciplina.", 1);
        } else {
            salvarDisciplinas();
        }

    }

    private void salvarDisciplinas() {

        Intent intent = new Intent();

        intent.putExtra("listaDisciplinas", (Serializable) listaDisciplinas);

        setResult(RESULT_OK, intent);
        finish();
    }


    private void limparCampos() {
        for (Disciplina d: listaDisciplinas) {
            d.setSelecionado(false);
        }
        adapter.notifyDataSetChanged();
    }

}