package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.AvaliacaoAluno;


import java.util.ArrayList;
import java.util.List;

public class AvaliacaoAlunoDAO {
    public static long salvar(AvaliacaoAluno avaliacaoAluno){
        try{
            return avaliacaoAluno.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a Avaliação do Aluno: "+ex.getMessage());
            return -1;
        }
    }

    public static AvaliacaoAluno getAvaliacaoAluno(int id){
        try{
            return AvaliacaoAluno.findById(AvaliacaoAluno.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar a avaliação do aluno: "+ex.getMessage());
            return null;
        }
    }

    public static List<AvaliacaoAluno> retornaAvaliacaoAlunos(String where, String[]whereArgs, String orderBy){
        List<AvaliacaoAluno> list = new ArrayList<>();
        try{
            list = AvaliacaoAluno.find(AvaliacaoAluno.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de avaliação dos alunos: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(AvaliacaoAluno avaliacaoAluno){
        try{
            return AvaliacaoAluno.delete(avaliacaoAluno);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o avaliaçao do Aluno: "+ex.getMessage());
            return false;
        }

    }
}
