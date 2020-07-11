package Program;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ManegerClass <T extends Citizen> {
	private ElectionRound electionRound;
	private Vector <Party> allParties;
	private Vector <? extends BallotBox<? extends Citizen>> allBallotBox;
	private Vector <Citizen> allCitizens;

	public ManegerClass(ElectionRound electionRound,Vector <Party> allParties,Vector <BallotBox<T>> allBallotBox, Vector<Citizen> allCitizens) {
		this.electionRound=electionRound;
		this.allParties=allParties;
		this.allBallotBox=allBallotBox;
		this.allCitizens=allCitizens;
	}

	public void addCitizen(Citizen c) throws Exception {
		boolean isExict= false;
		for (int i = 0; i < allCitizens.size(); i++) {
			if(c.equals(allCitizens.get(i))){
				isExict=true;
			}
		}
		if(!isExict) {
			electionRound.addCitizen(c);
		}
		this.allCitizens=electionRound.getAllCitizens();
	}

	public void addParty (Party p) {
		electionRound.addParty(p);
		this.allParties=electionRound.getAllParties();
	}

	public ElectionRound getElectionRound() {
		return electionRound;
	}

	public Vector<Party> getAllParties(){
		return electionRound.getAllParties();
	}

	public void addPartyMember(PartyMember pM) throws Exception {
		pM.getParty().addPartyMember(pM);
		addCitizen(pM);
	}

	public void addBallotBox(BallotBox<? extends Citizen> b) {
		if(b.getBallotBoxType() == Citizen.class) {
			electionRound.addCitizenBallotBox((BallotBox<Citizen>)b);
		}else if(b.getBallotBoxType() == SickCitizen.class) {
			electionRound.addSickCitizenBallotBox((BallotBox<SickCitizen>)b);
		}else if(b.getBallotBoxType() == Soldier.class) {
			electionRound.addSoldierBallotBox((BallotBox<Soldier>)b);
		}else if(b.getBallotBoxType() == SickSoldier.class) {
			electionRound.addSickSoldierBallotBox((BallotBox<SickSoldier>)b);
		}

		this.allBallotBox=electionRound.getAllBallotBox();

		for (int i = 0; i < allParties.size(); i++) {
			allBallotBox.get(allBallotBox.size()-1).getElectionRes().addParty(allParties.get(i));
		}
	}

	public Vector<? extends BallotBox<? extends Citizen>> getAllBallotBoxes(){
		return allBallotBox;
	}


	public String showAllBallotBox() {
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < allBallotBox.size(); i++) {
			sb.append(allBallotBox.get(i)+"\n");
		}
		return sb.toString();
	}

	public String election() {
		for (int i = 0; i < allCitizens.size(); i++) {
			if(allCitizens.get(i).wantToVote()) {
				allCitizens.get(i).getballotBox().getElectionRes().addVote(allCitizens.get(i).getVotedPartyIndex(allParties.size()));
			}
		}
		for (int i = 0; i < allBallotBox.size(); i++) {
			allBallotBox.get(i).setVotersPrecentage();
		}

		return "\nElection round ended successfully\n";
	}

	public String showAllParties() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < allParties.size(); i++) {
			sb.append(allParties.get(i)+"\n");
		}
		return sb.toString();
	}

	public String showAllCitizens() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < allCitizens.size(); i++) {
			sb.append(allCitizens.get(i)+"\n"+"\n");
		}
		return sb.toString();
	}

	private int sumVotes(int index) {
		int sumVotes=0;
		for (int i = 0; i < allBallotBox.size(); i++) {
			sumVotes += allBallotBox.get(i).getElectionRes().getAllPartyVotes(index);
		}
		return sumVotes;
	}

	public String showResults() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < allBallotBox.size(); i++) {
			sb.append("Ballot box number: "+allBallotBox.get(i).getSerialNum()+
					"\nVoters precentage: "+new DecimalFormat("##.##").format(allBallotBox.get(i).getVotersPrecentage())+" %\n"+
					allBallotBox.get(i).getElectionRes()+"\n");
		}

		for (int i = 0; i < allParties.size(); i++) {
			sb.append(allParties.get(i).getName()+" : "+ sumVotes(i) + "\n");
		}

		return sb.toString();
	}
}
