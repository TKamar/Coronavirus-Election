package Program;

import java.util.Vector;


public interface viewListener {
	void addBallotBox(BallotBox<? extends Citizen> b);
	void addCitizen(Citizen c) throws Exception;
	void addParty(Party p);
	void viewAskedShowAllBallotBox();
	void viewAskedShowAllCitizen();
	void viewAskedShowAllParties();
	void electionRun();
	void viewAskedForResults();
	String getElectionRoundDate();
	Vector<BallotBox<? extends Citizen>> getAllBallotBox();
	Vector <Party> getAllParties();

}
