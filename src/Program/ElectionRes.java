package Program;

import java.io.Serializable;
import java.util.Vector;

public class ElectionRes implements Serializable{

	private Vector<Party>allParties;
	private Vector<Integer> votes;

	public ElectionRes() {
		this.allParties=new Vector<Party>();
		this.votes=new Vector<Integer>();
	}
	
	public void addParty(Party p) {
		this.allParties.add(p);
		votes.add(0);
	}

	public double getAllVotes() {
		int sum=0;
		for (int i = 0; i < allParties.size(); i++) {
			sum+=votes.get(i);
		}
		return sum;
	}

	public void addVote(int partyIndex) {
		if(partyIndex<=votes.size() && partyIndex!=-1) {
			int temp=votes.get(partyIndex);
			temp++;
			votes.insertElementAt(temp, partyIndex);
		}
	}
		
	public int getAllPartyVotes(int index) {
		if(index < allParties.size())
		return votes.get(index);
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder("electiom results:\n");
		for (int i = 0; i < allParties.size(); i++) {
			sb.append(allParties.get(i).getName()+": "+ votes.get(i)+"\n");
		}
		return sb.toString();
	}

}
