package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.DisciplinaTurmaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edDescricao;
    private MaterialSpinner spRegime;
    private MaterialSpinner spPeriodo;
    private LinearLayout lnPrincipal;
    private List<Disciplina> listaDisciplinas;
    private Boolean isDisciplinaAssociada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edDescricao = findViewById(R.id.edDescricaoTurma);
        lnPrincipal = findViewById(R.id.lnPrincipal);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro Turma");


        listaDisciplinas = new ArrayList<>();
        listaDisciplinas = DisciplinaDAO.retornaDisciplinas(null, null, "descricao asc");

        for (Disciplina d : listaDisciplinas) {
            d.setIdDisciplina(d.getId());
        }

        iniciaSpinners();
    }


    private void iniciaSpinners() {
        spRegime = findViewById(R.id.spRegime);
        spPeriodo = findViewById(R.id.spPeriodo);

        String regimes[] = new String[]{"Semestral", "Anual"};
        String periodos[] = new String[]{"Manhã", "Tarde", "Noite"};

        ArrayAdapter adapterRegime = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);
        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, periodos);

        spRegime.setAdapter(adapterRegime);
        spPeriodo.setAdapter(adapterPeriodo);

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
        if (edDescricao.getText().toString().trim().equals("")) {
            edDescricao.setError("Informe a Descrição da turma");
            edDescricao.requestFocus();
            return;
        }
        if(spRegime.getSelectedItem() == null){
            spRegime.setError("Selecione o Regimo!");
            spRegime.requestFocus();
            return;
        }

        if(spPeriodo.getSelectedItem() == null){
            spPeriodo.setError("Selecione o Período!");
            spPeriodo.requestFocus();
            return;
        }

        if (!isDisciplinaAssociada) {
            Util.customSnackBar(lnPrincipal, "É Obrigatório Selecionar ao menos uma displina Clicando no botão '+ Disciplinas'", 1);
            return;
        }

        salvarTurma();
    }

    private void salvarTurma() {
        Turma turma = new Turma();

        turma.setDescricao(edDescricao.getText().toString());
        turma.setRegime(spRegime.getSelectedItem().toString());
        turma.setPeriodo(spPeriodo.getSelectedItem().toString());

        Long idTurmaGravada = TurmaDAO.salvar(turma);
        if (idTurmaGravada > 0) {
            for (Disciplina disciplina : listaDisciplinas) {
                if (disciplina.getSelecionado()) {
                    DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina.getIdDisciplina(), idTurmaGravada);
                    DisciplinaTurmaDAO.salvar(disciplinaTurma);
                }
            }

            setResult(RESULT_OK);
            finish();
        }


    }

    private void limparCampos() {
        edDescricao.setText("");

    }

    public void adicionarDisciplinas(View view) {
        Intent intent = new Intent(this, DisciplinaTurmaActivity.class);
        intent.putExtra("listaDisciplinas", (Serializable) listaDisciplinas);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            listaDisciplinas = (List<Disciplina>) data.getExtras().getSerializable("listaDisciplinas");
            isDisciplinaAssociada = true;
        }
    }
}