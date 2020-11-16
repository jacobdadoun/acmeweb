# acmeweb
sample web server for class exercises on IoC, DIP, DI

This sample shows how to leverage the Spring framework, for simulating a production control server that reports on 
and manages other manufactoring servers, machinery and processes at the Acme Disk Drive company.

Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *
 *    IMPORTANT: When using curl [LINK], Make sure terminal is set to bash$ and not zsh%
 *    http://localhost:8080/server/status
 *    http://localhost:8080/server/status?name=Noach
 *    http://localhost:8080/server/status?name=Jacob&details=Hello,world
 *
 *    1xx informational response – the request was received, continuing process
 *    2xx successful – the request was successfully received, understood, and accepted
 *    3xx redirection – further action needs to be taken in order to complete the request
 *    4xx client error – the request contains bad syntax or cannot be fulfilled
 *    5xx server error – the server failed to fulfil an apparently valid request
 *



