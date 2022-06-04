--liquibase formatted sql

--changeset krzysiek:5
ALTER TABLE debtors ADD created_at datetime NULL;