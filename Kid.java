
public class Kid extends Person{
    private String favoriteCandy;

    public Kid()
    {
    	name = "Timmy";
    	age = 6;
        favoriteCandy = "Twix";
    }
    public Kid(int _age, int _number, String _name, String _candy)
    {
        age = _age;
        phoneNumber= _number;
        name = _name;
        favoriteCandy = _candy;
    }
    
    public String getCandy()
    {
    	return favoriteCandy;
    }
}
