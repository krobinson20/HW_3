/*
import resources.Building;
import resources.Kid;
import resources.Police;
import resources.Teacher;
*/
public class CityHall extends Building{
	public CityHall(){
	    occupants[0] = new Kid();
	    occupants[1] = new Teacher();
	    occupants[3] = new Police(); 
	}
	
	public String nameatlocation(int i)
	{
		return occupants[i].getname(); 
	}
	}