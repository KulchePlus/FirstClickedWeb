package com.firstclick.config;

import com.firstclick.utils.Generator;
import com.firstclick.server.Memory;
import com.firstclick.server.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    @Bean
    public Processor processor(){
        return new Processor(memory(), generator());
    }
    @Bean
    public Memory memory(){
        return new Memory();
    }
    @Bean
    public Generator generator(){
        return new Generator();
    }

}
