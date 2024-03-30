package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisciplinaDAO
{
    GenericDAO gdao;

    public DisciplinaDAO(GenericDAO gdao) {
        this.gdao = gdao;
    }

    public Disciplina buscarDisciplina(Disciplina disciplina) throws SQLException, ClassNotFoundException
    {
        Connection con = gdao.getConnection();
        String query = "SELECT codigo, nome, horas_semanais FROM disciplina WHERE codigo=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, disciplina.getCodigo());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            disciplina.setCodigo(rs.getInt("codigo"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setHoras_semanais(rs.getInt("horas_semanais"));
        }

        return disciplina;
    }

}
