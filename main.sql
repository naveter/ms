CREATE SEQUENCE public.user_id_seq
  INCREMENT 1 MINVALUE 1
  MAXVALUE 9223372036854775807 START 1
  CACHE 1;
  
CREATE TABLE public."user" (
  id SERIAL,
  name VARCHAR NOT NULL,
  CONSTRAINT user_name_key UNIQUE(name),
  CONSTRAINT user_pkey PRIMARY KEY(id)
) 
WITH (oids = false);

ALTER TABLE public."user"
  OWNER TO postgres;
	
  
insert into public."user" (id, name) values(1, 'Ivan');
insert into public."user" (id, name) values(2, 'Olga');
insert into public."user" (id, name) values(3, 'Mary');