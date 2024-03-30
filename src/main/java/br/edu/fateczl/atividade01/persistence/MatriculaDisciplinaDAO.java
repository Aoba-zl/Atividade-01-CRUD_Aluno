package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Disciplina;
import br.edu.fateczl.atividade01.model.Horario;
import br.edu.fateczl.atividade01.model.Matricula;
import br.edu.fateczl.atividade01.model.MatriculaDisciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDisciplinaDAO
{
    GenericDAO gdao;

    public MatriculaDisciplinaDAO(GenericDAO gdao)
    {
        this.gdao = gdao;
    }

    public String insert(Matricula matricula, MatriculaDisciplina matriculaDisciplina)
            throws SQLException, ClassNotFoundException
    {
        return iu_matricula_disc("I", matricula, matriculaDisciplina);
    }

    public String update(Matricula matricula, MatriculaDisciplina matriculaDisciplina)
            throws SQLException, ClassNotFoundException
    {
        return iu_matricula_disc("U", matricula, matriculaDisciplina);
    }

    private String iu_matricula_disc(String modo, Matricula matricula, MatriculaDisciplina matriculaDisciplina)
            throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String query = "{CALL sp_iud_matricula_disciplina (?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = c.prepareCall(query);
        cs.setString(1, modo);
        cs.setInt(2, matriculaDisciplina.getId());
        cs.setString(3, matriculaDisciplina.getHorario().getCodigo());
        cs.setString(4, matricula.getRa());
        cs.setInt(5, matricula.getCurso().getCodigo());
        cs.setInt(6, matriculaDisciplina.getAno());
        cs.setInt(7, matriculaDisciplina.getSemestre());
        cs.setString(8, matriculaDisciplina.getSituacao());
        cs.registerOutParameter(9, Types.VARCHAR);
        cs.execute();
        String saida = cs.getString(9);

        cs.close();
        c.close();
        return saida;
    }

    public List<Horario> list_horarios_disponiveis(String ra, int dia, String carga_disciplina)
            throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String query = "SELECT fn.id AS id ,fn.horario_inicio AS horario_inicio, fn.numero_aulas AS numero_aulas, " +
                "fn.horario_termino AS horario_termino FROM fn_horarios_disponiveis (?, ?, ?) AS fn ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, dia);
        ps.setString(2, ra);
        ps.setString(3, carga_disciplina);

        List<Horario> horarios = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Horario horario = new Horario();
            horario.setCodigo(rs.getString("id"));
            horario.setHorario_inicio(rs.getTime("horario_inicio"));
            horario.setHorario_termino(rs.getTime("horario_termino"));
            horario.setNumero_aulas(rs.getInt("numero_aulas"));
            horarios.add(horario);
        }

        return horarios;
    }

    public List<MatriculaDisciplina> list_disciplinas_disponiveis(String ra)
            throws SQLException, ClassNotFoundException
    {
        Connection con = gdao.getConnection();
        String query = "SELECT fn.codigo, fn.nome, fn.situacao FROM fn_disciplinas_disponiveis (?) AS fn";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, ra);
        ResultSet rs = ps.executeQuery();
        List<MatriculaDisciplina> disciplinas = new ArrayList<>();

        while (rs.next())
        {
            Disciplina disciplina = new Disciplina();
            MatriculaDisciplina matriculaDisciplina = new MatriculaDisciplina();
            disciplina.setCodigo(rs.getInt("codigo"));
            disciplina.setNome(rs.getString("nome"));
            matriculaDisciplina.setSituacao(rs.getString("situacao"));
            matriculaDisciplina.setDisciplina(disciplina);

            disciplinas.add(matriculaDisciplina);
        }

        return disciplinas;
    }

    public List<MatriculaDisciplina> list_disciplinas_matriculadas(String ra)
            throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT md.id AS id, d.codigo AS codigo, d.nome AS nome, md.estado AS situacao ");
        query.append("FROM matricula_disciplina md, disciplina d ");
        query.append("WHERE md.ra_matricula = ? AND md.estado LIKE 'matriculado' ");

        PreparedStatement ps = c.prepareCall(query.toString());
        ps.setString(1, ra);

        List<MatriculaDisciplina> disciplinas = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            MatriculaDisciplina matricula = new MatriculaDisciplina();
            Disciplina disciplina = new Disciplina();
            disciplina.setCodigo(rs.getInt("codigo"));
            disciplina.setNome(rs.getString("nome"));
            matricula.setId(rs.getInt("id"));
            matricula.setSituacao(rs.getString("situacao"));
            matricula.setDisciplina(disciplina);
            disciplinas.add(matricula);
        }

        return disciplinas;
    }

}
