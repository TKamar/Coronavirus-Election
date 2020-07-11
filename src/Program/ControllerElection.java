package Program;

import java.util.Vector;

public class ControllerElection <T extends Citizen> implements viewListener, ModelListener{
	private ElectionRound theModel;
	private View theView;
	
	public ControllerElection(ElectionRound model, View view) {
		theModel= model;
		theView = view;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}

	@Override
	public void addBallotBox(BallotBox<? extends Citizen> b) {
		if(b.getBallotBoxType() == Citizen.class) {
			theModel.addCitizenBallotBox((BallotBox<Citizen>) b);
		}else if(b.getBallotBoxType() == SickCitizen.class) {
			theModel.addSickCitizenBallotBox((BallotBox<SickCitizen>) b);
		}else if(b.getBallotBoxType() == SickSoldier.class) {
			theModel.addSickSoldierBallotBox((BallotBox<SickSoldier>) b);
		}else
			theModel.addSoldierBallotBox((BallotBox<Soldier>)b);
	}

	@Override
	public void addCitizen(Citizen c) throws Exception {
		theModel.addCitizen(c);
	}

	@Override
	public void addParty(Party p) {
		theModel.addParty(p);
	}

	@Override
	public void viewAskedShowAllBallotBox() {
		theModel.showAllBallotBox();
	}

	@Override
	public void viewAskedShowAllCitizen() {
		theModel.showAllCitizens();
	}

	@Override
	public void viewAskedShowAllParties() {
		theModel.showAllParties();
	}

	@Override
	public void electionRun() {		
		theModel.election();
	}

	@Override
	public void viewAskedForResults() {
		theModel.showResults();
	}

	@Override
	public void modelupdatedShowAllBallotBox(String output) {
		theView.showAllBallotBox(output);
	}

	@Override
	public void modelUpdatedShowAllCitizen(String output) {
		theView.showAllCitizen(output);		
	}

	@Override
	public void modelUpdatedShowAllParties(String output) {
		theView.showAllParties(output);		
	}

	@Override
	public void modelUpdatedElectionEnded(String output) {
		theView.electionRunMessage(output);
	}

	@Override
	public void modelUpdatedResults(String output) {
		theView.showResults(output);
	}

	@Override
	public String getElectionRoundDate() {
		return theModel.getElectionRoundDate();
	}

	@Override
	public Vector<BallotBox<? extends Citizen>> getAllBallotBox() {
		return theModel.getAllBallotBox();
	}

	@Override
	public Vector<Party> getAllParties() {
		return theModel.getAllParties();
	}

}
