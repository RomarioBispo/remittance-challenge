-- Inserindo uma Pessoa Física (PF) e sua respectiva Wallet
WITH new_person AS (
INSERT INTO person (email, password, person_type)
VALUES ('joao.silva@email.com', 'hash_senha_segura_123', 'PF')
    RETURNING id
    )
INSERT INTO natural_person (id, full_name, cpf)
SELECT id, 'João da Silva', '123.456.789-01'
FROM new_person;

-- Criando a Wallet para o João (Pessoa Física)
-- Descobrimos o id da pessoa através do email ou assumimos o id gerado (ex: id 1)
INSERT INTO wallet (person_id, reais_balance, dollar_balance)
VALUES (
           (SELECT id FROM person WHERE email = 'joao.silva@email.com'),
           1500.50,
           100.00
       );


----------------------------------------------------------------------


-- Inserindo uma Pessoa Jurídica (PJ) e sua respectiva Wallet
WITH new_person AS (
INSERT INTO person (email, password, person_type)
VALUES ('contato@empresateste.com', 'hash_senha_segura_456', 'PJ')
    RETURNING id
    )
INSERT INTO legal_person (id, company_name, cnpj)
SELECT id, 'Empresa Teste Ltda', '12.345.678/0001-99'
FROM new_person;

-- Criando a Wallet para a Empresa Teste (Pessoa Jurídica)
INSERT INTO wallet (person_id, reais_balance, dollar_balance)
VALUES (
           (SELECT id FROM person WHERE email = 'contato@empresateste.com'),
           50000.00,
           2500.00
       );