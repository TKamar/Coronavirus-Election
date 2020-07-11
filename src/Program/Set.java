package Program;

import java.io.Serializable;
import java.util.Vector;

public class Set <T extends Citizen> implements Serializable {

	private Vector <T> allCitizens;

	public Set() {
		this.allCitizens=new Vector<T>();
	}

	public boolean add(T c) {
		boolean isExict=false;
		for (int i = 0; i < allCitizens.size(); i++) {
			if(c.equals(allCitizens.get(i))) {
				isExict=true;
			}
		}

		if(!(isExict)) {
			allCitizens.add(c);
		}

		return !(isExict);
	}
}
