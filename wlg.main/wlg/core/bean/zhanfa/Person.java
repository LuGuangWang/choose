package wlg.core.bean.zhanfa;

public class Person {
	//打击队伍数  eg.声东击西
	private int[] persons = {0};
	
	public Person(int...p) {
		persons=p;
	}
	
	public int[] getPersons() {
		return persons;
	}
}
