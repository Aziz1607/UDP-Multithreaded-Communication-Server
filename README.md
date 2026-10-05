# UDP Multithread Server

This project is a simple Java example of a UDP server and client using datagram sockets. The server listens on port 3000, accepts incoming UDP messages, and creates a separate thread for each packet so it can handle multiple clients concurrently.

## Project files

- `UDPServer.java` — listens for incoming UDP datagrams and processes each one in its own thread.
- `UDPClient.java` — sends a message to the UDP server.

## How it works

1. The server starts on `localhost:3000`.
2. It waits for incoming packets with `DatagramSocket.receive(...)`.
3. For each received packet, it creates a new `ClientHandler` thread.
4. The handler reads the message and logs:
   - client IP address
   - client port
   - content of the message

## Requirements

- Java JDK 8 or later

## Run the application

Open two terminal windows.

### 1) Start the server

```bash
javac UDPServer.java
java UDPServer
```

You should see:

```text
Le serveur UDP est en écoute sur le port 3000
```

### 2) Send a message from the client

In a second terminal:

```bash
javac UDPClient.java
java UDPClient
```

Then type a message and press Enter.

Example:

```text
Entrez un message à envoyer au serveur :
Hello UDP server!
```

The server will print a line similar to:

```text
Message reçu du client /127.0.0.1:xxxxx - Hello UDP server!
```

## Notes

- The server uses UDP, which is connectionless and does not guarantee delivery order or reliability.
- The multithreading example shows how to handle multiple incoming datagrams independently.
- This project is intended for learning and demonstration purposes.
