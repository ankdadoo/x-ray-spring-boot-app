package com.eitan.spring.xray;

import java.net.URL;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.plugins.EC2Plugin;
import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eitan.sela
 *
 */
@Slf4j
@Configuration
public class XRayConfig {

	static {
		log.info("XRayConfig - Start");
	    AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new EC2Plugin());

	    URL ruleFile = XRayConfig.class.getResource("/sampling-rules.json");
	    builder.withSamplingStrategy(new LocalizedSamplingStrategy(ruleFile));
	    
	    AWSXRay.setGlobalRecorder(builder.build());
	    log.info("XRayConfig - End");
	  }
}
