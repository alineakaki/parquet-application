# Parquet Application

Este projeto Java é uma aplicação Spring Boot que gera arquivos Parquet utilizando Avro e Hadoop. O código define um esquema Avro e escreve múltiplos registros em um arquivo Parquet, aplicando compressão GZIP para otimização do espaço.

## Pré-requisitos

Antes de executar a aplicação, você deve ter os seguintes softwares instalados:

- Java 11 ou superior
- Maven
- Hadoop (com a variável de ambiente `HADOOP_HOME` configurada)
- Spring Boot


## Como Compilar e Executar

1. **Clone o repositório:**

   ```bash
   git clone git@github.com:alineakaki/parquet-application.git
   cd parquet-application

2. **Compile o projeto:**

   ```bash
   mvn clean package

3. **Execute a aplicação:**
   ```bash
   java -jar target/parquet-application-0.0.1-SNAPSHOT.jar [número_de_registros]
