CREATE SEQUENCE public.category_id_seq
  INCREMENT 1 MINVALUE 1
  MAXVALUE 9223372036854775807 START 1
  CACHE 1;

CREATE TABLE public.category (
  id INTEGER DEFAULT nextval('category_id_seq'::regclass) NOT NULL,
  name VARCHAR(100) NOT NULL
) 
WITH (oids = false);

ALTER TABLE public.category
  ADD CONSTRAINT category_pkey 
    PRIMARY KEY (id) NOT DEFERRABLE;

ALTER TABLE public.category
  OWNER TO postgres;
  
insert into public.category (id, name) values(1, 'Music and Movie');
insert into public.category (id, name) values(2, 'Sport and Hunting');
insert into public.category (id, name) values(3, 'Avto and Moto');