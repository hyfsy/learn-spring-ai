package com.batchsight.mes.frame.ai.mcp.client;

import com.batchsight.mes.frame.ai.mcp.client.config.GreetingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingApi {

    @Resource
    private GreetingService greetingService;

    @RequestMapping("/")
    public String greeting() {
        String greeting = greetingService.greeting();
        System.out.println(greeting);
        return greeting;
    }
}
