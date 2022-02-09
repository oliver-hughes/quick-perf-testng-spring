package org.example;

import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:spring-beans.xml"})
public class SpringTest extends QuickPerfAbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private PersonDAO personDao;

    @ExpectSelect(123)
    @Test
    public void GetPeopleTest() {
        personDao.getPersonList();
    }

}
