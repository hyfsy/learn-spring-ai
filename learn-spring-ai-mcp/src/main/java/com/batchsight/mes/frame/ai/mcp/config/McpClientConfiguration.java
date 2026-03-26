package com.batchsight.mes.frame.ai.mcp.config;

import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpClientConfiguration {

    // @Autowired
    // private List<McpSyncClient> mcpSyncClients;
    @Autowired
    private SyncMcpToolCallbackProvider toolCallbackProvider;

    // @Bean
    // public CommandLineRunner demo(ChatClient chatClient, ToolCallbackProvider mcpTools) {
    //     return args -> {
    //         String response = chatClient
    //                 .prompt("What's the weather like in Paris?")
    //                 .toolCallbacks(mcpTools)
    //                 .call()
    //                 .content();
    //         System.out.println(response);
    //     };
    // }
}
