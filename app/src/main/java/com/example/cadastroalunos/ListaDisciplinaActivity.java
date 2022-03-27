package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapters.DisciplinaAdapter;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplinas;
    private LinearLayout lnListaDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);
        setTitle("Listagem de Disciplinas");
        lnListaDisciplinas = findViewById(R.id.lnListaDisciplinas);

        atualizaListaDisciplinas();
    }

    public void atualizaListaDisciplinas() {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        listaDisciplinas = DisciplinaDAO.retornaDisciplinas(null, null, "descricao asc");

        rvListaDisciplinas = findViewById(R.id.rvListaDisciplinas);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplinas, this);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListaDisciplinas.setLayoutManager(llm);
        rvListaDisciplinas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:
                abrirCadastroDisplina();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroDisplina() {
        Intent intent = new Intent(this, CadastroDisciplinaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaDisciplinas, "Disciplina salva com sucesso!", 1);
        }
        atualizaListaDisciplinas();
    }
}