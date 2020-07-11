package Program;


public interface ElectionModelEvent {
	void registerListener(ModelListener l);
	void addCitizenBallotBox(BallotBox<Citizen> b);
	void addSickCitizenBallotBox(BallotBox<SickCitizen> b);
	void addSoldierBallotBox(BallotBox <Soldier> b);
	void addSickSoldierBallotBox(BallotBox <SickSoldier> b);
	void addCitizen(Citizen c) throws Exception;
	void addParty(Party p);
	void showAllBallotBox();
	void showAllCitizens();
	void showAllParties();
	void election();
	void showResults();
}
