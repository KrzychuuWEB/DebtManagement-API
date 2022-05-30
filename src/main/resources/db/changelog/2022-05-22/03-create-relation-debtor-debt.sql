--liquibase formatted sql

--changeset krzysiek:3
ALTER TABLE debts ADD debtor_id BIGINT NULL;
ALTER TABLE debts ADD CONSTRAINT FK_DEBTS_DEBTORS FOREIGN KEY (debtor_id) REFERENCES debtors (id);