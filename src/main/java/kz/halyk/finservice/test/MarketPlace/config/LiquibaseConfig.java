package kz.halyk.finservice.test.MarketPlace.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/***
 * Configuration file for liquibase.
 */
@Configuration
public class LiquibaseConfig {
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    /***
     * Initialization SpringLiquibase bean
     * @param dataSource
     * @return
     */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/master.xml");
        liquibase.setShouldRun(true);

        liquibase.setDropFirst(false);
        return liquibase;
    }
}
