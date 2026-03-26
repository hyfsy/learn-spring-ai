// package com.batchsight.mes.frame.ai.mcp.config;
//
// import org.springaicommunity.mcp.security.server.apikey.ApiKeyEntity;
// import org.springaicommunity.mcp.security.server.apikey.ApiKeyEntityRepository;
// import org.springaicommunity.mcp.security.server.apikey.memory.ApiKeyEntityImpl;
// import org.springaicommunity.mcp.security.server.apikey.memory.InMemoryApiKeyEntityRepository;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
//
// import java.util.List;
//
// import static org.springaicommunity.mcp.security.server.config.McpApiKeyConfigurer.mcpServerApiKey;
//
// // 依赖不兼容，暂时注释
// @Configuration
// @EnableWebSecurity
// public class McpSecurityConfiguration {
//
//         @Bean
//         SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//             return http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
//                     .with(
//                             mcpServerApiKey(),
//                             (apiKey) -> {
//                                 // REQUIRED: the repo for API keys
//                                 apiKey.apiKeyRepository(apiKeyRepository());
//
//                                 // OPTIONAL: name of the header containing the API key.
//                                 // Here for example, api keys will be sent with "CUSTOM-API-KEY: <value>"
//                                 // Replaces .authenticationConverter(...) (see below)
//                                 //
//                                 // apiKey.headerName("CUSTOM-API-KEY");
//
//                                 // OPTIONAL: custom converter for transforming an http request
//                                 // into an authentication object. Useful when the header is
//                                 // "Authorization: Bearer <value>".
//                                 // Replaces .headerName(...) (see above)
//                                 //
//                                 // apiKey.authenticationConverter(request -> {
//                                 //     var key = extractKey(request);
//                                 //     return ApiKeyAuthenticationToken.unauthenticated(key);
//                                 // });
//                             }
//                     )
//                     .build();
//         }
//
//         /**
//          * Provide a repository of {@link ApiKeyEntity}.
//          */
//         private ApiKeyEntityRepository<ApiKeyEntityImpl> apiKeyRepository() {
//             var apiKey = ApiKeyEntityImpl.builder()
//                     .name("test api key")
//                     .id("id")
//                     .secret("secret")
//                     .build();
//
//             return new InMemoryApiKeyEntityRepository<>(List.of(apiKey));
//         }
//     }