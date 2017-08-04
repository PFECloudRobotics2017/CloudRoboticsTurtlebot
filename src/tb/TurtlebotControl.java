package tb;

import java.io.IOException;

public class TurtlebotControl {

    public String user = "";
    public String password = "";
    public String host = "";
    private TurtlebotAPI api = null;

    public TurtlebotControl(String user, String password, String host) {
        this.user = user;
        this.password = password;
        this.host = host;
    }

    public void connect() {
        api = new TurtlebotAPI();
        api.connect(user, host, password);
    }

    public void disconnect() {
        try {
            api.closeConnexion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnLeft(int time) {
        try {
            api.turnLeft(time);
            Thread.sleep(250);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnRight(int time) {
        try {
            api.turnRight(time);
            Thread.sleep(250);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move_forward(int time) {
        try {
            api.forward(time);
            Thread.sleep(250);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move_backward(int time) {
        try {
            api.backward(time);
            Thread.sleep(250);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(int time) {
        try {
            api.stop(time);
            Thread.sleep(250);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
