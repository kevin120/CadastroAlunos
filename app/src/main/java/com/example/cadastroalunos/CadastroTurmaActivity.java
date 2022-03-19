package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edDescricao;
    private MaterialSpinner spRegime;
    private MaterialSpinner spPeriodo;
    private ExtendedFloatingActionButton extendedFanAdicionaProfessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edDescricao = findViewById(R.id.edDescricaoTurma);
        extendedFanAdicionaProfessor = findViewById(R.id.extendedfab);

        iniciaSpinners();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro Turma");

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


        salvarTurma();
    }

    private void salvarTurma() {
        Turma turma = new Turma();

        turma.setDescricao(edDescricao.getText().toString());
        turma.setRegime(spRegime.getSelectedItem().toString());
        turma.setPeriodo(spPeriodo.getSelectedItem().toString());

        if (TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        }


    }

    private void limparCampos() {
        edDescricao.setText("");

    }

    public void adicionarDisciplinas(View view) {
    }
}