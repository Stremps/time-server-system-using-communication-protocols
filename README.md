
# Trabalho 1 Sistemas Distribuídos: Servidor de Tempo TCP
Este projeto implementa um sistema de servidor de tempo, onde os clientes podem solicitar o horário atual do servidor e ajustar seus relógios com base nos tempos de ida e volta das mensagens de solicitação e resposta. Além disso, o servidor mantém um registro das ações de cada usuário em logs.

## Funcionalidades
&nbsp; &nbsp; &nbsp; &nbsp; Consulta de Horário: Clientes podem solicitar o horário atual do servidor.
&nbsp; &nbsp; &nbsp; &nbsp; Atualização Automática: O cliente pode configurar o intervalo de tempo em que deseja receber atualizações automáticas do horário.
&nbsp; &nbsp; &nbsp; &nbsp; Logs de Atividades: Todas as solicitações e respostas são registradas em um arquivo de log para monitoramento e auditoria.

## Componentes
O projeto é composto pelos seguintes módulos Java:

&nbsp; &nbsp; &nbsp; &nbsp;  TCPServer.java: Implementa o servidor que lida com as solicitações de horário e envia respostas aos clientes.
&nbsp; &nbsp; &nbsp; &nbsp;  TCPClient.java: Cliente que solicita o horário do servidor e pode ser configurado para receber atualizações automáticas.
&nbsp; &nbsp; &nbsp; &nbsp;  LogToFile.java: Responsável por registrar as ações de cada usuário em um arquivo de log.
&nbsp; &nbsp; &nbsp; &nbsp;  Connection.java: Abstrai a lógica de conexão utilizada tanto pelo cliente quanto pelo servidor.

## Como usar

Pré-requisito: Java JDK versão 22 ou superior

1. Clone o repositório ou baixe os arquivos do projeto.
2. Compile os arquivos Java:
> javac TCPServer.java LogToFile.java Connection.java
3. Execute o servidor:
> java TCPServer
4. Compile o cliente:
> javac TCPClient.java Connection.java
5. Execute o cliente:
> java TCPClient


