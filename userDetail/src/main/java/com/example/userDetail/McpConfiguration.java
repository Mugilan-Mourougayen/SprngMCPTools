package com.example.userDetail;


import com.example.userDetail.Service.NewsService;
import com.example.userDetail.Service.UserService;
import com.example.userDetail.Service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfiguration {
    @Bean
    public ToolCallbackProvider userTool(UserService userService, WeatherService weatherService, NewsService newsService){
        return MethodToolCallbackProvider.builder().toolObjects(userService,weatherService,newsService).build();
    }

}
