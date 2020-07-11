package Program;

import java.io.Serializable;

public class SickSoldier extends Soldier implements Coronable, Serializable{

	private int numberOfSickDays;

	public SickSoldier(String name,int id,BallotBox ballotBox,boolean isIsolated,int birthYear,boolean isCarryWeapon, int numberOfSickDays) throws Exception {
		super(name,id,ballotBox,isIsolated,birthYear,isCarryWeapon);
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
