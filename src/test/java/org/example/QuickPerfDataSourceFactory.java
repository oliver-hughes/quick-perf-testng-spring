package org.example;

import org.quickperf.sql.config.QuickPerfSqlDataSourceBuilder;
import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

public class QuickPerfDataSourceFactory implements FactoryBean<DataSource> {

    private DataSource dataSource;

    public QuickPerfDataSourceFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getObject() throws Exception {
        System.out.println("Creating QuickPerf datasource proxy");
        return QuickPerfSqlDataSourceBuilder.aDataSourceBuilder().buildProxy(dataSource);
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }
}
