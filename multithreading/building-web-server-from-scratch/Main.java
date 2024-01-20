import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8080);
      System.out.println("Web server started && waiting for clients to connect !!");

      /*
       * serverSocket.setSoTimeout(3000);
       * Thread.sleep(5000);
       */
      // serverSocket.setSoTimeout(10000);
      while (true) {
        Socket client = serverSocket.accept();
        System.out.println("Server accepted a client connection !!");

        InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
        BufferedReader buffereReader = new BufferedReader(inputStreamReader);

        StringBuilder request = new StringBuilder();
        String line = buffereReader.readLine();

        while (!line.isBlank()) {
          request.append(line + "\r\n");
          line = buffereReader.readLine();
        }

        System.out.println("REQUEST --> " + request);

        OutputStream responseStream = client.getOutputStream();
        String response = "HTTP/1.1 200 OK \r\n";
        responseStream.write(response.getBytes());
        responseStream.write("\r\n".getBytes());
        responseStream.write("Hello World".getBytes());
        responseStream.flush();
        client.close();
      }
        
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}