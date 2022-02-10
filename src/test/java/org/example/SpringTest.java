package org.example;

import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:spring-beans.xml"})
public class SpringTest extends AbstractTransactionalTestNGSpringContextTests  {

    @Autowired
    private PersonDAO personDao;

    @ExpectSelect(123)
    @Test
    public void GetPeopleTest() {
        personDao.getPersonList();
    }

}
