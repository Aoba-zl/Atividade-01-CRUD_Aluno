package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Horario;
import br.edu.fateczl.atividade01.model.Matricula;
import br.edu.fateczl.atividade01.model.MatriculaDisciplina;

import java.sql.SQLException;
import java.util.List;

public interface I_MatriculaDisciplinaDAO
{
    public String insert (Matricula matricula, MatriculaDisciplina matriculaDisciplina)
            throws SQLException, ClassNotFoundException;
    public String update(Matricula matricula, MatriculaDisciplina matriculaDisciplina)
            throws SQLException, ClassNotFoundException;
    public List<Horario> list_horarios_disponiveis(String ra, int dia, String carga_disciplina)
            throws SQLException, ClassNotFoundException;
    List<MatriculaDisciplina> list_disciplinas_disponiveis(String ra)
            throws SQLException, ClassNotFoundException;
    public List<MatriculaDisciplina> list_disciplinas_matriculadas(String ra)
            throws SQLException, ClassNotFoundException;

}
