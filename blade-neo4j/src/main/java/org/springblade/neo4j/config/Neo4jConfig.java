package org.springblade.neo4j.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableNeo4jRepositories(basePackages = "org.springblade.**.repository")
@ConditionalOnProperty(prefix = "spring.neo4j", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Neo4jConfig {
    
    @Value("${spring.neo4j.uri:bolt://localhost:7687}")
    private String uri;
    
    @Value("${spring.neo4j.authentication.username:neo4j}")
    private String username;
    
    @Value("${spring.neo4j.authentication.password:password}")
    private String password;
    
    @Value("${spring.neo4j.connection-timeout:30s}")
    private String connectionTimeout;
    
    @Value("${spring.neo4j.max-connection-pool-size:100}")
    private int maxConnectionPoolSize;

    @Bean
    @Primary
    public Driver neo4jDriver() {
        try {
            log.info("Initializing Neo4j driver with URI: {}", uri);
            return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
        } catch (Exception e) {
            log.error("Failed to initialize Neo4j driver", e);
            throw new RuntimeException("Neo4j driver initialization failed", e);
        }
    }

    @Bean
    @Primary
    public Neo4jClient neo4jClient(Driver driver) {
        return Neo4jClient.create(driver);
    }

    @Bean
    @Primary
    public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient) {
        return new Neo4jTemplate(neo4jClient);
    }
}
