package com.ibm.ABCDEF.analysis;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Basic Unit Test
 * 
 * @author wunan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/ws-config/cxf-client.xml" })
public abstract class SpringTransactionalContextTest {

	protected Logger logger = LoggerFactory.getLogger(getClass());

}
