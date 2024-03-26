package br.edu.fateczl.atividade01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MatriculaDisciplina
{
    int id, ano, semestre, dia_semana;
    Horario horario;
    Disciplina disciplina;
    String situacao;
}
