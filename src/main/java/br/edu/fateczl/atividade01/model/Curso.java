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

public class Curso
{
    int codigo, carga_horaria, nota_enade;
    String nome, sigla, turno;
    List<Disciplina> disciplinas;
}
