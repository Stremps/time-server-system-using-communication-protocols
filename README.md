
# Trabalho 1 Sistemas Distribuídos: Servidor de Tempo implementado com protocolo TCP.
Este projeto implementa um sistema de servidor de tempo, onde os clientes podem solicitar o horário atual do servidor e ajustar seus relógios com base nos tempos de ida e volta das mensagens de solicitação e resposta. Além disso, o servidor mantém um registro das ações de cada usuário em logs.

## Funcionalidades
&nbsp; &nbsp; &nbsp; &nbsp; Consulta de Horário: Clientes podem solicitar o horário atual do servidor.

&nbsp; &nbsp; &nbsp; &nbsp; Atualização Automática: O cliente pode configurar o intervalo de tempo em que deseja receber atualizações automáticas do horário.

&nbsp; &nbsp; &nbsp; &nbsp; Logs de Atividades: Todas as solicitações e respostas são registradas em um arquivo de log para monitoramento e auditoria.

## Componentes
O projeto original, que implementa a proposta utilizando o protocolo TCP, é composto pelos seguintes módulos Java:

&nbsp; &nbsp; &nbsp; &nbsp;  TCPServer.java: Implementa o servidor que lida com as solicitações de horário e envia respostas aos clientes.

&nbsp; &nbsp; &nbsp; &nbsp;  TCPClient.java: Cliente que solicita o horário do servidor e pode ser configurado para receber atualizações automáticas.

&nbsp; &nbsp; &nbsp; &nbsp;  LogToFile.java: Responsável por registrar as ações de cada usuário em um arquivo de log.

&nbsp; &nbsp; &nbsp; &nbsp;  Connection.java: Abstrai a lógica de conexão utilizada tanto pelo cliente quanto pelo servidor.

## Como usar

Pré-requisito: Java JDK versão 22 ou superior

  Clonar o repositório ou baixar os arquivos do projeto. O repositório pode ser encontrado no URL https://github.com/Stremps/time-server-system-using-tcp .
 
 Compilar os arquivos Java:
 
> javac TCPServer.java LogToFile.java Connection.java
 
 Executar o servidor:
 
> java TCPServer

 Compilar o cliente:
 
> javac TCPClient.java Connection.java

 Executar o cliente:
 
> java TCPClient



# Trabalho 2 Sistemas Distribuídos: Readequação da implementasção anterior com o uso de RMI (Remote Method Invocation)

## Componentes

&nbsp; &nbsp; &nbsp; &nbsp;  RMIServer.java: Responsável por iniciar o servidor RMI, configurar o registro RMI e registrar as implementações dos serviços de tempo para que os clientes possam acessá-los.

&nbsp; &nbsp; &nbsp; &nbsp;  TimeClient.java: Cliente que conecta-se ao servidor RMI, solicita o serviço de tempo e ajusta o horário local com base no tempo sincronizado recebido do servidor.

&nbsp; &nbsp; &nbsp; &nbsp;  TimeService.java:  Interface remota que define os métodos para o serviço de tempo, que podem ser invocados remotamente pelos clientes.

&nbsp; &nbsp; &nbsp; &nbsp;  TimeServiceImpl.java: Implementação da interface TimeService que fornece o método para obter o tempo atual do servidor, que é invocado remotamente pelos clientes.

&nbsp; &nbsp; &nbsp; &nbsp;  TimeServiceFactory.java:  Interface remota responsável por criar novas instâncias do serviço de tempo para clientes que solicitam o serviço.

&nbsp; &nbsp; &nbsp; &nbsp;  TimeServiceFactoryImpl.java: Implementação da interface TimeServiceFactory, que cria e retorna novas instâncias do serviço de tempo.

&nbsp; &nbsp; &nbsp; &nbsp;  LogToFile.java: Responsável por registrar as ações de cada usuário em um arquivo de log.

&nbsp; &nbsp; &nbsp; &nbsp;  localIp.java: Classe responsável por obter e exibir o endereço IP local da máquina, usado para propósitos de configuração de rede.


## Como usar

Pré-requisito: Java JDK versão 22 ou superior

 Clonar o repositório ou baixar os arquivos do projeto. O repositório pode ser encontrado no URL https://github.com/Stremps/time-server-system-using-tcp .
 
 Compilar os arquivos Java, executando o seguinte comando em ambos os diretórios RMI e Log:
 
> javac *.java 
 
 Executar o servidor:
 
> java RMIServer

 Executar o cliente:
 
> java TimeClient


