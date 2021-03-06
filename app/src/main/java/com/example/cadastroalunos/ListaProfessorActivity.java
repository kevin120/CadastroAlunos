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

import com.example.cadastroalunos.adapters.ProfessorAdapter;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaProfessorActivity extends AppCompatActivity {
    private RecyclerView rvListaProfessores;
    private LinearLayout lnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);
        setTitle("Listagem de professores");

        lnLista = findViewById(R.id.lnLista);

        atualizaListaProfessor();
    }

    public void atualizaListaProfessor() {
        List<Professor> listaProfessores = new ArrayList<>();
        listaProfessores = ProfessorDAO.retornaProfessores("", new String[]{}, "nome asc");

        rvListaProfessores = findViewById(R.id.rvListaProfessores);
        ProfessorAdapter adapter = new ProfessorAdapter(listaProfessores, this);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListaProfessores.setLayoutManager(llm);
        rvListaProfessores.setAdapter(adapter);
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
                abrirCadastroProfessor();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroProfessor() {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnLista, "Professor salvo com sucesso!", 1);
        }
        atualizaListaProfessor();
    }

}