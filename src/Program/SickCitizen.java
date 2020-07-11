package Program;

import java.io.Serializable;

public class SickCitizen extends Citizen implements Coronable, Serializable{

	protected int numberOfSickDays;

	public SickCitizen(String name,int id,BallotBox ballotBox,boolean isIsolated,int birthYear,int numberOfSickDays) throws Exception {
		super(name,id,ballotBox,isIsolated,birthYear);
		this.numberOfSickDays=numberOfSickDays;
	}

	@Override
	public int getNumberOfSickDays() {
		return numberOfSickDays;
	}

	@Override
	public String toString() {
		return super.toString() + "\nNumber of sick days: " + numberOfSickDays;
	}
}
