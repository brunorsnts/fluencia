# FluencIA

> Professor de inglês virtual no WhatsApp, powered by IA.

## O que é

FluencIA é um bot de WhatsApp que funciona como um professor de inglês particular. Você manda uma mensagem em inglês e ele responde com correções, sugestões e feedback didático — como um professor real faria, direto no seu WhatsApp.

A ideia é simples: praticar inglês onde você já está, sem precisar abrir um app novo, sem mensalidade, sem horário marcado.

## Como funciona

1. Você manda uma mensagem em inglês no WhatsApp
2. A Evolution API entrega a mensagem via webhook para o FluencIA
3. O FluencIA processa o texto com IA (Anthropic Claude Haiku) mantendo o histórico da conversa por usuário
4. Você recebe de volta correções gramaticais, sugestões de vocabulário e feedback personalizado em português

## Stack

- **Java 21** + **Spring Boot 3.4.5**
- **LangChain4j 1.14.1** + **Anthropic** (claude-haiku-4-5) para processamento e geração de feedback
- **PostgreSQL** para persistência de dados
- **Evolution API** para integração com WhatsApp
- **Docker Compose** para orquestração dos serviços (Evolution API, Redis, PostgreSQL)

## Variáveis de ambiente

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do PostgreSQL |
| `DB_NAME` | Nome do banco de dados |
| `DB_USER` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `ANTHROPIC_API_KEY` | Chave de API da Anthropic |
| `AUTHENTICATION_API_KEY` | Chave de API da Evolution API |

## Como rodar

```bash
# Sobe a infraestrutura (Evolution API, Redis, PostgreSQL da Evolution API e do FluencIA)
docker compose up -d

# Roda a aplicação Spring Boot
./mvnw spring-boot:run
```

Configure o webhook da instância na Evolution API para apontar para `http://<seu-host>/webhook`.

> **Nota:** Se a Evolution API rodar via Docker e o Spring Boot rodar direto no host, use o IP do gateway da rede Docker no lugar de `localhost` (ex: `http://172.18.0.1:8080/webhook`). No Docker Desktop, use `http://host.docker.internal:8080/webhook`.

## Status

| Etapa | Status |
|---|---|
| Infraestrutura básica (Spring Boot + PostgreSQL) | ✅ Concluído |
| Integração WhatsApp (Evolution API) | ✅ Concluído |
| Integração com IA (LangChain4j + Anthropic) | ✅ Concluído |
| Lógica de professor de inglês (prompt) | ✅ Concluído |
| Memória e contexto por usuário | ✅ Concluído |
| Deploy | 🔲 Pendente |

## Autor

Bruno Santos — [LinkedIn](https://www.linkedin.com/in/bruno-santos-517368206) · [GitHub](https://github.com/brunorsnts)
