
package com.batchsight.mes.frame.ai.mcp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// claude mcp add -s user -t http greeting http://localhost:8073/mcp
// McpServerTransportProviderBase#protocolVersions
// stdio
// {"jsonrpc":"2.0","method":"initialize","id":"1", "params":{"protocolVersion":"2024-11-05"}}
// streamable
// curl -H "Content-Type: application/json" -H "Accept: application/json" -H "Accept: text/event-stream" -X POST http://localhost:8073/mcp -d "{\"jsonrpc\":\"2.0\",\"method\":\"initialize\",\"id\":\"1\", \"params\":{\"protocolVersion\":\"2024-11-05\"}}"
// sse
// curl http://localhost:8073/sse
// curl -X POST -H "Content-Type: application/json" http://localhost:8073/mcp/message?sessionId=825c313a-6fb0-42ca-82b3-c68bd8bbf99f -d "{\"jsonrpc\":\"2.0\",\"method\":\"initialize\",\"id\":\"1\", \"params\":{\"protocolVersion\":\"2024-11-05\"}}"
@SpringBootApplication
public class McpServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }
}
