package org.Lab2.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/*Указывает, что класс объявляет один или несколько @Bean и может обрабатываться контейнером Spring для создания
        определений компонентов и запросов служб для этих компонентов во время выполнения*/
@Configuration
@ComponentScan("org.Lab2")
//Аннотация, предоставляющая удобный и декларативный механизм для добавления PropertySource в Environment Spring.
// Для использования в сочетании с классами @Configuration.
@PropertySource("classpath:app.properties")
public class SpringConfig {

    //Значения связанные с app.properties
    @Value("${databaseUsername}")
    private String dbUsername;
    @Value("${databaseUrl}")
    private String dbUrl;
    @Value("${databasePassword}")
    private String dbPassword;

//В аннотации Spring @Bean указывается, что метод создает компонент, управляемый контейнером Spring. Метод Spring @Bean
// может быть создан в @Configuration и @Component классах. Областью действия компонента по умолчанию является синглтон.
// Аннотация @Bean может использоваться в сочетании с аннотациями, такими как @Scope, @Lazy, @DependsOn, @Primary и т. Д.
//Найдите необязательные методы @Bean аннотации.
    @Bean
    @Scope(value = "singleton")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    @Scope(value = "singleton")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
