package fr.amanzegouarene.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by amanzego on 31/08/2017.
 */
public class ApplicationMain {

    public static void main(String[] args) {
        // Application context init
        ApplicationContext context =
                // new AnnotationConfigApplicationContext(AppConfig.class);
                new ClassPathXmlApplicationContext("app_config.xml");
        GenerateurInfos generateurInfos = context.getBean(GenerateurInfos.class);
        System.out.println(generateurInfos.fournirLesDonnees());
    }
}
