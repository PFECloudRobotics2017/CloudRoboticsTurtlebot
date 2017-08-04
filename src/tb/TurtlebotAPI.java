package tb;

import com.jcraft.jsch.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class TurtlebotAPI {
    private static InputStream in;
    private static PipedOutputStream pin;
    private static Session session;
    private static Channel channel;
    
    private boolean isConnected = true;

    public void connect(String user, String host, String password) {
        try {
            JSch jsch = new JSch();

            session = jsch.getSession(user, host, 22);

            session.setPassword(password);

            UserInfo ui = new MyUserInfo() {
                public void showMessage(String message) {
                    JOptionPane.showMessageDialog(null, message);
                }

                public boolean promptYesNo(String message) {
                    Object[] options = {"yes", "no"};
                    int foo = JOptionPane.showOptionDialog(null, message, "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    return foo == 0;
                }
            };

            session.setUserInfo(ui);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(30000);

            channel = session.openChannel("shell");

            in = new PipedInputStream();
            pin = new PipedOutputStream((PipedInputStream) in);

            channel.setInputStream(in);
            channel.setOutputStream(System.out);

            channel.connect(3 * 1000);

            Thread.sleep(2000);

            pin.write("roslaunch turtlebot_teleop keyboard_teleop.launch --screen\r\n".getBytes());
            
            isConnected = true;

            Thread.sleep(3000);


        } catch (Exception e) {
        	isConnected = false;
            System.out.println(e);
        }
    }

    public void forward(int time) throws IOException {
    	if (isConnected) {
    		long t = System.currentTimeMillis();
    		long end = t + time;
    		System.out.println("Turtlebot connected... " + end);

    		while (System.currentTimeMillis() < end) {
    			pin.write("i\r\n".getBytes());
    		}
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public void backward(int time) throws IOException {
    	if (isConnected) {
    		long t = System.currentTimeMillis();
    		long end = t + time;
    		while (System.currentTimeMillis() < end) {
    			pin.write(",\r\n".getBytes());
    		}
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public void turnLeft(int time) throws IOException {
    	if (isConnected) {
    		long t = System.currentTimeMillis();
    		long end = t + time;
    		while (System.currentTimeMillis() < end) {
    			pin.write("j\r\n".getBytes());
    		}
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public void turnRight(int time) throws IOException {
    	if (isConnected) {
    		long t = System.currentTimeMillis();
    		long end = t + time;
    		while (System.currentTimeMillis() < end) {
    			pin.write("l\r\n".getBytes());
    		}
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public void stop(int time) throws IOException {
    	if (isConnected) {
    		long t = System.currentTimeMillis();
    		long end = t + time;
    		while (System.currentTimeMillis() < end) {
    			pin.write(" \r\n".getBytes());
    		}
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public void closeConnexion() throws IOException {
    	if (isConnected) {

    		pin.write("exit\r\n".getBytes());
    		pin.close();
    		channel.disconnect();
    		session.disconnect();
    	} else {
    		System.out.println("Turtlebot not connected...");
    	}
    }

    public abstract class MyUserInfo implements UserInfo, UIKeyboardInteractive {
        public String getPassword() {
            return null;
        }

        public boolean promptYesNo(String str) {
            return false;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return false;
        }

        public boolean promptPassword(String message) {
            return false;
        }

        public void showMessage(String message) {
        }

        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo) {
            return null;
        }
    }

}
