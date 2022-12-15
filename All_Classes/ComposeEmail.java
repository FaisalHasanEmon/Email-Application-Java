package All_Classes;
import java.io.*;
public class ComposeEmail extends Information implements Serializable{
    
    public String Mail_ID;
    public String Date;
    public String To;
    public String Subject;
    public String Compose;
    public Boolean InboxView;
    public Boolean SentboxView;
    public ComposeEmail(Boolean InboxView,Boolean SentboxView, String Mail_Id,String Date,String From, String To,String Subject,String Compose)
    {
        this.InboxView = InboxView;
        this.SentboxView = SentboxView;
        this.Mail_ID = Mail_Id;
        this.Date = Date;
        this.Email = From;
        this.To = To;
        this.Subject = Subject;
        this.Compose = Compose;
    }

}
