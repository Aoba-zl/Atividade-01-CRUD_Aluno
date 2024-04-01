package br.edu.fateczl.atividade01.controller;

import br.edu.fateczl.atividade01.model.*;
import br.edu.fateczl.atividade01.persistence.DisciplinaDAO;
import br.edu.fateczl.atividade01.persistence.GenericDAO;
import br.edu.fateczl.atividade01.persistence.MatriculaDAO;
import br.edu.fateczl.atividade01.persistence.MatriculaDisciplinaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/realizar_matricula")
public class RealizarMatriculaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public RealizarMatriculaServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String ra = request.getParameter("ra");
        String cod_disc = request.getParameter("cd");
        String nome_disc = request.getParameter("n");
        String dia = request.getParameter("d");
        String acao = request.getParameter("acao");
        String id_matricula = request.getParameter("id");

        String saida = "";
        String erro = "";
        String matricula_valida;
        String busca_valida = "";
        String carga_horaria;

        List<Horario> horarios = new ArrayList<>();
        Disciplina disciplina_selecionada = new Disciplina();
        Matricula matricula = new Matricula();
        List<MatriculaDisciplina> matriculasDisciplina = new ArrayList<>();
        matricula.setRa(ra);

        try
        {
            if (acao.equalsIgnoreCase("selecionar") || acao.equalsIgnoreCase("alterar"))
                busca_valida = "valido";
            if (acao.equalsIgnoreCase("selecionar"))
            {
                acao="SELECIONAR";
                matriculasDisciplina = listarDisciplinasDisponiveis(ra);
                carga_horaria = "%";
                if (cod_disc != null && !cod_disc.isEmpty())
                {
                    disciplina_selecionada = buscarDisciplina(Integer.parseInt(cod_disc));
                    carga_horaria = String.valueOf(disciplina_selecionada.getHoras_semanais());
                }
                horarios = listarHorariosDisponiveis(ra, Integer.parseInt(dia), carga_horaria);
                erro = horarios.isEmpty() ? "Não há horários disponives para " + get_dia_semana(Integer.parseInt(dia)): "";
            }
            if (acao.equalsIgnoreCase("alterar"))
            {
                acao="ALTERAR";
                matriculasDisciplina = listarDisciplinasMatriculadas(ra);
                carga_horaria = "%";
                if (cod_disc != null && !cod_disc.isEmpty())
                {
                    disciplina_selecionada = buscarDisciplina(Integer.parseInt(cod_disc));
                    carga_horaria = String.valueOf(disciplina_selecionada.getHoras_semanais());
                }
                horarios = listarHorariosDisponiveis(ra, Integer.parseInt(dia), carga_horaria);
                erro = horarios.isEmpty() ? "Não há horários disponives para " + get_dia_semana(Integer.parseInt(dia)): "";
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            matricula_valida = !horarios.isEmpty() ? "valido": "";
            request.setAttribute("matricula_valida", matricula_valida);
            request.setAttribute("busca_valida", busca_valida);
            request.setAttribute("horarios", horarios);
            request.setAttribute("matricula", matricula);
            request.setAttribute("id_matricula", id_matricula);
            request.setAttribute("cod_disc", cod_disc);
            request.setAttribute("nome_disc", nome_disc);
            request.setAttribute("dia", dia);
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("acao", acao);
            request.setAttribute("matriculasDisciplina", matriculasDisciplina);
            RequestDispatcher rd = request.getRequestDispatcher("aluno_realizar_matricula.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String ra = request.getParameter("ra");
        String dia_semana = request.getParameter("dia_semana");
        // dados de matricula
        String id_matricula = request.getParameter("id_matricula");
        String cod_disc = request.getParameter("cod_disc");
        String nome_disc = request.getParameter("nome_disc");
        String horario = request.getParameter("horario");

        String saida = "";
        String erro = "";
        String acao = request.getParameter("acao") == null? "" : request.getParameter("acao");
        String busca_valida;

        List<MatriculaDisciplina> matriculasDisciplina = new ArrayList<>();
        Matricula matricula = new Matricula();

        try
        {
            if (!cmd.isEmpty())
            {
                matricula.setRa(ra);
                matricula = buscarMatricula(matricula);
            }
            if (cmd.equalsIgnoreCase("Buscar"))
            {
                cod_disc = "";
                nome_disc = "";
                horario = "";
                matricula = buscarMatricula(matricula);
                matriculasDisciplina = new ArrayList<>();
                if (matricula.toString() == null)
                    erro = "Matricula não encontrada";
                else
                    saida = "Olá " + matricula.getAluno().getNome() + "!";
            }
            if (cmd.equalsIgnoreCase("Realizar Matricula"))
            {
                saida = realizarMatricula(matricula, cod_disc, horario, dia_semana);
                cod_disc = "";
                nome_disc = "";
                horario = "";
            }
            if (cmd.equalsIgnoreCase("Alterar Matricula"))
            {
                saida = alterarMatricula(matricula, id_matricula, cod_disc, horario, dia_semana);
                cod_disc = "";
                nome_disc = "";
                horario = "";
            }
            if (cmd.equalsIgnoreCase("Listar Disciplinas Disponiveis"))
            {
                matriculasDisciplina = listarDisciplinasDisponiveis(ra);
                acao="SELECIONAR";
            }
            if (cmd.equalsIgnoreCase("Listar Disciplinas Matriculadas"))
            {
                matriculasDisciplina = listarDisciplinasMatriculadas(ra);
                acao="ALTERAR";
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            busca_valida = (dia_semana != null && matricula.getRa() != null)?"valido":"";

            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("acao", acao);
            request.setAttribute("busca_valida", busca_valida);
            request.setAttribute("matricula", matricula);
            request.setAttribute("matriculasDisciplina", matriculasDisciplina);
            //Tabela matricula
            request.setAttribute("cod_disc", cod_disc);
            request.setAttribute("nome_disc", nome_disc);
            request.setAttribute("horario", horario);

            RequestDispatcher rd = request.getRequestDispatcher("aluno_realizar_matricula.jsp");
            rd.forward(request, response);
        }
    }

    private String alterarMatricula(Matricula matricula, String idMatricula,
                                    String codDisc, String horario, String diaSemana)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);

        Disciplina d = new Disciplina();
        d.setCodigo(Integer.parseInt(codDisc));
        Horario h = new Horario();
        h.setCodigo(horario);
        MatriculaDisciplina matriculaDisciplina = new MatriculaDisciplina();
        matriculaDisciplina.setId(Integer.parseInt(idMatricula));
        matriculaDisciplina.setDisciplina(d);
        matriculaDisciplina.setHorario(h);
        matriculaDisciplina.setDia_semana(Integer.parseInt(diaSemana));
        return md_dao.update(matricula, matriculaDisciplina);
    }

    private Matricula buscarMatricula(Matricula matricula)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO(gdao);
        return matriculaDAO.buscarMatricula(matricula);
    }

    private String realizarMatricula(Matricula matricula, String codDisc, String codHorario, String diaSemana)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);

        Disciplina d = new Disciplina();
        d.setCodigo(Integer.parseInt(codDisc));
        Horario h = new Horario();
        h.setCodigo(codHorario);
        MatriculaDisciplina matriculaDisciplina = new MatriculaDisciplina();
        matriculaDisciplina.setDisciplina(d);
        matriculaDisciplina.setHorario(h);
        matriculaDisciplina.setDia_semana(Integer.parseInt(diaSemana));
        return md_dao.insert(matricula, matriculaDisciplina);
    }

    private List<MatriculaDisciplina> listarDisciplinasMatriculadas(String ra)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);
        return md_dao.list_disciplinas_matriculadas(ra);
    }

    private List<MatriculaDisciplina> listarDisciplinasDisponiveis(String ra)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);
        return md_dao.list_disciplinas_disponiveis(ra);
    }
    private List<Horario> listarHorariosDisponiveis(String ra, int diaSemana, String carga_horaria_disciplina)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        MatriculaDisciplinaDAO md_dao = new MatriculaDisciplinaDAO(gdao);
        return md_dao.list_horarios_disponiveis(ra, diaSemana, carga_horaria_disciplina);
    }

    private Disciplina buscarDisciplina(int cod_disciplina)
            throws SQLException, ClassNotFoundException
    {
        GenericDAO gdao = new GenericDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(gdao);
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(cod_disciplina);
        return disciplinaDAO.buscarDisciplina(disciplina);
    }

    private String get_dia_semana(int dia_numerico)
    {
        return switch (dia_numerico) {
            case 2 -> "Segunda-Feira";
            case 3 -> "Terça-Feira";
            case 4 -> "Quarta-Feira";
            case 5 -> "Quinta-Feira";
            case 6 -> "Sexta-Feira";
            case 7 -> "Sábado";
            default -> "Domingo";
        };
    }
}