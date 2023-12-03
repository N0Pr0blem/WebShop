create table product (
    count integer,
    id bigint not null,
    price float(53),
    company varchar(255),
    description varchar(255),
    image varchar(255),
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table product_seq (
    next_val bigint
) engine=InnoDB;

insert into product_seq values ( 1 );

create table user_role (
    user_id bigint not null,
    roles enum ('ADMIN','USER')
) engine=InnoDB;

create table users (
    active bit not null,
    cart_id bigint,
    id bigint not null,
    email varchar(255),
    password varchar(255),
    phone varchar(255),
    username varchar(255),
    primary key (id)
) engine=InnoDB;

create table carts (
    id bigint not null auto_increment,
    primary key (id)
) engine=InnoDB;

create table cart_product (
    product_id bigint not null,
    cart_id bigint not null
) engine=InnoDB;

create table users_seq (next_val bigint) engine=InnoDB;

insert into users_seq values ( 1 );

alter table users add constraint UK_pnp1baae4enifkkuq2cd01r9l unique (cart_id);

alter table cart_product add constraint FK2kdlr8hs2bwl14u8oop49vrxi foreign key (product_id) references product (id);

alter table cart_product add constraint FK8bhydybldutgf7a82oxkctlgs foreign key (cart_id) references carts (id);

alter table user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users (id);

alter table users add constraint FKdv26y3bb4vdmsr89c9ppnx85w foreign key (cart_id) references carts (id);
