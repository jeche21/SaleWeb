package es.sidelab.SaleWeb;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableHazelcastHttpSession
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	 public Config config() {
	 Config config = new Config();
	 JoinConfig joinConfig = config.getNetworkConfig().getJoin();
	 joinConfig.getMulticastConfig().setEnabled(false);
	 joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
	 return config;
	 }
}
