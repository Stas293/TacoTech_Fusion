create table if not exists Taco_Order
(
    id              IDENTITY,
    delivery_Name   VARCHAR(255) NOT NULL,
    delivery_Street VARCHAR(255) NOT NULL,
    delivery_City   VARCHAR(255) NOT NULL,
    delivery_State  VARCHAR(255) NOT NULL,
    delivery_Zip    VARCHAR(255) NOT NULL,
    cc_number       VARCHAR(255) NOT NULL,
    cc_expiration   VARCHAR(255) NOT NULL,
    cc_cvv          VARCHAR(255) NOT NULL,
    placed_at       TIMESTAMP    NOT NULL,
    user_id         BIGINT       NOT NULL
);

create table if not exists Taco
(
    id         IDENTITY,
    name       VARCHAR(255) not null,
    taco_order BIGINT       NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

create table if not exists Ingredient
(
    id   IDENTITY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref
(
    id         IDENTITY,
    taco       BIGINT NOT NULL,
    ingredient BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Users
(
    id           IDENTITY,
    username     VARCHAR(255),
    password     VARCHAR(255),
    fullname     VARCHAR(255),
    street       VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    zip          VARCHAR(255),
    phone_number VARCHAR(255)
);

ALTER TABLE Taco
    ADD FOREIGN KEY (taco_order) REFERENCES Taco_Order (id);

ALTER TABLE Ingredient_Ref
    ADD FOREIGN KEY (taco) REFERENCES Taco (id);

ALTER TABLE Ingredient_Ref
    ADD FOREIGN KEY (ingredient) REFERENCES Ingredient (id);

ALTER TABLE Taco_Order
    ADD FOREIGN KEY (user_id) REFERENCES Users (id);


