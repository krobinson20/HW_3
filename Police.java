
public class Police extends Person implements Employee{
    public enum PoliceRole{Patrol, Sargent, Captain, Cheif};
    public PoliceRole Role;
    public Police()
    {
        Role = PoliceRole.Patrol;
    }
    public Police(int _age, int _number, String _name, PoliceRole _role)
    {
        age = _age;
        phoneNumber= _number;
        name = _name;
        Role = _role;
    }
    public boolean PayEmployee(Employee e){
        return true;
    }
    public boolean GetID(Employee e){
        return true;
    }
    public PoliceRole getrole()
    {
    	return Role; 
    }
}
