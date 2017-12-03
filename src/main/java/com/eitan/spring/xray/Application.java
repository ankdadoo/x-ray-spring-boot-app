package com.eitan.spring.xray;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eitan.sela
 *
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebEnvironment(false);
		application.run(args);
		}

	@Override
	public void run(String... args) throws Exception {
		log.debug("run - start");		
		AWSXRay.beginSegment("My-Custom-Segment");
		
    	long timeBefore = System.currentTimeMillis();
    	
    	Subsegment subsegment1 = AWSXRay.beginSubsegment("Sleep 1");
	    try {
	    	log.debug("run - before sleep 1");
	        Thread.sleep(2000);
	        log.debug("run - after sleep 1");
	    	
    	} catch ( Exception ex ) {
    		log.error("run - Error: ", ex);
    		subsegment1.addException(ex);
    	} finally {
    		AWSXRay.endSubsegment();
    	}
	    	
	    Subsegment subsegment2 = AWSXRay.beginSubsegment("Sleep 2");
	    try {
	    	log.debug("run - before sleep 2");
	        Thread.sleep(500);
	        throw new RuntimeException("My Dummy Error");
	    	
    	} catch ( Exception ex ) {
    		log.error("run - Error: ", ex);
    		subsegment2.addException(ex);
    	} finally {
    		AWSXRay.endSubsegment();
    	}
        
    	AWSXRay.endSegment();
        log.debug("run - end - Time Took (ms): {}", (System.currentTimeMillis() - timeBefore));
	}
}