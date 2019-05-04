package com.ld.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean("dataSource")
    public DataSource dataSource(@Value("${jdbc.username}") String username, @Value("${jdbc.password}") String pwd,
                                 @Value("${jdbc.url}") String url, @Value("${jdbc.driver}") String driverClass) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(username);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean("sqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(@Autowired DataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ld.entity");
        return sqlSessionFactoryBean;
    }

    @Bean("globalConfig")
    public GlobalConfiguration globalConfig(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setDbColumnUnderline(true);
        globalConfiguration.setIdType(0);
        globalConfiguration.setTablePrefix("tbl_");
        return globalConfiguration;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.ld.mapper");
        return mapperScannerConfigurer;
    }



}
