package com.example.cadastroalunos.dao;

import android.util.Log;


import com.example.cadastroalunos.model.DisciplinaTurma;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaTurmaDAO {

    public static long salvar(DisciplinaTurma disciplinaTurma) {
        try {
            return disciplinaTurma.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a Disciplina da Turma: "+ex.getMessage());
            return -1;
        }
    }

    public static DisciplinaTurma getDisciplinaTurma(int id){
        try{
            return DisciplinaTurma.findById(DisciplinaTurma.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar a Disciplina da Turma: "+ex.getMessage());
            return null;
        }
    }

    public static List<DisciplinaTurma> retornaDisciplinasTurmas(String where, String[]whereArgs, String orderBy){
        List<DisciplinaTurma> list = new ArrayList<>();
        try{
            list = DisciplinaTurma.find(DisciplinaTurma.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Disciplinas da turma: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(DisciplinaTurma disciplinaTurma){
        try{
            return DisciplinaTurma.delete(disciplinaTurma);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar a Disciplina da Turma: "+ex.getMessage());
            return false;
        }

    }
}
