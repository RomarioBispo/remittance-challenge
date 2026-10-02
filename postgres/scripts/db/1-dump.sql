-- CreateTable
CREATE TABLE IF NOT EXISTS natural_person (
                                     "id" TEXT NOT NULL,
                                     "full_name" TEXT NOT NULL,
                                     "email" TEXT NOT NULL,
                                     "password" DATE NOT NULL,
                                     "cpf" TEXT NOT NULL
                                     PRIMARY KEY ("id")
    );

CREATE TABLE IF NOT EXISTS legal_person (
                                              "id" TEXT NOT NULL,
                                              "full_name" TEXT NOT NULL,
                                              "email" TEXT NOT NULL,
                                              "password" DATE NOT NULL,
                                              "cnpj" TEXT NOT NULL
                                              PRIMARY KEY ("id")
    );

CREATE TABLE IF NOT EXISTS idempotency_keys (
                                                "key" TEXT NOT NULL,
                                                "endpoint" TEXT NOT NULL,
                                                "created_at" DATE NOT NULL,
                                                "request_hash" TEXT NOT NULL
);