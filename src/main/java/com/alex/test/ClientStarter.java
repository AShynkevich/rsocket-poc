package com.alex.test;

import com.alex.test.component.ReqResClient;

public class ClientStarter {

    public static void main(String[] args) {
        System.out.println("Start Client");
        ReqResClient reqResClient = new ReqResClient();

        System.out.println(reqResClient.callBlocking("Hi there"));

        System.out.println("Disposing server");
    }
}
