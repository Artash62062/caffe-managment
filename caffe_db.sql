drop table if exists admins;
create table admins
(
    id         bigint not null auto_increment,
    updated_at datetime(6),
    created_at datetime(6),
    login      varchar(255),
    name       varchar(255),
    password   varchar(255),
    role       varchar(255),
    primary key (id)
);
drop table if exists mangaers;
create table managers
(
    id           bigint not null auto_increment,
    updated_at   datetime(6),
    created_at   datetime(6),
    login        varchar(255),
    name         varchar(255),
    password     varchar(255),
    role         varchar(255),
    manager_code varchar(255),
    primary key (id)
);
drop table if exists products;
create table products
(
    id         bigint not null auto_increment,
    updated_at datetime(6),
    created_at datetime(6),
    name       varchar(255),
    order_id   bigint,
    primary key (id)
);
drop table if exists orders;
create table orders
(
    id             bigint not null auto_increment,
    updated_at     datetime(6),
    created_at     datetime(6),
    order_state    integer,
    product_amount integer,
    table_id       bigint,
    primary key (id)
);
drop table if exists tables;
create table tables
(
    id         bigint not null auto_increment,
    updated_at datetime(6),
    created_at datetime(6),
    waiter_id  bigint,
    primary key (id)
);
drop table if exists waiter;
create table waiter
(
    id                   bigint not null auto_increment,
    updated_at           datetime(6),
    created_at           datetime(6),
    login                varchar(255),
    name                 varchar(255),
    password             varchar(255),
    role                 varchar(255),
    serving_tables_count integer,
    primary key (id)
);
drop table if exists product_in_order;
create table product_in_order
(
    id                     bigint not null auto_increment,
    updated_at             datetime(6),
    created_at             datetime(6),
    amount                 integer,
    product_in_order_state integer,
    order_id               bigint,
    product_id             bigint,
    primary key (id)
);

drop table if exists orders_products;

create table orders_products
(
    orders_id    bigint  not null,
    products_id  bigint  not null,
    products_key integer not null,
    primary key (orders_id, products_key)
);

drop table if exists tables_orders;

create table tables_orders
(
    tables_id bigint not null,
    orders_id bigint not null
);
drop table if exists waiter_tables;
create table waiter_tables
(
    waiter_id bigint not null,
    tables_id bigint not null
);

alter table admins
    add constraint unique (login);

alter table managers
    add constraint unique (login);
alter table managers
    add constraint unique (manager_code);

alter table orders_products
    add constraint unique (products_id);
alter table tables_orders
    add constraint unique (orders_id);

alter table waiter
    add constraint unique (login);

alter table waiter_tables
    add constraint unique (tables_id);

alter table orders
    add constraint foreign key (table_id) references tables (id);

alter table orders_products
    add constraint foreign key (products_id) references products (id);
alter table orders_products
    add constraint foreign key (orders_id) references orders (id);

alter table product_in_order
    add constraint foreign key (order_id) references orders (id);
alter table product_in_order
    add constraint foreign key (product_id) references products (id);

alter table products
    add constraint foreign key (order_id) references orders (id);
alter table tables
    add constraint foreign key (waiter_id) references waiter (id);

alter table tables_orders
    add constraint foreign key (orders_id) references orders (id);

alter table tables_orders
    add constraint foreign key (tables_id) references tables (id);

alter table waiter_tables
    add constraint foreign key (tables_id) references tables (id);

alter table waiter_tables
    add constraint foreign key (waiter_id) references waiter (id);