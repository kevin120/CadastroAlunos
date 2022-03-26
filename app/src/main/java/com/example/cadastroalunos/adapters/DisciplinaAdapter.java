package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private List<Disciplina> listaDisciplinas;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText edDescricaoDisciplina;
        TextInputEditText edDuracaoDisciplina;
        TextInputEditText edProfessorDisciplina;


        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);
            edDescricaoDisciplina = (TextInputEditText) itemView.findViewById(R.id.edDescricaoDisciplina);
            edDuracaoDisciplina = (TextInputEditText) itemView.findViewById(R.id.edDuracaoDisciplina);
            edProfessorDisciplina = (TextInputEditText) itemView.findViewById(R.id.edProfessorDisciplina);

        }
    }

    @NonNull
    @Override
    public DisciplinaAdapter.DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_displina, parent, false);

        DisciplinaAdapter.DisciplinaViewHolder viewHolder = new DisciplinaAdapter.DisciplinaViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaAdapter.DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplinas.get(position);

        holder.edDescricaoDisciplina.setText(String.valueOf(disciplina.getDescricao()));
        holder.edDuracaoDisciplina.setText(String.valueOf(disciplina.getDuracao()));

        Professor professor = ProfessorDAO.getProfessor(disciplina.getIdProfessor());
        holder.edProfessorDisciplina.setText(String.valueOf(professor.getNome()));
    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}
