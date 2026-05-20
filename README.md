# SGHSS — Sistema de Gestão Hospitalar e de Serviços de Saúde (Rede VidaPlus)

Este repositório contém o código-fonte do Produto Mínimo Viável (MVP) do SGHSS, uma API RESTful de alta criticidade desenvolvida para a unificação e a centralização de registros clínicos dispersos na rede VidaPlus (que integra hospitais, clínicas de bairro, laboratórios e equipes de home care).

O projeto foi estruturado sob os pilares da Engenharia de Software moderna, utilizando Java 17 e Spring Boot 3.x, para aos critérios de avaliação da trilha de Back-end da disciplina Projeto Multidisciplinar.

**ALuna:** Ana Gabriela Brasil Fraiz
**RU:** 4877350
---

## Funcionalidades Principais (Eixos Tecnológicos)

1. **Módulo de Autenticação e Segurança (RBAC):**
    * Endpoint centralizado de login (`/api/auth/login`) com suporte lúdico a tokens criptografados JWT (JSON Web Tokens).
    * Separação de escopo baseada em papéis de atuação: `ROLE_MEDICO` e `ROLE_ADMIN` (Controle de Acesso Baseado em Funções).
2. **Cadastro Centralizado de Pacientes (Compliance LGPD):**
    * Unificação cadastral com camada interna de ofuscação de dados sensíveis (o CPF é criptografado em Base64 antes de atingir a persistência).
    * Disparo síncrono e mandatório de logs de auditoria para controle de criação de registros na tabela `tb_logs_auditoria_lgpd`.
3. **Hub de Consultas e Telemedicina:**
    * Agendamento automatizado de consultas médicas físicas e digitais.
    * Inteligência de negócios integrada: se a flag de telemedicina for ativada (`eTelemedicina: true`), o sistema gera de forma randômica um token securitizado e atribui uma URL exclusiva para a sala virtual de atendimento.
4. **Prontuários Clínicos e Rastreabilidade Digital:**
    * Evolução clínica e amarração de diagnósticos com suporte a Classificação Internacional de Doenças (CID).
    * **Rigor Regulatória (RNF002):** Toda escrita ou leitura de histórico médico aciona de forma transparente mecanismos de rastreamento (Logs), capturando a identidade do operador, a ação executada e o registro afetado.

---

## Pilha Tecnológica (Stack)

* **Linguagem Principal:** Java 17 (LTS)
* **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Gerenciador de Dependências:** Gradle (Groovy)
* **Banco de Dados:** H2 Database (Persistência em memória relacional para validação rápida de MVP)
* **Documentação Viva:** Springdoc OpenAPI / Swagger UI (OpenAPI 3.0)

---

## Arquitetura do Projeto e Estrutura de Pastas

A solução adota o padrão arquitetural em camadas Controller-Service-Repository, garantindo alta coesão, baixo acoplamento e separação clara de responsabilidades. Os arquivos encontram-se estruturados a partir do pacote oficial definido para a aplicação:

Saída de código
Arquivo README-v2.md gravado com sucesso.

```text
src/main/java/com/example/Projeto_Multidisciplinar_SGHSS/
│
├── config/
│   └── SwaggerConfig.java              # Configuração dos metadados globais da especificação OpenAPI
│
├── controller/
│   ├── AuthController.java             # Interceptador de credenciais e geração de JWT simulado
│   ├── PacienteController.java         # Operações de cadastro e unificação de pacientes
│   ├── ConsultaController.java         # Gestão de agendamentos e links de Telemedicina
│   └── ProntuarioController.java       # Barramento de evolução clínica e leitura de dados sensíveis
│
├── dto/
│   └── LoginRequest.java               # Objeto de transferência de dados (DTO) para payloads de entrada
│
├── entity/
│   ├── Paciente.java                   # Modelo relacional da tabela tb_pacientes
│   ├── LogAuditoria.java               # Modelo estruturado para compliance com a LGPD
│   ├── Consulta.java                   # Modelo de dados de agendamento e salas virtuais
│   └── Prontuario.java                 # Modelo clínico de evolução e registros CID
│
├── repository/
│   ├── PacienteRepository.java         # Interface de aplicação CRUD de pacientes via Spring Data JPA
│   ├── LogAuditoriaRepository.java    # Persistência de logs de rastreabilidade
│   ├── ConsultaRepository.java         # Persistência do módulo de agendamento
│   └── ProntuarioRepository.java       # Persistência do histórico clínico
│
└── service/
    ├── PacienteService.java            # Lógica de ofuscação do CPF e gatilho de auditoria
    ├── ConsultaService.java            # Regra de negócio para criação de links de telemedicina via UUID
    └── ProntuarioService.java          # Escrita e leitura clínica sob pr