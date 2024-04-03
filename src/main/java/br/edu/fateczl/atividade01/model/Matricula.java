package br.edu.fateczl.atividade01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Matricula
{
    String ra;
    int pontuacao_vestibular, posicao_vestibular,
    ano_ingresso, semestre_ingresso, ano_limite, semestre_limite;
    boolean matricula_ativa;
    Aluno aluno;
    Curso curso;
    List<MatriculaDisciplina> matriculaDisciplinas;

    @Override
    public String toString() {
        return (this.ra);
    }
}
