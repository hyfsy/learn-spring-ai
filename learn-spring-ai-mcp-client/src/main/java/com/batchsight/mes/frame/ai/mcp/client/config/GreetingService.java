package com.batchsight.mes.frame.ai.mcp.client.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.modelcontextprotocol.spec.McpSchema;
import jakarta.annotation.Resource;
import org.springframework.ai.mcp.annotation.*;
import org.springframework.ai.mcp.annotation.context.McpSyncRequestContext;
import org.springframework.ai.mcp.annotation.context.StructuredElicitResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GreetingService {

    private static final String CLIENT_NAME = "mcp-client-demo-ai-mcp-client";

    public String greeting() {
        // request server response
        return "hello";
    }

    @McpElicitation(clients = CLIENT_NAME)
    public McpSchema.ElicitResult userInfoElicitation(McpSchema.ElicitRequest request) {
        System.out.println("Receive greeting elicitation request: " + request);
        User user = new User(18, 0);
        return McpSchema.ElicitResult.builder()
                .message(McpSchema.ElicitResult.Action.ACCEPT)
                .content(JSON.parseObject(JSON.toJSONString(user)))
                .build();
    }

    @McpSampling(clients = CLIENT_NAME)
    public McpSchema.CreateMessageResult currentStatusSampling(McpSchema.CreateMessageRequest request) {
        System.out.println("Receive greeting sampling request: " + request);
        String message = "hello mcp sampling get data";
        return McpSchema.CreateMessageResult.builder()
                .message(message)
                .build();
    }

    @McpProgress(clients = CLIENT_NAME)
    public void reportProgress(McpSchema.ProgressNotification notification) {
        System.out.printf("Receive greeting progress: %s(%s/%s) --- %s%n", notification.progressToken(), notification.progress(), notification.total(), notification.message());
    }

    @McpLogging(clients = CLIENT_NAME)
    public void logLogging(McpSchema.LoggingMessageNotification notification) {
        System.out.printf("Receive greeting log: [%s] %s --- %s%n", notification.level(), notification.logger(), notification.data());
    }

    private record User(int age, int gender) { }
}
