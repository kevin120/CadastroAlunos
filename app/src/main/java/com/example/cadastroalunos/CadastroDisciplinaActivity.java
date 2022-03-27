package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edDescricaoDisciplina;
    private MaterialSpinner spDuracao;
    private MaterialSpinner spProfessores;
    private LinearLayout cadastroDisciplinaAcitivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edDescricaoDisciplina = findViewById(R.id.edDescricaoDisciplina);
        cadastroDisciplinaAcitivity = findViewById(R.id.cadastroDisciplinaAcitivity);

        iniciaSpinners();

        setTitle("Cadastrar Disciplina");
    }

    private void iniciaSpinners(){
        spDuracao = findViewById(R.id.spDuracao);
        spProfessores = findViewById(R.id.spProfessores);


        String[] duracao = new String[]{
                "1 ano",
                "2 anos",
                "3 anos",
                "4 anos",
                "5 anos",};

        ArrayList<Professor> professores = (ArrayList<Professor>) ProfessorDAO.retornaProfessores(null, null, null);

        ArrayAdapter adapterDuracao = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  duracao);
        ArrayAdapter<Professor> adapterProfessor = new ArrayAdapter<Professor>(this,
                android.R.layout.simple_list_item_1,  professores);

        adapterProfessor.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spDuracao.setAdapter(adapterDuracao);
        spProfessores.setAdapter(adapterProfessor);
    }

    //Validação dos campos
    private void validaCampos(){
        //Valida o campo Descrição
        if(edDescricaoDisciplina.getText().equals("")){
            edDescricaoDisciplina.setError("Informe o nome da Disciplina!");
            edDescricaoDisciplina.requestFocus();
            return;
        }

        //Valida o spinner de duração
        if(spDuracao.getSelectedItem() == null){
            spDuracao.setError("Selecione a duração!");
            spDuracao.requestFocus();
            return;
        }

        //Valida o campo do professor
        if(spProfessores.getSelectedItem() == null){
            spProfessores.setError("Selecione o Professor!");
            spProfessores.requestFocus();
            return;
        }
        salvarDisciplina();
    }

    public void salvarDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setDescricao(edDescricaoDisciplina.getText().toString());
        disciplina.setDuracao(spDuracao.getSelectedItem().toString());
        disciplina.setIdProfessor((int) ((Professor) spProfessores.getSelectedItem()).getId().longValue());

        if(DisciplinaDAO.salvar(disciplina) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(cadastroDisciplinaAcitivity, "Erro ao salvar a disciplina ("+disciplina.getDescricao()+") " +
                    "verifique o log", 0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_limpar:
                //TODO: adicionar método  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar método  de salvar dados
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edDescricaoDisciplina.setText("");
        spDuracao.setSelection(-1);
        spProfessores.setSelection(-1);
    }
}