--liquibase formatted sql

--changeset tvlobanov:1
CREATE TABLE dealers
(
    id            BIGSERIAL PRIMARY KEY,
    uid           uuid UNIQUE NOT NULL,
    name          varchar(64) NOT NULL,
    location      varchar(320),
    region        varchar(4)  NOT NULL,
    inn           varchar(16) NOT NULL,
    kpp           varchar(16) NOT NULL,
    create_date   timestamp   NOT NULL,
    create_author varchar     NOT NULL,
    is_deleted    boolean
);

CREATE UNIQUE INDEX ON dealers (uid);


--changeset tvlobanov:2
CREATE TABLE connections
(
    id             BIGSERIAL PRIMARY KEY,
    uid            uuid UNIQUE                    NOT NULL,
    type           varchar(16)                    NOT NULL,
    dealer_id      bigint REFERENCES dealers (id) NOT NULL,
    create_date    timestamp                      NOT NULL,
    create_author  varchar(320)                   NOT NULL,
    is_used        boolean                        NOT NULL,
    last_task_date timestamp
);

CREATE UNIQUE INDEX ON connections (uid);

--changeset tvlobanov:3
CREATE TABLE tasks
(
    id               BIGSERIAL PRIMARY KEY,
    uid              uuid UNIQUE                        NOT NULL,
    connection_id    bigint REFERENCES connections (id) NOT NULL,
    dealer_id        bigint REFERENCES dealers (id)     NOT NULL,
    create_date      timestamp                          NOT NULL,
    author           varchar(320)                       NOT NULL,
    status           varchar(16)                        NOT NULL,
    is_used          boolean,
    task_result      jsonb,
    offers_received  integer,
    offers_published integer
);

CREATE UNIQUE INDEX ON tasks (uid);