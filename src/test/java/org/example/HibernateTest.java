package org.example;

import org.apache.commons.dbcp.BasicDataSource;
import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.quickperf.sql.annotation.ExpectSelect;
import org.quickperf.sql.config.QuickPerfSqlDataSourceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

public class HibernateTest {

    private SessionFactory sessionFactory;

    private DataSource createDataSource() {
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

    @BeforeMethod
    public void setUp() {

        DataSource dataSourceBase = createDataSource();
        DataSource dataSource = QuickPerfSqlDataSourceBuilder.aDataSourceBuilder().buildProxy(dataSourceBase);

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(Environment.DATASOURCE, dataSource)
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
//			StandardServiceRegistryBuilder.destroy( registry );
            throw new RuntimeException(e);
        }

    }

    @AfterMethod
    public void tearDown() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @ExpectSelect(123)
    @Test
    public void PersonListTest() {
        Session session = sessionFactory.openSession();

        TypedQuery<Person> fromPerson = session.createQuery("FROM Person", Person.class);
        fromPerson.getResultList();

        session.close();
    }

}
