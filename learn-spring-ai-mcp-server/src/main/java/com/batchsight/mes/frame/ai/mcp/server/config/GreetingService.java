package com.batchsight.mes.frame.ai.mcp.server.config;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.mcp.annotation.context.McpSyncRequestContext;
import org.springframework.ai.mcp.annotation.context.StructuredElicitResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GreetingService {

    @McpTool(name = "greeting", title = "Just Greeting", description = "Let someone give another one a greeting")
    public String greeting(@McpToolParam(description = "The greeting source") String sourceName,
                              @McpToolParam(description = "The greeting target") String targetName,
                              McpSyncRequestContext context) {
        context.progress(0);
        System.out.println("Start greeting from " + sourceName + " to " + targetName);
        System.out.println("Client Info:");
        System.out.println("- sessionId: " + context.sessionId());
        McpSchema.Implementation implementation = context.clientInfo();
        System.out.println("- name: " + implementation.name());
        System.out.println("- title: " + implementation.title());
        System.out.println("- version: " + implementation.version());
        McpSchema.ClientCapabilities clientCapabilities = context.clientCapabilities();
        System.out.println("- capabilities: " + clientCapabilities);
        System.out.println("- request meta: ");
        Map<String, Object> map = context.requestMeta();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("  - " + entry.getKey() + " : " + entry.getValue());
        }
        context.info("Greeting started, from " + sourceName + " to " + targetName + " ...");
        context.progress(10);

        String genderData = null;
        String ageData = null;
        if (context.elicitEnabled()) {
            StructuredElicitResult<User> elicitResult = context.elicit(User.class);
            if (elicitResult.action() == McpSchema.ElicitResult.Action.ACCEPT) {
                User user = elicitResult.structuredContent();
                genderData = user.getGender() == 0 ? "handsome" : "pretty";
                ageData = String.valueOf(user.getAge());
            }
        }
        context.progress(30);
        String home = null;
        if (context.rootsEnabled()) {
            McpSchema.ListRootsResult rootsResult = context.roots();
            System.out.println("Get client root next cursor: " + rootsResult.nextCursor());
            List<McpSchema.Root> roots = rootsResult.roots();
            if (!roots.isEmpty()) {
                home = roots.get(0).uri();
            }
            for (McpSchema.Root root : roots) {
                System.out.println("Get client root: " + root.name() + " -> " + root.uri());
            }
        }
        context.progress(60);
        String currentStatus = null;
        if (context.sampleEnabled()) {
            McpSchema.CreateMessageResult sampleResult = context.sample("Target current Status");
            System.out.println("Sample stop reason: " + sampleResult.stopReason());
            System.out.println("Sample generated role: " + sampleResult.model());
            System.out.println("Sample generated llm name: " + sampleResult.role());
            McpSchema.Content content = sampleResult.content();
            switch (content.type()) {
                case "text":
                    McpSchema.TextContent textContent = (McpSchema.TextContent) content;
                    currentStatus = textContent.text();
                    break;
                default:
                    context.warn("Not support non text sample content");
                    break;
            }
        }
        context.progress(99);
        return "hello, "
                + (ageData == null ? "" : genderData + " ")
                + (genderData == null ? "" : genderData + " age ")
                + (currentStatus == null ? "" : currentStatus + " ")
                + targetName
                + (home == null ? "" : ", your home is at " + home);
    }

    private static class User {
        private int age;
        private int gender;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
