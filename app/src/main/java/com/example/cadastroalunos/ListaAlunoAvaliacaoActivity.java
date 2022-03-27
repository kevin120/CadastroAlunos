package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.AvaliacaoAlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.DisciplinaTurmaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AvaliacaoAluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.RecyclerItemClickListener;
import com.example.cadastroalunos.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ListaAlunoAvaliacaoActivity extends AppCompatActivity {
    private RecyclerView rvListaAlunos;
    private LinearLayout lnListaAlunoAvaliacaoActivity;
    private Long idDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno_avaliacao);
        lnListaAlunoAvaliacaoActivity = findViewById(R.id.lnListaAlunoAvaliacaoActivity);
        atualizaListaAluno();
    }

    public void atualizaListaAluno() {

        List<Aluno> listaAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");

        rvListaAlunos = findViewById(R.id.rvListaAlunos);
        AlunoAdapter adapter = new AlunoAdapter(listaAluno, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunos.setLayoutManager(llm);
        rvListaAlunos.setAdapter(adapter);

        rvListaAlunos.addOnItemTouchListener(new RecyclerItemClickListener(this, rvListaAlunos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AbreDialog(listaAluno.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void AbreDialog(Aluno aluno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaAlunoAvaliacaoActivity.this);
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_disciplinas, null);

        iniciaSpinner(viewDialog, aluno);
        builder.setView(viewDialog);
        builder.setPositiveButton("AVALIAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                abreAvaliacao(aluno);
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

    private void iniciaSpinner(View viewDialog, Aluno aluno) {
        MaterialSpinner spDisciplinas = viewDialog.findViewById(R.id.spDisciplina);

        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(aluno.getIdTurma());
        ArrayList<DisciplinaTurma> disciplinaTurmas = (ArrayList<DisciplinaTurma>) DisciplinaTurmaDAO.retornaDisciplinasTurmas("ID_TURMA = ?", whereArgs, null);

        whereArgs[0] = String.valueOf(aluno.getId());

        List<AvaliacaoAluno> avaliacaoAlunos = AvaliacaoAlunoDAO.retornaAvaliacaoAlunos("ID_ALUNO = ?", whereArgs, null);

        for (int i = 0; i < disciplinaTurmas.size(); i++) {
            Disciplina d = DisciplinaDAO.getDisciplina(disciplinaTurmas.get(i).getIdDisciplina());
            disciplinaTurmas.get(i).setDescricaoDisciplina(d.getDescricao());

            for (AvaliacaoAluno aa : avaliacaoAlunos) {
                if (aa.getIdDisciplina().equals(disciplinaTurmas.get(i).getIdDisciplina())) {
                    disciplinaTurmas.remove(disciplinaTurmas.get(i));
                    i--;
                }
            }
        }

        ArrayAdapter<DisciplinaTurma> adapterTurma = new ArrayAdapter<DisciplinaTurma>(this,
                android.R.layout.simple_list_item_1, disciplinaTurmas);

        spDisciplinas.setAdapter(adapterTurma);
        if (disciplinaTurmas.size() > 0)
            spDisciplinas.setSelection(1);

        spDisciplinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > -1) {
                    idDisciplina = adapterTurma.getItem(i).getIdDisciplina();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void abreAvaliacao(Aluno aluno) {
        if (idDisciplina == null) {
            Util.customSnackBar(lnListaAlunoAvaliacaoActivity, "É necessário informar uma disciplina para avaliar", 1);
        } else {
            Intent intent = new Intent(ListaAlunoAvaliacaoActivity.this, CadastroAlunoAvaliacaoActivity.class);
            Bundle bundle = new Bundle();

            bundle.putLong("idTurma", aluno.getIdTurma());
            bundle.putLong("idAluno", aluno.getId());
            Turma turma = TurmaDAO.getTurma(aluno.getIdTurma());
            bundle.putString("regimeTurma", turma.getRegime());
            bundle.putLong("idDisciplina", idDisciplina);
            bundle.putString("nomeAluno", aluno.getNome());
            intent.putExtras(bundle);

            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaAlunoAvaliacaoActivity, "Avaliação salva com sucesso!", 1);
            idDisciplina = null;
        }
    }
}