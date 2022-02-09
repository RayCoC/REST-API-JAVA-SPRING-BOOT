create table bonsai (
    id uuid primary key,
    name varchar(100) not null,
    species varchar(100),
    acquisition_date date,
    acquisition_age integer
    age integer,

);
create table owner(id uuid primary key, name varchar(100));
alter table bonsai add column owner_id uuid, add constraint fk_bonsai_owner foreign key (owner_id) references owner(id);

