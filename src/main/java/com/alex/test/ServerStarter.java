package com.alex.test;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.alex.test.component.Server;

public class ServerStarter extends JFrame {

    private Server server;

    public ServerStarter() throws HeadlessException {
        server = new Server();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void dispose() {
        server.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        new ServerStarter();
    }
}
