package hu.elekest.preschoolvote.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import hu.elekest.preschoolvote.exception.ErrorCode;
import hu.elekest.preschoolvote.exception.VoteServiceException;
import hu.elekest.preschoolvote.model.Child;
import hu.elekest.preschoolvote.model.Vote;

public class VoteServiceImpl implements VoteService {

	public Child processVotesAndGetWinner(InputStream inputStream) throws VoteServiceException {
		if (inputStream == null) {
			throw new VoteServiceException(ErrorCode.INPUT_STREAM_MAY_NOT_BE_NULL);
		}
		try {
			return getWinner(convertLinesToVotes(IOUtils.readLines(inputStream, "UTF-8")));
		} catch (IOException e) {
			throw new VoteServiceException(ErrorCode.INVALID_INPUT_STREAM, e);
		}
	}

	private List<Vote> convertLinesToVotes(List<String> lines) {
		Set<Vote> votes = new HashSet<>();
		List<Child> children = new ArrayList<>();
		lines.forEach((line) -> {
			List<String> names = Arrays.asList(line.split("\\s+"));
			names.listIterator(1).forEachRemaining((name) -> {
				votes.add(new Vote(getExistingOrCreateNewChildByName(children, names.get(0)),
						getExistingOrCreateNewChildByName(children, name)));
			});
		});
		return new ArrayList<Vote>(votes);
	}

	private Child getExistingOrCreateNewChildByName(List<Child> children, String childName) {
		return children.stream().filter(child -> StringUtils.equals(child.getName(), childName)).findFirst()
				.orElseGet(() -> {
					Child child = new Child(childName);
					children.add(child);
					return child;
				});
	}

	private Child getWinner(List<Vote> votes) throws VoteServiceException {
		try {
			votes.forEach((vote) -> vote.getCandidate().incrementNumberOfVotesWithOne());
			return votes.stream().map((vote) -> vote.getCandidate()).max((o1, o2) -> o1.compareTo(o2)).get();
		} catch (NoSuchElementException e) {
			throw new VoteServiceException(ErrorCode.NO_CANDIDATES_FOUND, e);
		}
	}

}
