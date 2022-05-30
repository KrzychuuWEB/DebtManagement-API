--liquibase formatted sql

--changeset krzysiek:2
CREATE TABLE debts (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   price DECIMAL NULL,
   is_devoted BIT(1) NULL,
   CONSTRAINT pk_debts PRIMARY KEY (id)
);