package Program;

public interface ElectionUIEvent {
	void registerListener(viewListener l);
	void showAllBallotBox(String output);
	void showAllCitizen(String output);
	void showAllParties(String output);
	void results(String output);
	void electionRunMessage(String msg);
}
