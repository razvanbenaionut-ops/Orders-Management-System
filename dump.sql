DROP TABLE IF EXISTS public.log CASCADE;
DROP TABLE IF EXISTS public.orders CASCADE;
DROP TABLE IF EXISTS public.product CASCADE;
DROP TABLE IF EXISTS public.client CASCADE;

create table public.client
(
    id   serial
        primary key,
    name varchar(50) not null
);

alter table public.client
    owner to postgres;

create table public.product
(
    id    serial
        primary key,
    name  varchar(50)      not null,
    price double precision not null,
    stock integer          not null
);

alter table public.product
    owner to postgres;

create table public.orders
(
    id          serial
        primary key,
    client_id   integer          not null
        references public.client
            on delete cascade,
    product_id  integer          not null
        references public.product
            on delete cascade,
    total_price double precision not null,
    quantity    integer          not null
);

alter table public.orders
    owner to postgres;

create table public.log
(
    id            serial
        primary key,
    name_client   varchar(50)      not null,
    name_product  varchar(50)      not null,
    quantity      integer          not null,
    price_product double precision not null,
    total_price   double precision not null
);

alter table public.log
    owner to postgres;

