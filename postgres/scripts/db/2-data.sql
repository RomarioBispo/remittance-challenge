INSERT INTO person (id, email, password, person_type) VALUES
                                                          (1, 'joao.silva@email.com', 'senha123', 'PF'),
                                                          (2, 'maria.santos@email.com', 'senha123', 'PF'),
                                                          (3, 'empresa.tech@email.com', 'senha123', 'PJ');

INSERT INTO natural_person (id, full_name, cpf) VALUES
                                                    (1, 'João da Silva', '12345678901'),
                                                    (2, 'Maria dos Santos', '98765432109');

INSERT INTO legal_person (id, company_name, cnpj) VALUES
    (3, 'Tech Solutions Ltda', '12345678000199');

INSERT INTO wallet (id, person_id, reais_balance, dollar_balance) VALUES
                                                                      (1, 1, 5000.00, 200.00),
                                                                      (2, 2, 12500.50, 1000.00),
                                                                      (3, 3, 50000.00, 5000.00);