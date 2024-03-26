package br.edu.fateczl.atividade01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Aluno
{
    String cpf, nome, nome_social,
    email_pessoal, email, email_corporativo,
    instituicao_seg_grau;
    Date dt_nasc, dt_conclusao_seg_grau;
    List<Telefone> telefones;
}
