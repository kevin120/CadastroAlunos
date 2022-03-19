package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public static long salvar(Disciplina disciplina) {
        try {
            return disciplina.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a Disciplina: "+ex.getMessage());
            return -1;
        }
    }

    public static Disciplina getDisciplina(int id){
        try{
            return Disciplina.findById(Disciplina.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar a Disciplina: "+ex.getMessage());
            return null;
        }
    }

    public static List<Disciplina> retornaDisciplinas(String where, String[]whereArgs, String orderBy){
        List<Disciplina> list = new ArrayList<>();
        try{
            list = Disciplina.find(Disciplina.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Disciplinas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Disciplina disciplina){
        try{
            return Disciplina.delete(disciplina);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar a Disciplina: "+ex.getMessage());
            return false;
        }

    }

}
