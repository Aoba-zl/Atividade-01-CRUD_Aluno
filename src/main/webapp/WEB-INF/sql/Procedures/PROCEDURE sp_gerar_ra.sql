/*
 * PROCEDURE sp_gerar_ra
 *
 * USE Avaliacao_1_Lab_BD
*/

/*
DROP PROCEDURE sp_gerar_ra
*/

CREATE PROCEDURE sp_gerar_ra( @ano_ingresso INT, @semestre_ingresso INT, @novo_ra CHAR(9) OUTPUT )
AS
BEGIN
    SET @novo_ra = CAST(@ano_ingresso AS VARCHAR(10)) +
                   CAST(@semestre_ingresso AS CHAR(1)) +
                   CAST(CAST(RAND() * 10 AS INT) AS CHAR(1)) +
                   CAST(CAST(RAND() * 10 AS INT) AS CHAR(1)) +
                   CAST(CAST(RAND() * 10 AS INT) AS CHAR(1)) +
                   CAST(CAST(RAND() * 10 AS INT) AS CHAR(1))

    -- validar se ra é duplicado

END
