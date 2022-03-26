package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Disciplina;

import java.util.List;

public class DisciplinaTurmaAdapter extends RecyclerView.Adapter<DisciplinaTurmaAdapter.DisciplinaTurmaViewHolder> {
    private List<Disciplina> listaDisciplinas;
    private Context context;

    public DisciplinaTurmaAdapter(List<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    public static class DisciplinaTurmaViewHolder extends RecyclerView.ViewHolder {
        TextView txtDisciplina;
        CheckBox chkSelecionado;

        public DisciplinaTurmaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDisciplina = itemView.findViewById(R.id.txtDisciplina);
            chkSelecionado = itemView.findViewById(R.id.chkDisciplina);
        }
    }

    @NonNull
    @Override
    public DisciplinaTurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina_turma, parent, false);
        DisciplinaTurmaAdapter.DisciplinaTurmaViewHolder viewHolder = new DisciplinaTurmaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaTurmaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplinas.get(position);

        holder.txtDisciplina.setText(String.valueOf(disciplina.getDescricao()));
        holder.chkSelecionado.setChecked(listaDisciplinas.get(position).getSelecionado());
        holder.chkSelecionado.setTag(position);
        holder.chkSelecionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.chkSelecionado.getTag();

                if (listaDisciplinas.get(pos).getSelecionado()) {
                    listaDisciplinas.get(pos).setSelecionado(false);
                } else {
                    listaDisciplinas.get(pos).setSelecionado(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}
