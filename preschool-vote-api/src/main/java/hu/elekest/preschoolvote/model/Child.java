package hu.elekest.preschoolvote.model;

import java.io.Serializable;
import java.util.Comparator;

public class Child implements Serializable, Comparable<Child> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -204905283745012321L;
	private final String name;
	private Integer numberOfVotes;
	
	public void incrementNumberOfVotesWithOne() {
		numberOfVotes++;
	}
	
	public Child(String name, Integer numberOfVotes) {
		this.name = name;
		this.numberOfVotes = numberOfVotes;
	}
	
	public Child(String name) {
		this.name = name;
		this.numberOfVotes = 0;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Child other = (Child) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public Integer getNumberOfVotes() {
		return numberOfVotes;
	}

	@Override
	public int compareTo(Child other) {
		return numberOfVotes.compareTo(other.getNumberOfVotes());
	}
	
}
