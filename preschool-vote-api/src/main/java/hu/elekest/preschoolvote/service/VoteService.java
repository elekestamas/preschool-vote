package hu.elekest.preschoolvote.service;

import java.io.InputStream;

import hu.elekest.preschoolvote.exception.VoteServiceException;
import hu.elekest.preschoolvote.model.Child;

public interface VoteService {

	Child processVotesAndGetWinner(InputStream inputStream) throws VoteServiceException;
	
}
