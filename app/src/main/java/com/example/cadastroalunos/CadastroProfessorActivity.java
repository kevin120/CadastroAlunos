package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.CpfMask;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CadastroProfessorActivity extends AppCompatActivity {
    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;
    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNascProfessor;
    private TextInputEditText edDtContratoProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edNomeProfessor         = findViewById(R.id.edNomeProfessor);
        edCpfProfessor          = findViewById(R.id.edCpfProfessor);
        edDtNascProfessor       = findViewById(R.id.edDtNascProfessor);
        edDtContratoProfessor   = findViewById(R.id.edDtContratoProfessor);

        setDataAtual();

        edDtNascProfessor.setFocusable(false);
        edDtContratoProfessor.setFocusable(false);

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro Professor");
    }


    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText)dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
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
        if (edNomeProfessor.getText().toString().trim().equals("")) {
            edNomeProfessor.setError("Informe o Nome do Professor");
            edNomeProfessor.requestFocus();
            return;
        }

        if (edCpfProfessor.getText().toString().trim().equals("")) {
            edCpfProfessor.setError("Informe o CPF do Professor");
            edCpfProfessor.requestFocus();
            return;
        }

        if (edDtNascProfessor.getText().toString().trim().equals("")) {
            edDtNascProfessor.setError("Informe a data de Nascimento do Professor");
            edDtNascProfessor.requestFocus();
            return;
        }

        if (edDtContratoProfessor.getText().toString().trim().equals("")) {
            edDtContratoProfessor.setError("Informe a data de Cadastro do Professor");
            edDtContratoProfessor.requestFocus();
            return;
        }

        SalvarProfessor();
    }

    private void SalvarProfessor() {
        Professor professor = new Professor();

        professor.setNome(edNomeProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDtNascimento(edDtNascProfessor.getText().toString());
        professor.setDtContrato(edDtContratoProfessor.getText().toString());

        if(ProfessorDAO.salvar(professor) > 0) {

            setResult(RESULT_OK);
            finish();
        }


    }

    private void limparCampos() {
        edNomeProfessor.setText("");
        edCpfProfessor.setText("");
        edDtNascProfessor.setText("");
        edDtContratoProfessor.setText("");
    }
}