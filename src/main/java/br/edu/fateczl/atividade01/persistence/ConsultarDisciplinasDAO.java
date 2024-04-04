package br.edu.fateczl.atividade01.persistence;

import br.edu.fateczl.atividade01.model.Disciplina;
import br.edu.fateczl.atividade01.model.Horario;
import br.edu.fateczl.atividade01.model.MatriculaDisciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultarDisciplinasDAO {


    private GenericDAO gDAO= new GenericDAO();


    public List<MatriculaDisciplina> getDiciplinas(String ra) throws SQLException, ClassNotFoundException {
        List<MatriculaDisciplina> disciplinas= new ArrayList<>();

        Connection c = gDAO.getConnection();

        String sql = """
                select disciplina.codigo,
                           disciplina.nome,
                           matricula_disciplina.estado,
                           horario.horario_inicio,
                           horario.num_aulas
                    from matricula_disciplina, disciplina, horario, matricula
                    where disciplina.codigo = matricula_disciplina.cod_disciplina and
                          horario.id = matricula_disciplina.id_horario and
                          matricula.ra = matricula_disciplina.ra_matricula and
                          matricula_disciplina.estado = 'matriculado' and
                          matricula.ra = ?
                """;
        PreparedStatement ps= c.prepareStatement(sql);
        ps.setString(1, ra);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Disciplina disciplina= new Disciplina();
            Horario horario= new Horario();
            MatriculaDisciplina matriculaDisciplina= new MatriculaDisciplina();

            disciplina.setCodigo(rs.getInt(1));
            disciplina.setNome(rs.getString(2));
            matriculaDisciplina.setSituacao(rs.getString(3));
            horario.setHorario_inicio(rs.getTime(4));
            horario.setNumero_aulas(rs.getInt(5));
//            horario.set_Horario_termino();

            matriculaDisciplina.setDisciplina(disciplina);
            matriculaDisciplina.setHorario(horario);

            disciplinas.add(matriculaDisciplina);
        }
        rs.close();
        ps.close();
        c.close();

        return disciplinas;
    }
}
