package hu.elekest.preschoolvote.model;

import java.io.Serializable;

public class Vote implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7580487612603048714L;
	private final Child child;
	private final Child candidate;
	
	public Vote(Child child, Child candidate) {
		this.child = child;
		this.candidate = candidate;
	}

	public Child getVoter() {
		return child;
	}

	public Child getCandidate() {
		return candidate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
		result = prime * result + ((child == null) ? 0 : child.hashCode());
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
		Vote other = (Vote) obj;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
			return false;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("Child: '").append(child).append("' Candidate: '").append(candidate).append("'").toString();
	}
	
}
