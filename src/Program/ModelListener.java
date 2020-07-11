package Program;

public interface ModelListener {
	
	void modelupdatedShowAllBallotBox(String output);
	void modelUpdatedShowAllCitizen(String output);
	void modelUpdatedShowAllParties(String output);
	void modelUpdatedElectionEnded(String output);
	void modelUpdatedResults(String output);
}
