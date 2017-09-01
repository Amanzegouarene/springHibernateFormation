package fr.amanzegouarene.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by amanzego on 31/08/2017.
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public InfosInterface getInfos(){
        return new InfosXML();
    }
}
