package com.example.praticaweb.Service;

import com.example.praticaweb.entities.Aluno;
import com.example.praticaweb.entities.Turma;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    public List<Turma> turmas = new ArrayList<Turma>();

    public List<Turma> findAll(){

        List<Turma> result = turmas;

        return result;
    }

    public Turma addTurma(Turma newTurma){

        for (Turma turma : turmas){
            if (turma.getCodigo() == newTurma.getCodigo()){
                return null;
            }
        }

        turmas.add(newTurma);

        return newTurma;
    }

    public Turma findByCodigo(int codigo){

        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                return turma;
            }
        }

        return null;
    }

    public Turma editTurmaByCodigo(int codigo, Turma editTurma){
        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                turma.setSemestre(editTurma.getSemestre());
                turma.setAlunos(editTurma.getAlunos());
                return turma;
            }
        }

        return null;
    }

    public List<Turma> deleteTurmaByCodigo(int codigo){
        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                turmas.remove(turma);
                return turmas;
            }
        }

        return null;
    }

    public List<Aluno> findAllAlunosByCodigoTurma(int codigo){
        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                return turma.getAlunos();
            }
        }

        return null;
    }

    public Turma addAlunoByCodigoTurma(int codigo, Aluno aluno){
        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                turma.addAluno(aluno);
                return turma;
            }
        }

        return null;
    }

    public Turma deleteAlunoByMatriculaAndCodigoTurma(int codigo, int matricula){
        for (Turma turma : turmas){
            if (turma.getCodigo() == codigo){
                for (Aluno aluno : turma.getAlunos()){
                    if (aluno.getMatricula() == matricula){
                        turma.removeAluno(aluno);
                        return turma;
                    }
                }
            }
        }

        return null;
    }
}
