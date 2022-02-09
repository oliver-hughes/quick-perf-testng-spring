package org.example;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataSourceFactory implements FactoryBean<DataSource> {

    @Override
    public DataSource getObject() throws Exception {
        int randomInt = Math.abs(ThreadLocalRandom.current().nextInt());
        String url = "jdbc:h2:mem:test" + randomInt;
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername("qp");
        dataSource.setPassword("");
        dataSource.setMaxActive(4);
        dataSource.setPoolPreparedStatements(true);
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }
}
