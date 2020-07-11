package Program;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Citizen implements Serializable {

	protected String name;
	protected int id;
	protected BallotBox ballotBox;
	protected boolean isIsolated;
	protected int birthYear;

	public Citizen(String name, int id, BallotBox ballotBox, boolean isIsolated, int birthYear) throws Exception {
		this.name = name;
		setId(id);
		this.ballotBox = ballotBox;
		this.isIsolated = isIsolated;
		setBirthYear(birthYear);
	}
	
	//default C'tor
	public Citizen() {
	
	}
	 
	public boolean setBirthYear(int birthYear) throws Exception {
		if (!(birthYear <= 1900 || birthYear > LocalDate.now().getYear())) {
			this.birthYear = birthYear;
			return true;
		}
		throw new Exception("Birth year must be between 1900-" + LocalDate.now().getYear());
	}

	public boolean setId(int id) throws Exception {
		int temp = id;
		int count = 0;
		while (temp > 0) {
			temp = temp / 10;
			count++;
		}
		if (count == 9) {
			this.id = id;
			return true;
		}
		throw new Exception("Invalid id, id must be 9 digits");
	}

	public boolean getIsolation() {
		return isIsolated;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public boolean wantToVote() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	public BallotBox getballotBox() {
		return ballotBox;
	}

	public int getVotedPartyIndex(int size) {
		return (isIsolated && !isProtected()) ? -1 : (int) (Math.random() * size);
	}

	public boolean isProtected() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		}
		Citizen temp = (Citizen) other;
		return this.id == temp.id;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + name + "\nid: " + this.id + "\nBirth year: " + this.birthYear
				+ "\nVoteing at ballot box number: " + this.ballotBox.getSerialNum() + "\nis "
				+ (isIsolated ? "Isolated" : "not Isolated");
	}
}
