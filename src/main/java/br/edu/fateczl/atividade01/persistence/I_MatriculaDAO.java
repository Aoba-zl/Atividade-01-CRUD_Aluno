package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Matricula;

import java.sql.SQLException;

/*
Interface:
Create,
Update,
Delete,
and Find
 */
public interface I_MatriculaDAO
{
    public String insert (Matricula matricula)	throws SQLException, ClassNotFoundException;
    public String update (Matricula matricula)	throws SQLException, ClassNotFoundException;
    public String disable (Matricula matricula)    throws SQLException, ClassNotFoundException;
    public String enable (Matricula matricula)    throws SQLException, ClassNotFoundException;
    public Matricula findCpf(Matricula matricula)         throws SQLException, ClassNotFoundException;
    public Matricula findRa(Matricula matricula)         throws SQLException, ClassNotFoundException;
}
