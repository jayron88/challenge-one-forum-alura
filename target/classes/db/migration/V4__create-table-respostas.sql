CREATE TABLE respostas (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  mensagem TEXT,
  topico_id BIGINT NOT NULL,
  data_criacao DATETIME NOT NULL,
  autor_id BIGINT NOT NULL,
  solucao BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (topico_id) REFERENCES topicos(id),
  FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);