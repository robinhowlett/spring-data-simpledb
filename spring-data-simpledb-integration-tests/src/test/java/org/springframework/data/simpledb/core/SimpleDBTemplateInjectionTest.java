package org.springframework.data.simpledb.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.simpledb.core.SimpleDbOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:simpledb-with-templates-context.xml")
public class SimpleDBTemplateInjectionTest {

	@Autowired
	@Qualifier("simpleDBTemplate1")
	private SimpleDbOperations operations1;

	@Autowired
	@Qualifier("simpleDBTemplate2")
	private SimpleDbOperations operations2;

	@Test
	public void autowired_operations_should_not_be_null() {
		assertNotNull(operations1);
		assertNotNull(operations2);
	}

	@Test
	public void operations_should_be_configured() {
		assertNotNull(operations1.getDB());
		assertNotNull(operations2.getDB());
	}
}
