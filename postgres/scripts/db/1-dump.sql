-- 1. Tabela Pai de Pessoas com Auditoria
CREATE TABLE IF NOT EXISTS  person (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        person_type VARCHAR(2) NOT NULL CHECK (person_type IN ('PF', 'PJ'))
);

-- 2. Tabela de Carteiras
CREATE TABLE IF NOT EXISTS  wallet (
                        id SERIAL PRIMARY KEY,
                        person_id INT UNIQUE NOT NULL,
                        reais_balance NUMERIC(15, 2) DEFAULT 0.00,
                        dollar_balance NUMERIC(15, 2) DEFAULT 0.00,
                        FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

-- 3. Tabela de Pessoa Física
CREATE TABLE IF NOT EXISTS natural_person (
                                id INT PRIMARY KEY,
                                full_name VARCHAR(255) NOT NULL,
                                cpf VARCHAR(14) UNIQUE NOT NULL,
                                FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS legal_person (
                              id INT PRIMARY KEY,
                              company_name VARCHAR(255) NOT NULL,
                              cnpj VARCHAR(18) UNIQUE NOT NULL,
                              FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS remittance (
                            id SERIAL PRIMARY KEY,
                            debitor_wallet_id INT NOT NULL,
                            creditor_wallet_id INT NOT NULL,
                            value NUMERIC(15, 2) NOT NULL CHECK (value > 0),
                            currency VARCHAR(3) NOT NULL CHECK (currency IN ('BRL', 'USD')),
                            remittance_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (debitor_wallet_id) REFERENCES wallet(id),
                            FOREIGN KEY (creditor_wallet_id) REFERENCES wallet(id),
    -- Impede que uma carteira transfira dinheiro para ela mesma
                            CHECK (debitor_wallet_id <> creditor_wallet_id)
);

CREATE TABLE IF NOT EXISTS dollar_quote (
                              id SERIAL PRIMARY KEY,
                              value NUMERIC(15, 2) NOT NULL,
                              quote_date DATE DEFAULT CURRENT_DATE
);