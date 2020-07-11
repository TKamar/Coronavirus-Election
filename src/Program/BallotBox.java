package Program;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class BallotBox <T extends Citizen> implements Serializable{
	
	protected static int serialNumGen=1;

	protected int serialNum;
	protected Address address;
	protected Vector <T> allVoters;
	protected double votersPrecentage;
	protected ElectionRes result;
	
	public BallotBox(Address address, ElectionRes result, Vector<T> allVoters) {
		this.serialNum = serialNumGen++;
		this.address = address;
		this.result = result;
		this.allVoters=new Vector<T>();
		for (int i = 0; i < allVoters.size(); i++) {
			this.allVoters.add(allVoters.get(i));
		}
	} 
	
	//default c'tor
	public BallotBox() {
		this(new Address("", "", 0), new ElectionRes(), new Vector<T>());
		serialNumGen--;
	}
			
	public void addVoter(T v) throws Exception {
		if(!(LocalDate.now().getYear() - ((Citizen)v).getBirthYear() < 18)) {
			allVoters.add(v);
		}
		else {
			throw new Exception("Voater must be at least 18 years old");
		}
	}
	
	public boolean setVotersPrecentage() {
		if(allVoters.size()!=0) {
		this.votersPrecentage=((result.getAllVotes()/allVoters.size())*100);
		}
		return true;
	}
	
	public int getSerialNum() {
		return serialNum;
	}
		
	public ElectionRes getElectionRes() {
		return result;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof BallotBox)) {
			return false;
		}
		BallotBox temp=(BallotBox)other;
		return this.serialNum==temp.serialNum;
	}
	
	public double getVotersPrecentage() {
		return votersPrecentage;
	}
	
	public Class getBallotBoxType() {
		return allVoters.get(0).getClass();
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("ballot box: "+serialNum+" address: "+address+"\nall voaters:\n");
		for (int i = 0; i < allVoters.size(); i++) {
			sb.append(allVoters.get(i)+"\n");
		}
		return sb.toString();
	}
	
}
