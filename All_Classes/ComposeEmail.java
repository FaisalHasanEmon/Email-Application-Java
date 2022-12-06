package All_Classes;
import java.io.*;
public class ComposeEmail implements Serializable{
    
    public String Mail_ID;
    public String Date;
    public String From;
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
        this.From = From;
        this.To = To;
        this.Subject = Subject;
        this.Compose = Compose;
    }

}
