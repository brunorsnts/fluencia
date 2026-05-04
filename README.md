# FluencIA

> Professor de inglês virtual no WhatsApp, powered by IA.

## O que é

FluencIA é um bot de WhatsApp que funciona como um professor de inglês particular. Você manda uma mensagem em inglês e ele responde com correções, sugestões e feedback didático — como um professor real faria, direto no seu WhatsApp.

A ideia é simples: praticar inglês onde você já está, sem precisar abrir um app novo, sem mensalidade, sem horário marcado.

## Como funciona

1. Você manda uma mensagem em inglês no WhatsApp
2. A Evolution API entrega a mensagem via webhook para o FluencIA
3. O FluencIA processa o texto com IA (Groq + llama-3.3-70b)
4. Você recebe de volta correções gramaticais, sugestões de vocabulário e feedback personalizado em português

## Stack

- **Java 21** + **Spring Boot 3.4.5**
- **Spring AI 1.0.0** + **Groq** (llama-3.3-70b-versatile) para processamento e geração de feedback
- **PostgreSQL** para histórico de conversas
- **Evolution API** para integração com WhatsApp
- **Docker Compose** para orquestração dos serviços (Evolution API, Redis, PostgreSQL)

## Variáveis de ambiente

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do PostgreSQL |
| `DB_NAME` | Nome do banco de dados |
| `DB_USER` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `GROQ_API_KEY` | Chave de API do Groq |
| `EVOLUTION_API_KEY` | Chave de API da Evolution API |

## Como rodar

```bash
# Sobe a infraestrutura (Evolution API, Redis, PostgreSQL)
docker compose up -d

# Roda a aplicação Spring Boot
./mvnw spring-boot:run
```

Configure o webhook da Evolution API para apontar para `http://<seu-host>/webhook`.

## Status

| Etapa | Status |
|---|---|
| Infraestrutura básica (Spring Boot + PostgreSQL) | ✅ Concluído |
| Integração WhatsApp (Evolution API) | ✅ Concluído |
| Integração com IA (Spring AI + Groq) | ✅ Concluído |
| Lógica de professor de inglês (prompt) | ✅ Concluído |
| Memória e contexto por usuário | 🔲 Pendente |
| Deploy | 🔲 Pendente |

## Autor

Bruno Santos — [LinkedIn](https://www.linkedin.com/in/bruno-santos-517368206) · [GitHub](https://github.com/brunorsnts)
