package com.snowcat;

import com.snowcat.config.Jobs;
import com.snowcat.job.JobsHandler;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Hello world!
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        try {
            JAXBContext jc = JAXBContext.newInstance("com.snowcat.config.Jobs");
            Unmarshaller u = jc.createUnmarshaller();
            JAXBElement configElement = (JAXBElement) u.unmarshal(new FileInputStream("../config/config.xml"));
            Jobs jobs=(Jobs)configElement.getValue();
            JobsHandler.handleJobs(jobs);
        }catch (FileNotFoundException|JAXBException e){
            logger.warn("some error happened.",e);
        }
    }
}
