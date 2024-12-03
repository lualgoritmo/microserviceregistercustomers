CREATE TABLE tb_collaborator (
  id_collaborator UUID NOT NULL,
   name VARCHAR(255),
   date_birth TIMESTAMP WITHOUT TIME ZONE,
   gender VARCHAR(255),
   registration_date TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_tb_collaborator PRIMARY KEY (id_collaborator)
);