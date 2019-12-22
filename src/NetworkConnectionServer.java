import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnectionServer {
    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReciveCallback;

    public NetworkConnectionServer(Consumer<Serializable> onReciveCallback) {
        this.onReciveCallback = onReciveCallback;
        connThread.setDaemon(true);
    }



    public void startConnection() throws Exception{
        connThread.start();

    }

    public void send(Serializable data) throws Exception{
        connThread.out.writeObject(data);
    }

    public void closeConnection() throws Exception{
        connThread.socket.close();
    }

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    private class ConnectionThread extends Thread{
        private Socket socket;
        private ObjectOutputStream out;
        public Serializable read(ObjectInputStream in){
            try {
                Serializable data = (Serializable) in.readObject();
                onReciveCallback.accept(data);
                return data;
            }catch (Exception e) {}
            finally {
                return null;
            }
        }
        @Override
        public void run(){
            try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                Socket socket = isServer() ? server.accept() : new Socket(getIP(),getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);


                while(true){
                    read(in);
                    Player player = new Player("Client",out,in,this);
                    Eights eights = new Eights(player,2,out);
                    eights.playGame();
                }

            }
            catch(Exception e){
                onReciveCallback.accept("Connection closed");

            }

        }

    }
}
