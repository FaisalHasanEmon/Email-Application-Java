import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.io.*;
import java.text.SimpleDateFormat;  
import java.net.SocketTimeoutException;
import java.rmi.StubNotFoundException;
import java.util.regex.Pattern;

import javax.lang.model.util.ElementScanner14;
import javax.print.attribute.SetOfIntegerSyntax;
import javax.swing.text.html.HTMLDocument.BlockElement;

import All_Classes.*;

public class Email{


//------------------------------------------ Function Email Address Checkings----------------------------------------------
static void Clear()
{
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}

static String Email_ID()
{
	double randomNumber = Math.random()*100000;
    int x = (int)randomNumber;
    return String.valueOf(x); 
}

static void Continue()
{
    Scanner cont = new Scanner(System.in);
    System.out.println();
    System.out.print("Press enter for continue... ");
    String con = cont.nextLine();

}

public static boolean isValid0(String email)
{
     String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";
     Pattern pat = Pattern.compile(emailRegex);
     if (email == null)
            return false;
        return pat.matcher(email).matches();
}
public static boolean isValid(String st)
{
    ArrayList<String> address = new ArrayList<>();
    address.add(st);
    for(String i : address)
    {
        if (isValid0(i))
             return true;
         else
            return false;
        }
        return false;
    }
//----------------------------------------- Function Email Address Checking Closed-----------------------------------------

//-----------------------------------------Function Phone Number Check-----------------------------------------------------

public static boolean Number_check(String X) 
{
    if (X.length() != 11) {
        return false;
    }
    for (int i = 0; i < X.length(); i++) {
        if (X.charAt(i) < '0'|| X.charAt(i) > '9') {
            return false;
        }
    }
    return true;
}

//-----------------------------------------Function Phone Number Check Closed-----------------------------------------------------


//----------------------------------------Main Function--------------------------------------------------
public static void main(String[] args) throws Exception{

    Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in);
//----------------------------------------Date --------------------------------------------------------


//----------------------------------------Date Closed--------------------------------------------------
//----------------------------------------Object Output & Input Stream----------------------------------
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
//---------------------------------Object Output & Input Stream Closed----------------------------------
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date = null;
//----------------------------------------Creating Files-------------------------------------------------
    File Folder = new File("Datas");
    Folder.mkdir();

    File file1= new File("Datas//Accounts.txt");
    File file2= new File("Datas//Compose.txt");

//---------------------------------------Files Closed----------------------------------------------------
        

//----------------------------------------Array List & Iterators-----------------------------------------
   ArrayList<Accounts> account = new ArrayList<Accounts>();
   ListIterator<Accounts> account_list = null;
   
   ArrayList<ComposeEmail> compose = new ArrayList<ComposeEmail>();
   ListIterator<ComposeEmail> compose_list = null;

//-------------------------------------Array List & Iteratos Closed--------------------------------------


//-------------------------------------Reading Files----------------------------------------------------
    if(file1.isFile())
    {
        //------>Accounts
        ois = new ObjectInputStream( new FileInputStream(file1));
        account = (ArrayList<Accounts>)ois.readObject();
        ois.close();
    }

