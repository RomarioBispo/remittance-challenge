-- 1. Tabela Pai de Pessoas com Auditoria
CREATE TABLE person (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        person_type VARCHAR(2) NOT NULL CHECK (person_type IN ('PF', 'PJ')),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela de Carteiras
CREATE TABLE wallet (
                        id SERIAL PRIMARY KEY,
                        person_id INT UNIQUE NOT NULL,
                        reais_balance NUMERIC(15, 2) DEFAULT 0.00 CHECK (reais_balance >= 0),
                        dollar_balance NUMERIC(15, 2) DEFAULT 0.00 CHECK (dollar_balance >= 0),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

-- 3. Tabela de Pessoa Física
CREATE TABLE natural_person (
                                id INT PRIMARY KEY,
                                full_name VARCHAR(255) NOT NULL,
                                cpf VARCHAR(14) UNIQUE NOT NULL,
                                FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);

-- 4. Tabela de Pessoa Jurídica
CREATE TABLE legal_person (
                              id INT PRIMARY KEY,
                              company_name VARCHAR(255) NOT NULL,
                              cnpj VARCHAR(18) UNIQUE NOT NULL,
                              FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);

-- 5. Tabela de Remessas / Transferências robusta
CREATE TABLE remittance (
                            id SERIAL PRIMARY KEY,
                            debitor_wallet_id INT NOT NULL,
                            creditor_wallet_id INT NOT NULL,
                            value NUMERIC(15, 2) NOT NULL CHECK (value > 0),
                            currency VARCHAR(3) NOT NULL CHECK (currency IN ('BRL', 'USD')),
                            status VARCHAR(20) DEFAULT 'COMPLETED' CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'CANCELLED')),
                            remittance_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (debitor_wallet_id) REFERENCES wallet(id),
                            FOREIGN KEY (creditor_wallet_id) REFERENCES wallet(id),
    -- Impede que uma carteira transfira dinheiro para ela mesma
                            CHECK (debitor_wallet_id <> creditor_wallet_id)
);


-- 5. Tabela de quote
CREATE TABLE dollar_quote (
                            id SERIAL PRIMARY KEY,
                            value NUMERIC(15, 2) NOT NULL,
                            quote_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);