package hu.elekest.preschoolvote.model;

import org.junit.Assert;
import org.junit.Test;

import hu.elekest.preschoolvote.model.Child;

public class ChildTest {

	@Test
	public void testWhenEquals() {
		Child dto1 = new Child("test 1");
		Assert.assertEquals(dto1, dto1);
		
		Child dto2 = new Child("test 1");
		Assert.assertEquals(dto1, dto2);
		
		dto1 = new Child("test 1", 1);
		dto2 = new Child("test 1", 14);
		Assert.assertEquals(dto1, dto2);
	}
	
	@Test
	public void testWhenNotEquals() {
		Child dto1 = new Child("test 1");
		Child dto2 = new Child("test 2");
		
		Assert.assertNotEquals(dto1, dto2);
	}
}
