
public class Teacher extends Person implements Employee{
    private String GradeLevel;
    private String Certification; 

    public Teacher()
    {
        GradeLevel = "12th grade";
        Certification = "Teaching";
    }
       public Teacher(int _age, int _number, String _name, String _gradelevel, String _Certification)
    {
        age = _age;
        phoneNumber= _number;
        name = _name;
        GradeLevel = _gradelevel;
        Certification = _Certification;
    }
     public boolean PayEmployee(Employee e){
        return true;
    }
    public boolean GetID(Employee e){
        return true;
    }
}
