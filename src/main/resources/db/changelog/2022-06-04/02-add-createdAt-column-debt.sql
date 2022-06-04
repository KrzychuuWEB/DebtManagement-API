--liquibase formatted sql

--changeset krzysiek:6
ALTER TABLE debts ADD created_at datetime NULL;