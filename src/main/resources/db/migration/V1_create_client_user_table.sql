CREATE TABLE tb_client_user (
  id_client_user UUID NOT NULL,
   name_surname VARCHAR(255),
   date_of_birth date,
   registration_date TIMESTAMP WITHOUT TIME ZONE,
   cpf VARCHAR(255),
   cep VARCHAR(255),
   number_residence VARCHAR(255),
   phone VARCHAR(255),
   rg VARCHAR(255),
   email VARCHAR(255),
   CONSTRAINT pk_tb_client_user PRIMARY KEY (id_client_user)
);