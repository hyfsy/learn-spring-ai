package com.batchsight.mes.frame.ai.mcp.client.config;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.annotation.*;
import org.springframework.ai.mcp.annotation.context.MetaProvider;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

// @Component
public class MyTools {

    private static final String CLIENT_NAME = "mcp-client-demo-ai-mcp-client";

    @McpElicitation(clients = CLIENT_NAME)
    public McpSchema.ElicitResult McpElicitationDemo(McpSchema.ElicitRequest request) {
        System.out.println("Receive elicitation request: " + request);
        return McpSchema.ElicitResult.builder()
                .message(McpSchema.ElicitResult.Action.ACCEPT)
                .content(Map.of("key", "value"))
                .build();
    }

    @McpSampling(clients = CLIENT_NAME)
    public McpSchema.CreateMessageResult McpSamplingDemo(McpSchema.CreateMessageRequest request) {
        System.out.println("Receive sampling request: " + request);
        String message = "hello mcp sampling get data";
        return McpSchema.CreateMessageResult.builder()
                .message(message)
                .build();
    }

    @McpProgress(clients = CLIENT_NAME)
    public void McpProgressDemo(McpSchema.ProgressNotification notification) {
        System.out.printf("Receive progress: %s(%s/%s) --- %s%n", notification.progressToken(), notification.progress(), notification.total(), notification.message());
    }

    @McpLogging(clients = CLIENT_NAME)
    public void McpLoggingDemo(McpSchema.LoggingMessageNotification notification) {
        System.out.printf("Receive log: [%s] %s --- %s%n", notification.level(), notification.logger(), notification.data());
    }

    @McpToolListChanged(clients = CLIENT_NAME)
    public void McpToolListChangedDemo(List<McpSchema.Tool> updatedTools) {
        System.out.println("Receive changed tool list: " + updatedTools);
    }

    @McpResourceListChanged(clients = CLIENT_NAME)
    public void McpResourceListChangedDemo(List<McpSchema.Resource> updatedResources) {
        System.out.println("Receive changed resource list: " + updatedResources);
    }

    @McpPromptListChanged(clients = CLIENT_NAME)
    public void McpPromptListChangedDemo(List<McpSchema.Prompt> updatedPrompts) {
        System.out.println("Receive changed prompt list: " + updatedPrompts);
    }
}
