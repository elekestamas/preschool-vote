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
		InputStream inputStream = new ByteArrayInputStream(new StringBuffer().append("Tamás Péter	József\n") //
				.append("Géza István  József\n") //
				.append("Tamás Péter Péter Péter		Enikõ\n") //
				.append("Krisztina Emil	  Flóra\n") //
				.append("Krisztina Zoltán	Renáta\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("József", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer().append("Tamás Péter\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("Péter", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer()
				.append("Géza István  József\n") //
				.append("Tamás Péter Péter Péter		Enikõ\n") //
				.append("Krisztina Emil	  Flóra\n") //
				.append("Krisztina Zoltán	József\n") //
				.toString().getBytes("UTF-8"));
		Assert.assertEquals("József", voteService.processVotesAndGetWinner(inputStream).getName());
		inputStream.close();
		
		inputStream = new ByteArrayInputStream(new StringBuffer()
				.append("Tamás Körte Eper\n") //
				.append("Tamás Körte Torony\n") //
				.append("Géza Torony\n") //
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
		InputStream inputStream = new ByteArrayInputStream(new StringBuffer().append("Géza\n") //
				.append("Tamás\n") //
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
