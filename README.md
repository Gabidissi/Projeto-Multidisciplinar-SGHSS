# SGHSS — Sistema de Gestão Hospitalar e de Serviços de Saúde (Rede VidaPlus)

Este repositório contém o código-fonte do Produto Mínimo Viável (MVP) do SGHSS, uma API RESTful de alta criticidade desenvolvida para a unificação e a centralização de registros clínicos dispersos na rede VidaPlus (que integra hospitais, clínicas de bairro, laboratórios e equipes de home care).

O projeto foi estruturado sob os pilares da Engenharia de Software moderna, utilizando Java 17 e Spring Boot 3.x, para aos critérios de avaliação da trilha de Back-end da disciplina Projeto Multidisciplinar.

**ALuna:** Ana Gabriela Brasil Fraiz
**RU:** 4877350
---
## Funcionalidades e Requisitos Implementados

1. **Autenticação e Controle de Acesso (RBAC):**
   * Endpoint de login (`/api/auth/login`) simulando a checagem de credenciais corporativas.
   * Emissão de token estático para validação dos cabeçalhos das requisições e atribuição de perfis de acesso (`ROLE_MEDICO` e `ROLE_ADMIN`).
2. **Cadastro Unificado de Pacientes (Compliance LGPD):**
   * Atendimento ao requisito não funcional RNF001. A camada de serviço captura o CPF bruto do paciente e realiza a codificação em Base64 antes de salvar na base de dados, mascarando informações sensíveis.
3. **Hub de Consultas e Telemedicina:**
   * Mapeamento lógico estruturado para agendamentos. Caso a flag de telemedicina seja enviada como verdadeira (`eTelemedicina: true`), o sistema aciona um gerador de UUID para criar um link seguro de sala virtual de forma automática.
4. **Prontuários e Rastreabilidade Digital (RNF002):**
   * Persistência de evoluções médicas vinculadas ao ID do paciente e suporte a diagnóstico CID.
   * Integration síncrona com a tabela de auditoria (`tb_logs_auditoria_lgpd`). Toda operação de escrita ou leitura dispara um gatilho que registra o operador, a ação e o carimbo de data/hora atual do servidor.

---

## "Tech Stack" Adotada

* **Linguagem:** Java 17 (LTS)
* **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Gerenciador de Escopo:** Gradle (Groovy)
* **Banco de Dados:** H2 Database (Execução em memória para testes ágeis de MVP)
* **Documentação:** Springdoc OpenAPI 3.0 (Swagger UI)

---

## Arquitetura do Projeto e Estrutura de Pastas

A solução adota o padrão arquitetural em camadas Controller-Service-Repository, garantindo alta coesão, baixo acoplamento e separação clara de responsabilidades. Os arquivos encontram-se estruturados a partir do pacote oficial definido para a aplicação:


```text
src/main/java/com/example/Projeto_Multidisciplinar_SGHSS/
│
├── config/
│   └── SwaggerConfig.java              # Configura os metadados e o cadeado de seguranca do JWT
│
├── controller/
│   ├── AuthController.java             # Endpoint de login e emissao do token de teste
│   ├── PacienteController.java         # Roteamento do cadastro e listagem de pacientes
│   ├── ConsultaController.java         # Roteamento de agendamentos e regras de telemedicina
│   └── ProntuarioController.java       # Roteamento de evolucoes clinicas e historicos
│
├── dto/
│   └── LoginRequest.java               # Classe de transferencia para recebimento de credenciais
│
├── entity/
│   ├── Paciente.java                   # Modelo relacional da tabela tb_pacientes
│   ├── LogAuditoria.java               # Modelo relacional para logs da LGPD
│   ├── Consulta.java                   # Modelo relacional para agendamentos
│   └── Prontuario.java                 # Modelo relacional para dados de saude
│
├── repository/
│   ├── PacienteRepository.java         # Interface CRUD de pacientes via Spring Data JPA
│   ├── LogAuditoriaRepository.java    # Interface de persistencia de logs de auditoria
│   ├── ConsultaRepository.java         # Interface de persistencia de consultas
│   └── ProntuarioRepository.java       # Interface de historico de prontuarios
│
└── service/
    ├── PacienteService.java            # Logica de mascaramento de CPF e escrita de log
    ├── ConsultaService.java            # Logica de geracao de links dinamicos via UUID
    └── ProntuarioService.java          # Logica de evolucao clinica com logs obrigatorios
```
## Como Executar o Projeto Localmente 

1. Faça o download ou clone do código-fonte para a sua máquina local.
2. Abra a IDE de sua preferência e clique em **Open** (Abrir), selecionando a pasta raiz do projeto.
3. Aguarde a sincronização automática do Gradle. Caso a IDE solicite autorização, clique no ícone do elefante (**Reload All Gradle Projects**) localizado no canto superior direito para baixar as dependências.
4. Navegue até a raiz do pacote em: `src/main/java/com/example/Projeto_Multidisciplinar_SGHSS/ProjetoMultidisciplinarSghssApplication.java`.
5. Clique com o botão direito sobre o arquivo e selecione a opção **Run 'ProjetoMultidisciplinarSghssApplication.main()'** (ou clique no ícone do **Play** verde na parte superior).
6. O terminal interno da IDE exibirá as linhas de log do Spring Boot. A inicialização estará concluída com sucesso quando indicar o funcionamento do Tomcat na porta `8080`.

---

## Como Acessar e Testar os Endpoints via Swagger UI

Toda a validação funcional e coleta de evidências para o relatório pode ser feita diretamente pelo navegador, dispensando o uso de ferramentas externas como o Postman.

### Passo 1: Acesso à Interface Gráfica
Com a aplicação rodando no IntelliJ, abra o seu navegador de internet e acesse o endereço:
http://localhost:8080/swagger-ui/index.html

### Passo 2: Geração do Token de Segurança (CT001)
1. Na página do Swagger, expanda o bloco **Módulo de Autenticação & Segurança**.
2. Clique na rota `POST /api/auth/login` e selecione o botão **Try it out** no canto direito.
3. No campo do corpo da requisição (Body), insira as credenciais do médico:
```json
{
  "usuario": "medico@vidaplus.com",
  "senha": "senha123"
}
```
4. Clique no botão azul **Execute**.
5. Na resposta do servidor (*Server Response*), verifique o código `200 OK` e copie a string gerada no campo `"token"`.

**Nota:** Caso precise simular o perfil de Administrador em vez de Médico, utilize o payload abaixo no corpo da requisição do login:
```json
{
  "usuario": "admin@vidaplus.com",
  "senha": "admin123"
}
```

### Passo 3: Ativação da Autenticação
Suba até o topo da página do Swagger UI e clique no botão Authorize (marcado com o ícone de um cadeado).

No campo de texto que abrir, cole o token copiado no passo anterior.

Clique em Authorize e depois em Close.

A partir deste momento, todos os endpoints que exigem o cabeçalho Authorization receberão o token Bearer de forma automática pelo navegador e estarão liberados para uso conforme a role do usuário fornecido na authenticação.

## Acesso Visual ao Banco de Dados H2 Console
Para verificar as linhas gravadas fisicamente nas tabelas do banco durante a execução do MVP:

Acesse no seu navegador: http://localhost:8080/h2-console

Confirme se o campo JDBC URL está configurado exatamente como: jdbc:h2:mem:sghssdb

Deixe o campo Password em branco e clique em Connect.

No console SQL, execute comandos tradicionais como SELECT * FROM TB_LOGS_AUDITORIA_LGPD; para checar a rastreabilidade em tempo real.