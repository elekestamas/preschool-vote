package hu.elekest.preschoolvote.model;

import org.junit.Assert;
import org.junit.Test;

import hu.elekest.preschoolvote.model.Child;
import hu.elekest.preschoolvote.model.Vote;

public class VoteTest {

	@Test
	public void testWhenEquals() {
		Child child1 = new Child("test 1");
		Child child2 = new Child("test 1");
		
		Vote dto1 = new Vote(child1, child2);
		Assert.assertEquals(dto1, dto1);
		
		Vote dto2 = new Vote(child1, child2);
		Assert.assertEquals(dto1, dto2);
	}
	
	@Test
	public void testWhenNotEquals() {
		Child child1 = new Child("test 1");
		Child child2 = new Child("test 2");
		
		Vote dto1 = new Vote(child1, child2);
		Vote dto2 = new Vote(child2, child1);
		
		Assert.assertNotEquals(dto1, dto2);
	}
}
