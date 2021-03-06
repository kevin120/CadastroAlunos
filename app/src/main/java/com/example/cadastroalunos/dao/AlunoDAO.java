package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Aluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static long salvar(Aluno aluno){
        try{
            return aluno.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o aluno: "+ex.getMessage());
            return -1;
        }
    }

    public static Aluno getAluno(Long id){
         try{
            return Aluno.findById(Aluno.class, id);
         }catch (Exception ex){
             Log.e("Erro", "Erro ao retornar o aluno: "+ex.getMessage());
             return null;
         }
    }

    public static List<Aluno> retornaAlunos(String where, String[]whereArgs, String orderBy){
        List<Aluno> list = new ArrayList<>();
        try{
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de alunos: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Aluno aluno){
        try{
            return Aluno.delete(aluno);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o aluno: "+ex.getMessage());
            return false;
        }

    }




}
