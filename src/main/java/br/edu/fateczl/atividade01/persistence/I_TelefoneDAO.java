package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Aluno;
import br.edu.fateczl.atividade01.model.Telefone;

import java.sql.SQLException;
import java.util.List;

/*
Interface:
Create,
Update,
Delete,
and List
 */
public interface I_TelefoneDAO
{
    public void insert(Aluno aluno, Telefone telefone) throws SQLException, ClassNotFoundException;
    public void update (Aluno aluno)	throws SQLException, ClassNotFoundException;
    public void delete(Aluno aluno, Telefone telefone) throws SQLException, ClassNotFoundException;
    public List<Telefone> list(Aluno aluno) throws SQLException, ClassNotFoundException;
}

