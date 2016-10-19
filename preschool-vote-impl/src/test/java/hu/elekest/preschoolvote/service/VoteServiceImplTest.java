package hu.elekest.preschoolvote.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import hu.elekest.preschoolvote.exception.ErrorCode;
import hu.elekest.preschoolvote.exception.VoteServiceException;

public class VoteServiceImplTest {

	private static VoteService voteService;

	@Test
	public void testOnSucces() throws VoteServiceException, IOException {
		InputStream inputStream = new ByteArrayInputStream(new StringBuffer().append("Tam�s P�ter	J�zsef\n") //
				.append("G�za Istv�n  J�zsef\n") //
				.append("Tam�s P�ter P�ter P�ter		Enik�\n") //
				.append("Krisztina Emil	  Fl�ra\n") //
				.append("Krisztina Zolt�n	Ren�ta\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("J�zsef", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer().append("Tam�s P�ter\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("P�ter", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer()
				.append("G�za Istv�n  J�zsef\n") //
				.append("Tam�s P�ter P�ter P�ter		Enik�\n") //
				.append("Krisztina Emil	  Fl�ra\n") //
				.append("Krisztina Zolt�n	J�zsef\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("J�zsef", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer()
				.append("Tam�s K�rte Eper\n") //
				.append("Tam�s K�rte Torony\n") //
				.append("G�za Torony\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("Torony", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
	}

	@Test
	public void testWhenInputStreamIsNull() throws IOException {
		try {
			voteService.processVotesAndGetWinner(null);
			Assert.fail();
		} catch (VoteServiceException e) {
			Assert.assertEquals(ErrorCode.INPUT_STREAM_MAY_NOT_BE_NULL, e.getErrorCode());
		}
	}

	@Test
	public void testWithNoCandidates() throws UnsupportedEncodingException {
		InputStream inputStream = new ByteArrayInputStream(new StringBuffer().append("G�za\n") //
				.append("Tam�s\n") //
				.toString().getBytes("UTF-8"));
		try {
			voteService.processVotesAndGetWinner(inputStream);
			Assert.fail();
		} catch (VoteServiceException e) {
			Assert.assertEquals(ErrorCode.NO_CANDIDATES_FOUND, e.getErrorCode());
		}
	}

	@BeforeClass
	public static void initService() {
		voteService = VoteServiceImpl.getInstance();
	}
}
