package com.risi.springcloudgateway;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}

	/*@Bean
	public RouteLocator routes(RouteLocatorBuilder routeBuilder) {
		return routeBuilder.routes()
				.route("route_id",
						route -> route
								.path("/currency-converter-feign/**")
								.filters(f -> f.rewritePath("/currency-converter-feign/(?<RID>.*)", "/currency-converter-feign/${RID}"))
								.uri("lb://currency-conversion-service")
				)
				.build();
	}*/

	@Bean
	protected Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
