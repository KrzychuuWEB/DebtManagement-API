--liquibase formatted sql

--changeset krzysiek:1
CREATE TABLE debtors (
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   CONSTRAINT pk_debtors PRIMARY KEY (id)
);