/*
 * PROCEDURE sp_iud_matricula_disciplina
 *
 * USE Avaliacao_1_Lab_BD
*/

/*
    DROP PROCEDURE sp_iud_matricula_disciplina
*/

CREATE PROCEDURE sp_iud_matricula_disciplina ( @modo CHAR(1), @id INT, @id_horario CHAR(5), @ra_matricula CHAR(9),
                                                @cod_disciplina INT, @ano_matricula INT, @semestre_matricula INT,
                                                @estado VARCHAR(12), @saida VARCHAR(100) OUTPUT )
AS
BEGIN
    SET @modo = UPPER(@modo)
    IF ( @modo = 'I' OR @modo = 'U' OR @modo = 'D' OR @modo = 'A' )
    BEGIN
        IF ( @id IS NULL AND (@modo = 'U' OR @modo = 'D' OR @modo = 'A') )
        BEGIN; RAISERROR ('ID nulo', 16, 1); END

        IF ( (@modo = 'U' OR @modo = 'I') AND ( @id_horario IS NULL OR @ra_matricula IS NULL OR
                                                @cod_disciplina IS NULL OR @ano_matricula IS NULL OR
                                                @semestre_matricula IS NULL OR @estado IS NULL) )

        BEGIN; RAISERROR ('Nao e possivel alterar ou inserir dados nulos', 16, 1); END

        IF ( @modo = 'U' )
        BEGIN
            UPDATE matricula_disciplina
            SET id_horario = @id_horario, ra_matricula = @ra_matricula,
                cod_disciplina = @cod_disciplina, ano_matricula = @ano_matricula,
                semestre_matricula = @semestre_matricula, estado = @estado
            WHERE id =  @id
            SET @saida = 'Matricula atualizada com sucesso'
        END
        ELSE
        BEGIN
            IF ( @modo = 'I' )
            BEGIN
                INSERT INTO matricula_disciplina (id_horario, ra_matricula, cod_disciplina,
                                                  ano_matricula, semestre_matricula, estado) VALUES
                    (@id_horario, @ra_matricula, @cod_disciplina, @ano_matricula, @semestre_matricula, @estado)
                SET @saida = 'Matricula feita com sucesso'
            END
        END
    END
    ELSE
    BEGIN
        RAISERROR ('Modo invalido', 16, 1)
    END
END
