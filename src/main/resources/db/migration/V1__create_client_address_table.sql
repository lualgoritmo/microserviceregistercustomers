CREATE TABLE tb_address_client (
  id_address UUID NOT NULL,
   cep VARCHAR(255),
   road VARCHAR(255),
   city VARCHAR(255),
   number_residence VARCHAR(255),
   complement VARCHAR(255),
   uf VARCHAR(255),
   id_client UUID,
   CONSTRAINT pk_tb_addressclient PRIMARY KEY (id_address)
);

ALTER TABLE tb_address_client ADD CONSTRAINT FK_TB_ADDRESSCLIENT_ON_ID_CLIENT FOREIGN KEY (id_client) REFERENCES client_user (id_client_user);