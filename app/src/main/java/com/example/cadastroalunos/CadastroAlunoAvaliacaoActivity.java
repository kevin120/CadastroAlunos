package com.example.cadastroalunos;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.dao.AvaliacaoAlunoDAO;
import com.example.cadastroalunos.model.AvaliacaoAluno;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroAlunoAvaliacaoActivity extends AppCompatActivity {
    private TextInputEditText edNota1;
    private TextInputEditText edNota2;
    private TextInputEditText edNota3;
    private TextInputEditText edNota4;
    private TextInputEditText edFrequencia;
    private TextInputLayout txtNota3;
    private TextInputLayout txtNota4;
    private TextInputLayout txtFrequencia;
    private LinearLayout lnCadastroAlunoAvaliacaoActivity;

    private Long idTurma;
    private Long idAluno;
    private Long idDisciplina;
    private String regimeTurma;
    private String nomeAluno;
    private Boolean isAnual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno_avaliacao);

        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        edNota3 = findViewById(R.id.edNota3);
        edNota4 = findViewById(R.id.edNota4);
        edFrequencia = findViewById(R.id.edFrequencia);
        txtNota3 = findViewById(R.id.txtNota3);
        txtNota4 = findViewById(R.id.txtNota4);
        txtFrequencia = findViewById(R.id.txtFrequencia);
        lnCadastroAlunoAvaliacaoActivity = findViewById(R.id.lnCadastroAlunoAvaliacaoActivity);

        Bundle extras = getIntent().getExtras();

        idTurma = extras.getLong("idTurma");
        idAluno = extras.getLong("idAluno");
        idDisciplina = extras.getLong("idDisciplina");
        nomeAluno = extras.getString("nomeAluno");
        regimeTurma = extras.getString("regimeTurma");

        if (regimeTurma.equalsIgnoreCase("Anual")) {
            txtNota3.setVisibility(View.VISIBLE);
            txtNota4.setVisibility(View.VISIBLE);
            isAnual = true;
        }

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

    private void validaCampos() {
        if (edNota1.getText().toString().trim().equals("")) {
            edNota1.setError("Informe a Nota 1 do Aluno!");
            edNota1.requestFocus();
            return;
        } else if ((Double.parseDouble(edNota1.getText().toString()) > 10) || (Double.parseDouble(edNota1.getText().toString()) < 0)) {
            edNota1.setError("A nota 1 deve ser entre 0 e 10!");
            edNota1.requestFocus();
            return;
        }

        if (edNota2.getText().toString().trim().equals("")) {
            edNota2.setError("Informe a Nota 2 do Aluno!");
            edNota2.requestFocus();
            return;
        } else if ((Double.parseDouble(edNota2.getText().toString()) > 10) || (Double.parseDouble(edNota2.getText().toString()) < 0)) {
            edNota2.setError("A nota 2 deve ser entre 0 e 10!");
            edNota2.requestFocus();
            return;
        }

        if (isAnual) {
            if (edNota3.getText().toString().trim().equals("")) {
                edNota3.setError("Informe a Nota 3 do Aluno!");
                edNota3.requestFocus();
                return;
            } else if ((Double.parseDouble(edNota3.getText().toString()) > 10) || (Double.parseDouble(edNota3.getText().toString()) < 0)) {
                edNota3.setError("A nota 3 deve ser entre 0 e 10!");
                edNota3.requestFocus();
                return;
            }


            if (edNota4.getText().toString().trim().equals("")) {
                edNota4.setError("Informe a Nota 4 do Aluno!");
                edNota4.requestFocus();
                return;
            } else if ((Double.parseDouble(edNota4.getText().toString()) > 10) || (Double.parseDouble(edNota4.getText().toString()) < 0)) {
                edNota4.setError("A nota 4 deve ser entre 0 e 10!");
                edNota4.requestFocus();
                return;
            }
        }

        if (edFrequencia.getText().toString().trim().equals("")) {
            edFrequencia.setError("Informe a Frequencia do Aluno!");
            edFrequencia.requestFocus();
            return;
        } else if ((Double.parseDouble(edFrequencia.getText().toString()) > 100) || (Double.parseDouble(edFrequencia.getText().toString()) < 0)) {
            edFrequencia.setError("A Frequencia deve ser entre 0 e 100!");
            edFrequencia.requestFocus();
            return;
        }

        AbreDialog();

    }

    private void AbreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroAlunoAvaliacaoActivity.this);
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_confirma_avaliacao, null);

        TextView txtNome = viewDialog.findViewById(R.id.txtNomeAluno);
        TextView txtmedia = viewDialog.findViewById(R.id.txtMedia);
        TextView txtFrequencia = viewDialog.findViewById(R.id.txtFrequencia);
        TextView txtResultado = viewDialog.findViewById(R.id.txtResultado);

        Double nota1 = (Double.parseDouble(edNota1.getText().toString()));
        Double nota2 = (Double.parseDouble(edNota2.getText().toString()));
        Double nota3 = 0D;
        Double nota4 = 0D;

        if (isAnual) {
            nota3 = (Double.parseDouble(edNota3.getText().toString()));
            nota4 = (Double.parseDouble(edNota4.getText().toString()));
        }
        Double frequencia = (Double.parseDouble(edFrequencia.getText().toString()));

        Double media;
        if (isAnual) {
            media = (nota1 + nota2 + nota3 + nota4) / 4;
        } else {
            media = (nota1 + nota2) / 2;
        }

        String resultado;
        if ((media >= 6) && (frequencia >= 70)) {
            resultado = "Aprovado";
            txtResultado.setTextColor(Color.GREEN);
        } else {
            resultado = "Reprovado";
            txtResultado.setTextColor(Color.RED);
        }

        txtNome.setText(nomeAluno);
        txtmedia.setText("Média: " + String.valueOf(media));
        txtFrequencia.setText("Frequencia: " + String.valueOf(frequencia));
        txtResultado.setText("Status: Aluno " + resultado);


        builder.setView(viewDialog);
        Double finalNota3 = nota3;
        Double finalNota4 = nota4;
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                salvaAvaliacao(nota1, nota2, finalNota3, finalNota4, frequencia);
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void salvaAvaliacao(Double nota1, Double nota2, Double nota3, Double nota4, Double frequencia) {
        AvaliacaoAluno avaliacaoAluno = new AvaliacaoAluno(idAluno, idDisciplina, idTurma, nota1, nota2, nota3, nota4, frequencia);

        if (AvaliacaoAlunoDAO.salvar(avaliacaoAluno) > 0) {

            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnCadastroAlunoAvaliacaoActivity, "Erro ao salvar a Avaliação ", 0);
    }

    private void limparCampos() {
        edNota1.setText("");
        edNota2.setText("");
        edNota3.setText("");
        edNota4.setText("");
        edFrequencia.setText("");
    }


}