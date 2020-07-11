package Program;

import java.io.Serializable;

public class Soldier extends Citizen implements Soldierable, Serializable{
	
	protected boolean isCarryWeapon;
	
	public Soldier(String name,int id,BallotBox ballotBox,boolean isIsolated,int birthYear,boolean isCarryWeapon) throws Exception {
		super(name,id,ballotBox,isIsolated,birthYear);
		this.isCarryWeapon=isCarryWeapon;
	}
	
	@Override
	public boolean carryWeapon() {
		return isCarryWeapon;
	}
	
	@Override
	public String toString() {
		return super.toString()  + (isCarryWeapon ? "\nIs " : "\nNot ") + "carry weapon";
	}
}
