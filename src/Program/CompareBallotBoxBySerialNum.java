package Program;

import java.util.Comparator;

public class CompareBallotBoxBySerialNum implements Comparator<BallotBox<?>>{

	@Override
	public int compare(BallotBox<?> o1, BallotBox<?> o2) {
		if(o1.getSerialNum() > o2.getSerialNum())
			return 1;
		else if(o1.getSerialNum() < o2.getSerialNum())
			return -1;
		return 0;
	}
}
