package hu.elekest.preschoolvote.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.elekest.preschoolvote.exception.ErrorCode;
import hu.elekest.preschoolvote.exception.VoteServiceException;
import hu.elekest.preschoolvote.model.Child;
import hu.elekest.preschoolvote.service.VoteService;
import hu.elekest.preschoolvote.service.VoteServiceImpl;

public class PreschoolVoteCalculator {

	public static void main(String[] args) {
		try {
			VoteService voteService = VoteServiceImpl.getInstance();
			Child winner = voteService.processVotesAndGetWinner(getInputStreamFromConsole());
			System.out.println(
					"The winner is: '" + winner.getName() + "' with '" + winner.getNumberOfVotes() + "' votes!");
		} catch (VoteServiceException e) {
			System.out.println("An error occured with error code: '" + e.getErrorCode().name() + "' while running the program.");
		}
	}

	private static InputStream getInputStreamFromConsole() throws VoteServiceException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String input = "";
			StringBuffer stringBuffer = new StringBuffer();
			System.out.println("Enter the Children names (First name is the voter name, then comes the candidate names):");
			while (!(input = br.readLine()).isEmpty()) {
				stringBuffer.append(input).append("\n");
			}
			return new ByteArrayInputStream(stringBuffer.toString().getBytes("UTF-8"));

		} catch (IOException e) {
			throw new VoteServiceException(ErrorCode.INVALID_INPUT_STREAM, e);
		}
	}

}
