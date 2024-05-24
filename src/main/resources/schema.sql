create table if not exists Taco_Order
(
    id              identity,
    delivery_Name   varchar(255) not null,
    delivery_Street varchar(255) not null,
    delivery_City   varchar(255) not null,
    delivery_State  varchar(255) not null,
    delivery_Zip    varchar(255) not null,
    cc_number       varchar(255) not null,
    cc_expiration   varchar(255) not null,
    cc_cvv          varchar(255) not null,
    placed_at       timestamp    not null
);

create table if not exists Taco
(
    id             identity,
    name           varchar(255) not null,
    taco_order     bigint       not null,
    created_at     timestamp    not null
);

create table if not exists Ingredient
(
    id   identity,
    name varchar(255) not null,
    type varchar(255) not null
);

create table if not exists Ingredient_Ref
(
    id         identity,
    taco       bigint not null,
    ingredient bigint not null
);

alter table Taco
    add foreign key (taco_order) references Taco_Order (id);

alter table Ingredient_Ref
    add foreign key (taco) references Taco (id);

alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient (id);


