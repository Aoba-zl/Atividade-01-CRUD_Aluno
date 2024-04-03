package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Aluno;
import br.edu.fateczl.atividade01.model.Curso;
import br.edu.fateczl.atividade01.model.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MatriculaDAO //implements ICRUD<Matricula>
{
    GenericDAO gdao;

    public MatriculaDAO(GenericDAO gdao) {
        this.gdao = gdao;
    }

    public Matricula find(Matricula matricula) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String query = "SELECT m.ra, m.cpf_aluno as cpf, a.nome as nome, m.cod_curso as cod_curso, m.pontuacao_vestibular as pont_vest, " +
                "m.posicao_vestibular as pos_vest, m.ano_ingresso as a_ingresso, m.semestre_ingresso as s_ingresso, " +
                "m.ano_limite_graduacao as a_limite, m.semestre_limite_graduacao as s_limite, " +
                "m.matricula_ativa as ativa " +
                "FROM matricula m, aluno a " +
                "WHERE m.cpf_aluno = a.cpf AND m.cpf_aluno = ? ";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, matricula.getAluno().getCpf());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            matricula.setRa(rs.getString("ra"));
            matricula.setPontuacao_vestibular(rs.getInt("pont_vest"));
            matricula.setPosicao_vestibular(rs.getInt("pos_vest"));
            matricula.setAno_ingresso(rs.getInt("a_ingresso"));
            matricula.setSemestre_ingresso(rs.getInt("s_ingresso"));
            matricula.setAno_limite(rs.getInt("a_limite"));
            matricula.setSemestre_limite(rs.getInt("s_limite"));
            matricula.setMatricula_ativa(rs.getBoolean("ativa"));

            Curso curso = new Curso();
            curso.setCodigo(rs.getInt("cod_curso"));
            CursoDAO cursoDAO = new CursoDAO(gdao);
            matricula.setCurso(cursoDAO.find(curso));
        }
        return matricula;
    }

    public String insert(Matricula matricula) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String update(Matricula matricula) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String delete(Matricula matricula) throws SQLException, ClassNotFoundException {
        return null;
    }
}
