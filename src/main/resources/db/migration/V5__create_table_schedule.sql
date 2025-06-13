CREATE TABLE tb_service_schedule (
  id_shedule UUID NOT NULL,
   description VARCHAR(255),
   price DECIMAL,
   service_date date,
   service_hours time WITHOUT TIME ZONE,
   schedule_task SMALLINT,
   id_client UUID,
   id_address UUID,
   CONSTRAINT pk_tb_service_schedule PRIMARY KEY (id_shedule)
);

ALTER TABLE tb_service_schedule ADD CONSTRAINT uc_tb_service_schedule_id_address UNIQUE (id_address);

ALTER TABLE tb_service_schedule ADD CONSTRAINT FK_TB_SERVICE_SCHEDULE_ON_ID_ADDRESS FOREIGN KEY (id_address) REFERENCES tb_address_client (id_address);

ALTER TABLE tb_service_schedule ADD CONSTRAINT FK_TB_SERVICE_SCHEDULE_ON_ID_CLIENT FOREIGN KEY (id_client) REFERENCES tb_client_user (id_client_user);