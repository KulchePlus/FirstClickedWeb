package com.firstclick.utils;

import com.firstclick.config.SpringConfig;
import com.firstclick.model.ClickRequest;
import com.firstclick.model.ClickResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Processor {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    Generator generator = context.getBean("generator", Generator.class);
    Memory memory = context.getBean("memory", Memory.class);
    private final String sharedKey = "SHARED_KEY";
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int BAD_REQUEST_STATUS = 108;
    public ClickResponse getResponse(ClickRequest request) {
        switch (request.getMessageType()) {
            case CONNECT:
                return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS, generator.generateNewId());
                break;
            case STATUS:
                return new ClickResponse(SUCCESS_STATUS, CODE_SUCCESS, memory.getFirstClickedId(), memory.getFirstClickedName());
                break;
            case CLICK:
                return new ClickResponse(SUCCESS_STATUS,CODE_SUCCESS, memory.getFirstClickedId(), memory.getFirstClickedName());
                break;
            case DISCONNECT:
                return new ClickResponse(SUCCESS_STATUS,CODE_SUCCESS);
                break;
            default:
                return new ClickResponse(ERROR_STATUS, BAD_REQUEST_STATUS);
                break;
        }
    }



    private void addUser(){}
    private void clickAction(){}
}
