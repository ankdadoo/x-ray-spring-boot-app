package com.eitan.spring.xray;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
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
				
    	long timeBefore = System.currentTimeMillis();
        
    	AWSXRayRecorder xrayRecorder = AWSXRayRecorderBuilder.defaultRecorder();
        Subsegment subsegment = xrayRecorder.beginSubsegment("MyCustomSubsegment-01");
		
        log.debug("run - before sleep");
        Thread.sleep(2000);
        log.debug("run - after sleep");
        
        xrayRecorder.endSubsegment();
        
        log.debug("run - end - Time Took (ms): {}", (System.currentTimeMillis() - timeBefore));
	}
}