package All_Classes;
import java.io.Serializable;

abstract class Information implements Serializable
{
    public String Email;
}

public class Accounts extends Information implements Serializable{
    public String First_Name;
    public String Last_Name;
    public String Phone_Number;
    public String Password;

    public Accounts(String First_Name, String Last_Name, String Phone_Number, String Email, String Password)
    {
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Phone_Number = Phone_Number;
        this.Email = Email;
        this.Password = Password;
    }

    


    
}
