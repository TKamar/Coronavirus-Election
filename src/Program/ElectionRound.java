package Program;

import java.io.Serializable;
import java.security.AllPermission;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class ElectionRound implements ElectionModelEvent, Serializable{

	private ModelListener listener;
	private int month;
	private int year;
	private Vector<Citizen> allCitizens;
	private Vector<Party> allParties;
	private Vector<BallotBox<Citizen>> allCitizenBallotBox;
	private Vector<BallotBox<SickCitizen>> allSickCitizenBallotBox;
	private Vector<BallotBox<Soldier>> allSoldierBallotBox;
	private Vector<BallotBox<SickSoldier>> allSickSoldierBallotBox;

	public ElectionRound(int month, int year) throws Exception {
		setMonth(month);
		setYear(year);
		this.allCitizens = new Vector<Citizen>();
		this.allParties = new Vector<Party>();

		this.allCitizenBallotBox = new Vector<BallotBox<Citizen>>();
		this.allSickCitizenBallotBox = new Vector<BallotBox<SickCitizen>>();
		this.allSoldierBallotBox = new Vector<BallotBox<Soldier>>();
		this.allSickSoldierBallotBox = new Vector<BallotBox<SickSoldier>>();
	}

	public boolean setMonth(int month) throws Exception {
		if (!(month <= 0 || month > 12)) {
			this.month = month;
			return true;
		}
		throw new Exception("Month must be between 1-12");
	}

	public boolean setYear(int year) throws Exception {
		if (!(year <= 0 || year > LocalDate.now().getYear())) {
			this.year = year;
			return true;
		}
		throw new Exception("Year must be between 1900-2020");

	}
	
@Override
	public void addCitizenBallotBox(BallotBox<Citizen> b) {
		allCitizenBallotBox.add(b);
	}

@Override
	public void addSickCitizenBallotBox(BallotBox<SickCitizen> b) {
		allSickCitizenBallotBox.add(b);
	}

@Override
	public void addSoldierBallotBox(BallotBox <Soldier> b) {
		allSoldierBallotBox.add(b);
	}

@Override
	public void addSickSoldierBallotBox(BallotBox <SickSoldier> b) {
		allSickSoldierBallotBox.add(b);
	}

@Override
	public void addCitizen(Citizen c) throws Exception {
		allCitizens.add(c);

		if(c.getClass() == Citizen.class || c.getClass() == PartyMember.class) {
			for (int i = 0; i < allCitizenBallotBox.size(); i++) {
				if(c.getballotBox().equals(allCitizenBallotBox.get(i)))
					allCitizenBallotBox.get(i).addVoter(c);
			}
		}else if(c.getClass() == SickCitizen.class) {
			for (int i = 0; i < allSickCitizenBallotBox.size(); i++) {
				if(c.getballotBox().equals(allSickCitizenBallotBox.get(i)))
					allSickCitizenBallotBox.get(i).addVoter((SickCitizen)c);
			}
		}else if(c.getClass() == Soldier.class) {
			for (int i = 0; i < allSoldierBallotBox.size(); i++) {
				if(c.getballotBox().equals(allSoldierBallotBox.get(i)))
					allSoldierBallotBox.get(i).addVoter((Soldier)c);
			}
		}else if(c.getClass() == SickSoldier.class) {
			for (int i = 0; i < allSickSoldierBallotBox.size(); i++) {
				if(c.getballotBox().equals(allSickSoldierBallotBox.get(i)))
					allSickSoldierBallotBox.get(i).addVoter((SickSoldier)c);
			}
		}
	}

@Override
	public void addParty(Party p) {
		allParties.add(p);
		// update election results parties list
		for (int i = 0; i < allCitizenBallotBox.size(); i++) {
			allCitizenBallotBox.get(i).getElectionRes().addParty(p);
		}
		for (int i = 0; i < allSickCitizenBallotBox.size(); i++) {
			allSickCitizenBallotBox.get(i).getElectionRes().addParty(p);
		}
		for (int i = 0; i < allSoldierBallotBox.size(); i++) {
			allSoldierBallotBox.get(i).getElectionRes().addParty(p);
		}
		for (int i = 0; i < allSickSoldierBallotBox.size(); i++) {
			allSickSoldierBallotBox.get(i).getElectionRes().addParty(p);
		}
	}

	public Vector<Party> getAllParties() {
		return allParties;
	}

	public Vector<Citizen> getAllCitizens() {
		return allCitizens;
	}

	public Vector<BallotBox<? extends Citizen>> getAllBallotBox() {
		Vector<BallotBox<? extends Citizen>> allBallotBox=new Vector<BallotBox<? extends Citizen>>();
		for (int j = 0; j < allCitizenBallotBox.size(); j++) {
			allBallotBox.add(allCitizenBallotBox.get(j));
		}
		for (int j = 0; j < allSickCitizenBallotBox.size(); j++) {
			allBallotBox.add(allSickCitizenBallotBox.get(j));
		}
		for (int j = 0; j < allSoldierBallotBox.size(); j++) {
			allBallotBox.add(allSoldierBallotBox.get(j));
		}
		for (int j = 0; j < allSickSoldierBallotBox.size(); j++) {
			allBallotBox.add(allSickSoldierBallotBox.get(j));
		}
		allBallotBox.sort(new CompareBallotBoxBySerialNum());
		return allBallotBox;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ElectionRound)) {
			return false;
		}
		ElectionRound temp = (ElectionRound) other;
		return temp.year == this.year && temp.month == this.month;
	}

	public void showAllBallotBox() {
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < getAllBallotBox().size(); i++) {
			sb.append(getAllBallotBox().get(i)+"\n");
		}
		listener.modelupdatedShowAllBallotBox(sb.toString());
	}
	
	public void showAllCitizens() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < allCitizens.size(); i++) {
			sb.append(allCitizens.get(i)+"\n"+"\n");
		}
		listener.modelUpdatedShowAllCitizen(sb.toString());
	}

	public void showAllParties() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < allParties.size(); i++) {
			sb.append(allParties.get(i)+"\n");
		}
		listener.modelUpdatedShowAllParties(sb.toString());
	}

	@Override
	public void election() {
		for (int i = 0; i < allCitizens.size(); i++) {
			if(allCitizens.get(i).wantToVote()) {
				allCitizens.get(i).getballotBox().getElectionRes().addVote(allCitizens.get(i).getVotedPartyIndex(allParties.size()));
			}
		}
		for (int i = 0; i < getAllBallotBox().size(); i++) {
			getAllBallotBox().get(i).setVotersPrecentage();
		}
		listener.modelUpdatedElectionEnded("Election round ended successfully\n");
	}

	@Override
	public void showResults() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < getAllBallotBox().size(); i++) {
			sb.append("Ballot box number: "+getAllBallotBox().get(i).getSerialNum()+
					"\nVoters precentage: "+new DecimalFormat("##.##").format(getAllBallotBox().get(i).getVotersPrecentage())+" %\n"+
					getAllBallotBox().get(i).getElectionRes()+"\n");
		}

		for (int i = 0; i < allParties.size(); i++) {
			sb.append(allParties.get(i).getName()+" : "+ sumVotes(i) + "\n");
		}

		listener.modelUpdatedResults(sb.toString());
	}
	
	private int sumVotes(int index) {
		int sumVotes=0;
		for (int i = 0; i < getAllBallotBox().size(); i++) {
			sumVotes += getAllBallotBox().get(i).getElectionRes().getAllPartyVotes(index);
		}
		return sumVotes;
	}
	
	public String getElectionRoundDate() {
		return month + "/" + year;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "---> " + month + "/" + year;
	}

	@Override
	public void registerListener(ModelListener l) {
		listener = l;
	}

}
