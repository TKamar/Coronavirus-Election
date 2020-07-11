package Program;

import java.io.Serializable;

public class PartyMember extends Citizen implements Serializable {

	protected Party party;
	protected int placeInParty;
	
	public PartyMember(String name,int id,BallotBox ballotBox,boolean isIsolated,int birthYear,Party party,int placeInParty) throws Exception {
		super (name,id,ballotBox,isIsolated,birthYear);
		this.party=party;
		this.placeInParty=placeInParty;
	}
	
	public Party getParty() {
		return party;
	}
	
	public int getPlace() {
		return placeInParty;
	}
	
	@Override
	public String toString() {
		return super.toString()+"\nparty: "+this.party.getName()+" place in party: "+placeInParty;
	}
}
