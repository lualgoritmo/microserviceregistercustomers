CREATE TABLE tb_service_schedule (
  id_service UUID NOT NULL,
   description VARCHAR(255),
   price DECIMAL,
   service_date date,
   service_hours TIMESTAMP WITHOUT TIME ZONE,
   cliente_id UUID,
   CONSTRAINT pk_tb_service_schedule PRIMARY KEY (id_service)
);

CREATE TABLE tb_service_schedule_collaborator (
  address_collaborator_id_service UUID NOT NULL,
   collaborator_id_collaborator UUID NOT NULL
);

ALTER TABLE tb_service_schedule ADD CONSTRAINT FK_TB_SERVICE_SCHEDULE_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES tb_client_user (id_client_user);

ALTER TABLE tb_service_schedule_collaborator ADD CONSTRAINT fk_tbserschcol_on_collaborator FOREIGN KEY (collaborator_id_collaborator) REFERENCES tb_collaborator (id_collaborator);

ALTER TABLE tb_service_schedule_collaborator ADD CONSTRAINT fk_tbserschcol_on_service_schedule FOREIGN KEY (address_collaborator_id_service) REFERENCES tb_service_schedule (id_service);