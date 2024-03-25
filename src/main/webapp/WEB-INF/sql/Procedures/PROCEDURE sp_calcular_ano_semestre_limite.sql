/*
 * PROCEDURE sp_calcular_ano_semestre_limite
 *
 * USE Avaliacao_1_Lab_BD
*/

/*
DROP PROCEDURE sp_calcular_ano_semestre_limite
*/


CREATE PROCEDURE sp_calcular_ano_semestre_limite ( @ano_ingresso INT, @semestre_ingresso INT,
                                                   @ano_limite_graduacao INT OUTPUT ,
                                                   @semestre_limite_graduacao INT OUTPUT )
AS
BEGIN
    SET @ano_limite_graduacao = @ano_ingresso + 5
    SET @semestre_limite_graduacao = @semestre_ingresso + 5
END
