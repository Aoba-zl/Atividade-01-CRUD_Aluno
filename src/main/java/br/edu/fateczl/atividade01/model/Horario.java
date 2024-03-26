package br.edu.fateczl.atividade01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Horario
{
    String codigo, turno;
    Time horario_inicio, horario_termino;
    int numero_aulas;

    public void set_Horario_termino ()
    {
        long tempo_de_aulas = (50 * 60 * 1000) * this.numero_aulas;
        if (this.numero_aulas > 2) tempo_de_aulas += (10 * 60 * 1000); // mais 10 minutos de intervalo
        long horario_em_milisegundos = this.horario_inicio.getTime();
        horario_em_milisegundos += tempo_de_aulas;
        this.horario_termino.setTime(horario_em_milisegundos);
    }
}
