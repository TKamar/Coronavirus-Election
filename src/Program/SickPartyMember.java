package Program;

import java.io.Serializable;

public class SickPartyMember extends SickCitizen implements Coronable, Serializable{
	private Party party;
	private int placeInParty;
	private int numberOfSickDays;

	 public SickPartyMember(String name,int id,BallotBox ballotBox,boolean isIsolated,int birthYear,Party party,int placeInParty,int numberOfSickDays) throws Exception {
		super(name,id,ballotBox,isIsolated,birthYear,numberOfSickDays);
		this.party=party;
		this.placeInParty=placeInParty;
	}

	@Override
	public int getNumberOfSickDays() {
		return numberOfSickDays;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nparty: "+this.party.getName()+" place in party: "+placeInParty + " Number of sick days: " + numberOfSickDays;
	}


}
