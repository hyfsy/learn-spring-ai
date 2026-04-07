package com.batchsight.mes.frame.ai.mcp.server.config;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.annotation.*;
import org.springframework.ai.mcp.annotation.context.MetaProvider;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

// @Component
public class MyTools {

    @McpTool(description = "Her Greeting")
    public String z_greeting() {
        return "hello, handsome.";
    }

    @McpTool(description = "His Greeting")
    public String h_greeting() {
        return "hello, beauty.";
    }

    // @McpTool(description = "Get current time in ISO format")
    // public String getCurrentTime() {
    //     return LocalDateTime.now().toString();
    // }
    //
    // @McpTool(description = "Add two integers")
    // public int add(
    //         @McpToolParam(description = "First number") int a,
    //         @McpToolParam(description = "Second number") int b
    // ) {
    //     return a + b;
    // }

    // @PreAuthorize("isAuthenticated()")
    @McpTool(description = "Mcp Tool Demo Description")
    public String McpToolDemo(@McpProgressToken String progressToken) {
        return "nothing" + progressToken;
    }

    @McpResource(uri = "get-data://{id}", name = "Get Data", title = "Get Data Title", description = "Get Data Description", metaProvider = MyMcpResourceMetaProvider.class)
    public McpSchema.ReadResourceResult McpResourceDemo(String id, McpSchema.ReadResourceRequest request, McpMeta meta) {
        String data = "hello mcp resource get data";
        String clientKey = (String) meta.get("clientKey");
        return new McpSchema.ReadResourceResult(List.of(new McpSchema.TextResourceContents("get-data://" + id, "text/plain", data)));
    }

    public static class MyMcpResourceMetaProvider implements MetaProvider {

        @Override
        public Map<String, Object> getMeta() {
            return Map.of(
                    "team", "platform",
                    "experimental", false
            );
        }
    }

    @McpPrompt(name = "mcp-prompt-demo", title = "Mcp Prompt Demo Title", description = "Mcp Prompt Demo Description")
    public McpSchema.GetPromptResult McpPromptDemo(@McpArg(name = "language", required = true) String language) {
        String data = "hello mcp prompt get prompt by language: " + language;
        return new McpSchema.GetPromptResult("McpPromptDemo", List.of(new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent(data))));
    }

    @McpComplete(prompt = "mcp-prompt-demo")
    public String McpCompleteDemo(String language) {
        return language + "cn";
    }
}
