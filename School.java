/*
import resources.Building;
import resources.Kid;
import resources.Police;
import resources.Teacher;
*/
public class School  extends Building{
    
public School(){
    occupants[0] = new Kid();
    occupants[1] = new Teacher();
    occupants[3] = new Police(); 
}
public String nameatlocation(int i)
{
	return occupants[i].getname(); 
}

}
