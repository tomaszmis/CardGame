import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends NetworkConnectionClient {

    private String ip;
    private int port;

    public Client(String ip, int port,Consumer<Serializable> onReciveCallback){
        super(onReciveCallback);
        this.ip = ip;
        this.port = port;
    }
    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }
}