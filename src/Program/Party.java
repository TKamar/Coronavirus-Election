package Program;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public class Party<T extends Citizen> implements Serializable {

	private String name;
	private String section;
	private Date openingDate;
	private Vector <T> allPartyMembers;
	
	public Party(String name, String section, Date openingDate) {
		this.name=name;
		this.section=section;
		this.openingDate=openingDate;
		this.allPartyMembers=new Vector<T>();
		
	}
		
	public void addPartyMember(T p) {
		allPartyMembers.add(p);
		sort();
	}
	
	private void sort() {
		int indexMax=0;
		while(indexMax<allPartyMembers.size()) {
			for (int i = indexMax+1; i < allPartyMembers.size(); i++) {
				if(((PartyMember)allPartyMembers.get(indexMax)).getPlace() > ((PartyMember)allPartyMembers.get(i)).getPlace()) {
					swap(indexMax,i);
				}
			}
			indexMax++;
		}
	}
	private void swap(int from,int to) {
		PartyMember temp=(PartyMember)allPartyMembers.get(from);
		allPartyMembers.setElementAt(allPartyMembers.get(to), from);
		allPartyMembers.setElementAt((T)temp, to);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Party)){
			return false;
		}
		Party temp= (Party)other;
		return this.name.equals(temp.name);
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(getClass().getSimpleName()+": "+this.name+"\nsection: "+this.section+"\nopening date: "+this.openingDate+"\n");
		for (int i = 0; i < allPartyMembers.size(); i++) {
			sb.append(allPartyMembers.get(i)+"\n");
		}
		return sb.toString();
	}

}