    if(file2.isFile())
    {
        //------.>Compose
        ois = new ObjectInputStream( new FileInputStream(file2));
        compose = (ArrayList<ComposeEmail>)ois.readObject();
        ois.close();
    }

//-------------------------------------Reading Files Closed---------------------------------------------
    
String first_name,last_name,phone_number,email_add,password;
char page_selection = '~';
    do{

        Clear();
        System.out.println("----------------------------------------------");
        System.out.println("|                   E - MAIL                 |");
        System.out.println("----------------------------------------------");
        System.out.println("| 1 . Sign Up                                |");
        System.out.println("| 2 . Login                                  |");
        System.out.println("| 0 . Exit                                   |");
        System.out.println("----------------------------------------------");
        System.out.print("Select An Option---------> ");
        page_selection = sc1.next().charAt(0);
        switch(page_selection)
        {
            case '1':
                char signup_chck = '`';
                do
                {
                    Clear();
                    System.out.println("----------------------------------------------");
                    System.out.println("|                   SIGN UP                  |");
                    System.out.println("----------------------------------------------");
                    System.out.print("Enter First Name : ");
                    first_name = sc.nextLine();
                    System.out.print("Enter Last Name : ");
                    last_name = sc.nextLine();
                    System.out.print("Enter Phone Number : ");
                    phone_number = sc.nextLine();
                    Boolean phone_number_check =Number_check(phone_number);
                    System.out.print("Enter E-mail Address : ");
                    email_add = sc.nextLine();
                    Boolean email_add_chck = isValid(email_add);
                    if(!email_add_chck && !phone_number_check)
                    {
                        Clear();
                        System.out.println("----------------------------------------------");
                        System.out.println("|                   SIGN UP                  |");
                        System.out.println("----------------------------------------------");
                        System.out.println("Incorrect Email Address or Phone Number :/ Please Try again.");
                        System.out.println();
                        System.out.print("Press 0 for Exti : ");
                        signup_chck = sc1.next().charAt(0);
                    }

                    else {
                        account_list  = account.listIterator();
                        boolean account_chck = false;
                        while(account_list.hasNext())
                        {
                            Accounts acc= (Accounts)account_list.next();
                            if(email_add.equals(acc.Email))
                            {
                                Clear();
                                System.out.println("You Have Already An Account With This Email Address.\nPlease Login To Your Account :)");
                                System.out.print("Press 0 for Exit : ");
                                signup_chck = sc1.next().charAt(0);

                                account_chck = true;
                            }
                        }

                        if(account_chck == false)
                        {
                            System.out.print("Enter Password : ");
                            password = sc.nextLine();
                            account.add(new Accounts(first_name, last_name, phone_number, email_add, password));
                            oos = new ObjectOutputStream(new FileOutputStream(file1));
                            oos.writeObject(account);
                            oos.close();

                            Clear();
                            System.out.println("----------------------------------------------");
                            System.out.println("|                   SIGN UP                  |");
                            System.out.println("----------------------------------------------");
                            System.out.println("You have Successfully Signed up .\nNow you can login to your E-Mail account :) ");
                            System.out.println();
                            System.out.print("Press 0 for Exti : ");
                            signup_chck = sc1.next().charAt(0);
                        }   
                }
            }while(signup_chck!='0');

            break;
            //-------------------------------------------------------User Sign Up Page Closed------------------------------------------------------------------------------------


            //--------------------------------------------------------User Login Page-------------------------------------------------------------------------------------
            case '2':
                if(file1.isFile())
                {
                    Clear();
                    System.out.println("----------------------------------------------");
                    System.out.println("|                   LOGIN                   |");
                    System.out.println("----------------------------------------------");
                    System.out.print("Enter Email Address : ");
                    String user_email = sc.nextLine();
                    Boolean user_email_chck = isValid(user_email);
                    if(!user_email_chck)
                    {
                        
                        char invlaid_email_option = '~';
                        do
                        {
                            Clear();
                            System.out.println("Invalid Email Address");
                            System.out.println();
                            System.out.print("Exit (Press 0) : ");
                            invlaid_email_option = sc1.next().charAt(0);
                            switch(invlaid_email_option){};
                        }while(invlaid_email_option != '0');
                    }

                    else 
                    {
                        System.out.print("Enter Password : ");
                        String user_pass = sc.nextLine();

                        account_list = account.listIterator();
                        Boolean loging_chck = false;
                        while(account_list.hasNext())
                        {
                            Accounts acc = (Accounts)account_list.next();
                            if(user_email.equals(acc.Email) && user_pass.equals(acc.Password))
                            {
                                

                                char options = '~';
                                do{
                                    Clear();
                                    System.out.println("----------------------------------------------");
                                    System.out.println("|                   HOME PAGE                |");
                                    System.out.println("----------------------------------------------");

                                    System.out.println("1. Write Email");
                                    System.out.println("2. Inbox");
                                    System.out.println("3. Sent ");
                                    System.out.println("4. Account Settings");
                                    System.out.println("0. Logout");
                                    System.out.print("Select An Option : ");
                                    options = sc1.next().charAt(0);
                                    switch(options)
                                    {
                                        //---------------------------------------------------------------User Writing Email--------------------------------------------------
                                        case '1':
                                            Clear();
                                            System.out.println("----------------------------------------------");
                                            System.out.println("|                   WRITE E-mail             |");
                                            System.out.println("----------------------------------------------");
                                            String email_id = Email_ID();                                            
                                            date = new Date();
                                            String dt = formatter.format(date);
                                            System.out.println("Date : " +dt);
                                            System.out.println("From : "+user_email);
                                            System.out.print("To : ");
                                            Boolean reicver_view = true;
                                            Boolean sender_view = true;
                                            String reciver = sc.nextLine();
                                            Boolean reciver_email_chck = isValid(reciver);
                                            if(!reciver_email_chck)
                                            {
                                                char reciver_invalid_email_chck = '~';
                                                do
                                                {
                                                    Clear();
                                                    System.out.println("Invalid Email Address");
                                                    System.out.println();
                                                    System.out.print("Exit (Press 0) : ");
                                                    reciver_invalid_email_chck = sc1.next().charAt(0);
                                                    switch(reciver_invalid_email_chck){};
                                                }while(reciver_invalid_email_chck != '0');

                                            }
                                            else{

                                                System.out.print("Subject : ");
                                                String subject = sc.nextLine();
                                                System.out.print("Write Email : ");
                                                String write_email = sc.nextLine();

                                                
                                                char compose_options = '!';
                                                do
                                                {
                                                    System.out.println("1. Sent");
                                                    System.out.println("0. Go Back ");
                                                    System.out.print("Selelct An Option : ");
                                                    compose_options = sc1.next().charAt(0);
                                                    switch(compose_options)
                                                    {
                                                        //---------------------------------------Email Sent Option------------------------------------------------------
                                                        case '1':
                                                             
                                                             account_list = account.listIterator();
                                                             Boolean user_acc_check = false;
                                                             while(account_list.hasNext())
                                                             {
                                                                Accounts acc1 = (Accounts)account_list.next();
                                                                if(reciver.equals(acc1.Email))
                                                                {
                                                                    compose.add(new ComposeEmail(reicver_view,sender_view,email_id,dt,user_email, reciver, subject, write_email));
                                                                    oos = new ObjectOutputStream(new FileOutputStream(file2));
                                                                    oos.writeObject(compose);
                                                                    oos.close();

                                                                    char email_sent_message = '~';
                                                                    do
                                                                    {
                                                                    Clear();
                                                                    System.out.println("Email Successfully Sent :) ");

                                                                    System.out.println();
                                                                    System.out.print("Back (Press 0) : ");
                                                                    email_sent_message = sc1.next().charAt(0);
                                                                    switch(email_sent_message){}

                                                                    }while(email_sent_message !='0');
                                                                    user_acc_check = true;
                                                                }
                                                             }
                                                             if(user_acc_check == false)
                                                                {
                                                                    char email_not_sent_message = '~';
                                                                    do
                                                                    {
                                                                     Clear();
                                                                    System.out.println(reciver+" Dose not Exists");

                                                                    System.out.println();
                                                                    System.out.print("Back (Press 0) : ");
                                                                    email_not_sent_message = sc1.next().charAt(0);
                                                                    switch(email_not_sent_message){}

                                                                    }while(email_not_sent_message !='0');
                                                                }

                                                        break;
                                                        //---------------------------------------Email Sent Option Closed-----------------------------------------------
                                                        
                                                    }
                                                }while(compose_options!='0');
                                                






                                            }
                                        break;
                                        //---------------------------------------------------------------User Writing Email Closed--------------------------------------------

                                        //---------------------------------------------------------------User Inbox-----------------------------------------------------------
                                        case '2':
                                            Clear();
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            System.out.println("|                                    INBOX                                        |");
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            char inbox_view = '~';
                                            do{
                                            compose_list = compose.listIterator();
                                            Boolean inbox_check = false;
                                            int i = 1;
                                            
                                            while(compose_list.hasNext())
                                            {
                                                ComposeEmail cmp = (ComposeEmail)compose_list.next();
                                                if(user_email.equals(cmp.To)  && cmp.InboxView==true) 
                                                {
                                                    
                                                    
                                                    System.out.println(i+" .Mail Code : "+cmp.Mail_ID);
                                                    System.out.println("   Date : "+cmp.Date);
                                                    System.out.println("   From : "+cmp.From);
                                                    System.out.println("   To : "+cmp.To);
                                                    System.out.println("   Subject :"+cmp.Subject);
                                                    System.out.println("   Email : "+cmp.Compose);
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    i++;
                                                    inbox_check = true; 
                                                }
                                            }
                                            if(inbox_check == true)
                                            {
                                                System.out.println("1 .Delete Email ");
                                                System.out.println("0 .Back");
                                                System.out.print("Select An Option : ");
                                                inbox_view = sc1.next().charAt(0);
                                                switch(inbox_view)
                                                {
                                                    case '1':
                                                        System.out.print("Enter Mail Code : ");
                                                        String inbox_mail_code= sc.nextLine();
                                                        compose_list = compose.listIterator();
                                                        Boolean inbox_mail_chck = false;
                                                        while(compose_list.hasNext())
                                                        {
                                                            ComposeEmail comp = (ComposeEmail)compose_list.next();
                                                            if(inbox_mail_code.equals(comp.Mail_ID))
                                                            {
                                                                    
                                                                Boolean inboxview = false; 
                                                                Boolean sentboxview = comp.SentboxView;
                                                                
                                                                String new_date = comp.Date;
                                                                String newfrom = comp.From ;
                                                                String newto = comp.To;
                                                                String newsubject =  comp.Subject;
                                                                String newcompose = comp.Compose;

                                                                compose_list.set(new ComposeEmail(inboxview, sentboxview, inbox_mail_code, new_date, newfrom, newto, newsubject, newcompose));
                                                                inbox_mail_chck = true;
                                                            }
                                                        }
                                                        if(inbox_mail_chck == true)
                                                        {
                                                            oos = new ObjectOutputStream(new FileOutputStream(file2));
                                                            oos.writeObject(compose);
                                                            oos.close();
                                                            System.out.println("Email Successfully Deleted !!! ");
                                                        }
                                                        else 
                                                        {
                                                            System.out.println("Invalid Mail Code");
                                                        }
                                                        System.out.print("Press 0 for Back : ");
                                                        inbox_view = sc1.next().charAt(0);

                                                    break;
                                                }

                                            }
                                            else
                                            {
                                                System.out.println("Empty!!!");
                                                System.out.print("Press 0 for Back : ");
                                                inbox_view = sc1.next().charAt(0);
                                            }
                                            
                                        }while(inbox_view!='0');

                                        break;
                                        //---------------------------------------------------------------User Inbox Closed----------------------------------------------------

                                        //---------------------------------------------------------------User Sent Box---------------------------------------------------------
                                        case '3':
                                        Clear();
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            System.out.println("|                                    SENT                                        |");
                                            System.out.println("-----------------------------------------------------------------------------------");
                                            char Sent_view = '~';
                                            do{
                                            compose_list = compose.listIterator();
                                            Boolean inbox_check = false;
                                            int i = 1;
                                            
                                            while(compose_list.hasNext())
                                            {
                                                ComposeEmail cmp = (ComposeEmail)compose_list.next();
                                                if(user_email.equals(cmp.From)  && cmp.SentboxView==true) 
                                                {
                                                    
                                                    
                                                    System.out.println(i+" .Mail Code : "+cmp.Mail_ID);
                                                    System.out.println("   Date : "+cmp.Date);
                                                    System.out.println("   From : "+cmp.From);
                                                    System.out.println("   To : "+cmp.To);
                                                    System.out.println("   Subject :"+cmp.Subject);
                                                    System.out.println("   Email : "+cmp.Compose);
                                                    System.out.println("-----------------------------------------------------------------------------------");
                                                    i++;
                                                    inbox_check = true; 
                                                }
                                            }
                                            if(inbox_check == true)
                                            {
                                                System.out.println("1 .Delete Email ");
                                                System.out.println("0 .Back");
                                                System.out.print("Select An Option : ");
                                                Sent_view = sc1.next().charAt(0);
                                                switch(Sent_view)
                                                {
                                                    case '1':
                                                        System.out.print("Enter Mail Code : ");
                                                        String inbox_mail_code= sc.nextLine();
                                                        compose_list = compose.listIterator();
                                                        Boolean inbox_mail_chck = false;
                                                        while(compose_list.hasNext())
                                                        {
                                                            ComposeEmail comp = (ComposeEmail)compose_list.next();
                                                            if(inbox_mail_code.equals(comp.Mail_ID))
                                                            {
                                                                    
                                                                Boolean inboxview = comp.InboxView; 
                                                                Boolean sentboxview = false;
                                                                
                                                                String new_date = comp.Date;
                                                                String newfrom = comp.From ;
                                                                String newto = comp.To;
                                                                String newsubject =  comp.Subject;
                                                                String newcompose = comp.Compose;

                                                                compose_list.set(new ComposeEmail(inboxview, sentboxview, inbox_mail_code, new_date, newfrom, newto, newsubject, newcompose));
                                                                inbox_mail_chck = true;
                                                            }
                                                        }
                                                        if(inbox_mail_chck == true)
                                                        {
                                                            oos = new ObjectOutputStream(new FileOutputStream(file2));
                                                            oos.writeObject(compose);
                                                            oos.close();
                                                            System.out.println("Email Successfully Deleted !!! ");
                                                        }
                                                        else 
                                                        {
                                                            System.out.println("Invalid Mail Code");
                                                        }
                                                        System.out.print("Press 0 for Back : ");
                                                        Sent_view = sc1.next().charAt(0);

                                                    break;
                                                }

                                            }
                                            else
                                            {
                                                System.out.println("Empty!!!");
                                                System.out.print("Press 0 for Back : ");
                                                Sent_view = sc1.next().charAt(0);
                                            }
                                            
                                        }while(Sent_view!='0');

                                        break;
                                        //---------------------------------------------------------------User Sent Box Closed--------------------------------------------------

                                     //----------------------------------------------------------------User Account Settings-------------------------------------------------
                                        case '4':
                                        
                                        char user_acc_option = '~';
                                        do
                                        {
                                            Clear();
                                            System.out.println("--------------------------------------------------");
                                            System.out.println("|           ACCOUNT SETTINGS                     |");
                                            System.out.println("--------------------------------------------------");
                                            System.out.println("1 .View Information"); 
                                            System.out.println("2 .Change Password");
                                            System.out.println("0 .Exit ");
                                            System.out.print("Select An Option : ");
                                            user_acc_option = sc1.next().charAt(0);
                                            switch(user_acc_option)
                                            {
                                                case '1':
                                                    Clear();
                                                    System.out.println("--------------------------------------------------");
                                                    System.out.println("|           ACCOUNT INFORMATIONS                 |");
                                                    System.out.println("--------------------------------------------------");
                                                   
                                                   account_list = account.listIterator();
                                                   Boolean user_acc_view_chck = false;
                                                   while(account_list.hasNext())
                                                   {
                                                    Accounts acc1 = (Accounts)account_list.next();
                                                    if(user_email.equals(acc1.Email))
                                                    {
                                                        System.out.println("Name : "+acc1.First_Name+" "+acc1.Last_Name);
                                                        System.out.println("E-mail Address : "+acc1.Email);
                                                        System.out.println("Phone Number : "+acc1.Phone_Number);
                                                        System.out.println("Password : "+acc1.Password);
                                                        user_acc_view_chck = true;
                                                        Continue();
                                                    }
                                                   }
                                                   if(user_acc_view_chck == false)
                                                   {
                                                    System.out.println("User Not Found !!! ");
                                                   }



                                    
                                                break;

                                                case '2':
                                                   Clear();
                                                   System.out.println("--------------------------------------------------");
                                                   System.out.println("|                CHANGE PASSWORD                 |");
                                                   System.out.println("--------------------------------------------------");
                                                   System.out.print("Enter Phone Number : ");
                                                   String chck_number = sc.nextLine();

                                                   account_list = account.listIterator();
                                                   Boolean pass_change = false;
                                                   while(account_list.hasNext())
                                                   {
                                                    Accounts acc1 = (Accounts)account_list.next();
                                                    if(user_email.equals(acc1.Email) && chck_number.equals(acc1.Phone_Number))
                                                    {
                                                        
                                                        String new_firstname = acc1.First_Name;
                                                        String new_lastname = acc1.Last_Name;
                                                        
                                                        
                                                        
                                                        System.out.print("Enter New Password : ");
                                                        String new_pass = sc.nextLine();
                                                        account_list.set(new Accounts(new_firstname, new_lastname, chck_number, user_email, new_pass));
                                                        pass_change = true;
                                                        
                                                    }
                                                   }

                                                   if(pass_change== true)
                                                   {
                                                        oos = new ObjectOutputStream(new FileOutputStream(file1));
                                                        oos.writeObject(account);
                                                        oos.close();
                                                        System.out.println("Password Succefully Changed !!!");
                                                        Continue();
                                                   }
                                                   else
                                                   {
                                                    System.out.println("Invalid Phone Number !!! ");
                                                    Continue();
                                                   }

                                                break;
                                            }
                                            

                                        }while(user_acc_option !='0');

                                        break;
                                        //--------------------------------------------------------------User Account Settings Closed---------------------------------------------
                                    

                                    }
                                    loging_chck = true;

                                }while(options !='0');
                            }
                        }
                        if(loging_chck == false)
                        {
                            char incorrect_email_and_pass = '~';
                            do{
                                Clear();
                                System.out.println("Incorrect Email Address And Password.\nPlease Try Again !!!");
                                System.out.println();
                                System.out.print("Exit (Press 0) : ");
                                incorrect_email_and_pass = sc1.next().charAt(0);
                            }while(incorrect_email_and_pass!='0');
                        }

                    }

                }
                else 
                {
                    System.out.println("Nothing Found :( ");
                }



            break;
        //--------------------------------------------------------User Login Page Closed-------------------------------------------------------------------------------------

        }

    }while(page_selection!='0');
}
}