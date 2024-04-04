package br.edu.fateczl.atividade01.persistence;

import java.sql.SQLException;
import java.util.List;

/*
Interface:
Create,
Update,
Delete,
Find,
and List
 */
public interface ICRUD<T>
{
    public String insert (T t)	throws SQLException, ClassNotFoundException;
    public String update (T t)	throws SQLException, ClassNotFoundException;
    public String delete (T t)    throws SQLException, ClassNotFoundException;
    public T find (T t)         throws SQLException, ClassNotFoundException;
    public List<T> list ()    	    throws SQLException, ClassNotFoundException;
}
