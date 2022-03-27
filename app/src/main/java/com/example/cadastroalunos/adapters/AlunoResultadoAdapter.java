package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.AvaliacaoAluno;
import com.example.cadastroalunos.model.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AlunoResultadoAdapter extends RecyclerView.Adapter<AlunoResultadoAdapter.AlunoResultadoViewHolder> {

    private List<AvaliacaoAluno> listaAvaliacaoAlunos;
    private Context context;

    public AlunoResultadoAdapter(List<AvaliacaoAluno> listaAvaliacaoAlunos, Context context) {
        this.listaAvaliacaoAlunos = listaAvaliacaoAlunos;
        this.context = context;
    }

    public static class AlunoResultadoViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edTurma;
        TextInputEditText edDisciplina;
        TextInputEditText edMedia;
        TextInputEditText edFrequencia;
        TextInputEditText edResultado;
        LinearLayout lnPrincipal;


        public AlunoResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            edRaAluno = itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = itemView.findViewById(R.id.edNomeAluno);
            edTurma = itemView.findViewById(R.id.edturmaAluno);
            edDisciplina = itemView.findViewById(R.id.eddisciplina);
            edMedia = itemView.findViewById(R.id.edmedia);
            edFrequencia = itemView.findViewById(R.id.edfrequencia);
            edResultado = itemView.findViewById(R.id.edresultado);
            lnPrincipal = itemView.findViewById(R.id.lnPrincipal);
        }

    }


    @NonNull
    @Override
    public AlunoResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_resultado_avaliacao, parent, false);

        AlunoResultadoAdapter.AlunoResultadoViewHolder viewHolder = new AlunoResultadoAdapter.AlunoResultadoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoResultadoViewHolder holder, int position) {
        AvaliacaoAluno avaliacaoAluno = listaAvaliacaoAlunos.get(position);

        if (avaliacaoAluno.getAluno() != null) {

            holder.edRaAluno.setText(String.valueOf(avaliacaoAluno.getAluno().getRa()));

            if (!avaliacaoAluno.getAluno().getNome().equals(""))
                holder.edNomeAluno.setText(avaliacaoAluno.getAluno().getNome());
        }

        if ((avaliacaoAluno.getTurma().getDescricao() != null) && (!avaliacaoAluno.getTurma().getDescricao().equals("")))
            holder.edTurma.setText(avaliacaoAluno.getTurma().getDescricao());

        if ((avaliacaoAluno.getDisciplina() != null) && (!avaliacaoAluno.getDisciplina().getDescricao().equals("")))
            holder.edDisciplina.setText(avaliacaoAluno.getDisciplina().getDescricao());

        Double media;
        if (avaliacaoAluno.getTurma().getRegime().equalsIgnoreCase("Anual")) {
            media = (avaliacaoAluno.getNota1() + avaliacaoAluno.getNota2() + avaliacaoAluno.getNota3() + avaliacaoAluno.getNota4()) / 4;
        } else
            media = (avaliacaoAluno.getNota1() + avaliacaoAluno.getNota2()) / 2;

        Double frequencia = avaliacaoAluno.getFrequencia();
        holder.edMedia.setText(String.valueOf(media));
        holder.edFrequencia.setText(String.valueOf(frequencia));

        String resultado;
        if ((media >= 6) && (frequencia >= 70)) {
            resultado = "Aprovado";
            holder.lnPrincipal.setBackgroundColor(Color.GREEN);
        } else {
            resultado = "Reprovado";
            holder.lnPrincipal.setBackgroundColor(Color.RED);
        }
        holder.edResultado.setText(resultado);
    }

    @Override
    public int getItemCount() {
        return listaAvaliacaoAlunos.size();
    }


}
