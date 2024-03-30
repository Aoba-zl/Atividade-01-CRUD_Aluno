package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Aluno;
import br.edu.fateczl.atividade01.model.Curso;
import br.edu.fateczl.atividade01.model.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatriculaDAO
{
    GenericDAO gdao;

    public MatriculaDAO(GenericDAO gdao) {
        this.gdao = gdao;
    }

    public Matricula buscarMatricula (Matricula matricula) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("SELECT m.ra AS ra, m.cod_curso AS cod_curso, a.nome AS nome FROM matricula m, aluno a ");
        query.append("WHERE m.cpf_aluno = a.cpf AND m.ra = ?");
        PreparedStatement ps = c.prepareStatement(query.toString());
        ps.setString(1, matricula.getRa());
        ResultSet rs = ps.executeQuery();
        matricula = new Matricula();
        if (rs.next())
        {
            matricula.setRa(rs.getString("ra"));
            Curso curso = new Curso();
            curso.setCodigo(rs.getInt("cod_curso"));
            matricula.setCurso(curso);
            Aluno aluno = new Aluno();
            aluno.setNome(rs.getString("nome"));
            matricula.setAluno(aluno);
        }
        return matricula;
    }
}
