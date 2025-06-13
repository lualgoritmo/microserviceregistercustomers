ALTER TABLE tb_collaborator_schedule
  ADD CONSTRAINT fk_tbcolsch_on_schedule FOREIGN KEY (schedule_id_shedule) REFERENCES tb_service_schedule (id_shedule);
