package com.firstclick.server;

import com.firstclick.ClickButtonServerSpringMavenApplication;
import com.firstclick.config.SpringConfig;
import com.firstclick.model.ClickRequest;
import com.firstclick.model.ClickResponse;
import com.firstclick.utils.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import lombok.Getter;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Processor {

    static final Logger log = LoggerFactory.getLogger(Processor.class);
    Memory memory;
    Generator generator ;

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int BAD_REQUEST_STATUS = 108;
    private static final int BAD_REQUEST_NAME = 102;
    private final String localIpAddress;

    public Processor(Memory memory, Generator generator) {
        this.memory = memory;
        this.generator = generator;
        localIpAddress = getLocalIpAddress();
        log.info(localIpAddress);
    }

    private String getLocalIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp() || iface.isVirtual())
                    continue;


                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address) {
                        ip = addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }


    public ClickResponse getResponse(ClickRequest request) {
        log.trace("Checking messageType");
        return switch (request.getMessageType()) {
            case CONNECT -> responseToConnect(request);
            case INFO -> responseToInfo(request);
            case CLICK -> responseToClick(request);
            case DISCONNECT -> responseToDisconect(request);
            case CLEAR -> responseToClear(request);
            default -> new ClickResponse(ERROR_STATUS, BAD_REQUEST_STATUS);
        };
    }

    private ClickResponse responseToClear(ClickRequest request) {
        log.trace("Doing process: MessageType = CLEAR");
        memory.clearFirstClicked();
        return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS);
    }


    private ClickResponse responseToConnect(ClickRequest request) {
        if(request.getName().isEmpty()){
            return new ClickResponse(ERROR_STATUS, BAD_REQUEST_NAME);
        }

        int userId = generator.generateNewId();
        memory.addPlayer(userId, request.getName());
        return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS, userId);
    }

    private ClickResponse responseToInfo(ClickRequest request) {
        return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS, memory.getFirstClickedId(), memory.getFirstClickedName());
    }

    private ClickResponse responseToClick(ClickRequest request) {
        log.trace("Doing process: MessageType = CLICK");
        memory.writeFirstClickedIfNull(request.getUserId(), request.getName());
        return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS, memory.getFirstClickedId(), memory.getFirstClickedName());
    }

    private ClickResponse responseToDisconect(ClickRequest request) {
        int userId = generator.generateNewId();
        memory.removePlayer(userId);
        return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS);
    }
}
