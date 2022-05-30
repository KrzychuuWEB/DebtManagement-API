--liquibase formatted sql

--changeset krzysiek:4
ALTER TABLE debtors ADD is_enabled BIT(1) NULL;