import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MailServer {

    private ArrayList<Account> accounts;
    private String name;

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public MailServer(ArrayList<Account> accounts){
        this.accounts = accounts;
        name = "";
    }

    public void setClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
        try {
            in = new DataInputStream( clientSocket.getInputStream());
            out = new DataOutputStream( clientSocket.getOutputStream());
            showMenu();
            mainMenu();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }

    public void showMenu() {
        try {
            out.writeUTF("===============\n" + "Mail Server\n" + "===============\n" + "You Are Connected As A Guest!\n" + "---------------\n" + "> Register\n" + "> Log In\n" + "> Exit\n" + "===============");
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        }
    }

    public void showAcc(){
       try {
           out.writeUTF("===============\n"+"Mail Server\n"+"===============\n"+"You Are Connected As " + name+"\n---------------\n"+"> New Email\n"+"> Show Emails\n"+"> Read Email\n"+"> Delete Email\n"+"> Log Out\n"+"===============");
       }catch (EOFException e){
           System.out.println("EOF:"+e.getMessage());
       } catch(IOException e) {
           System.out.println("readline:"+e.getMessage());
       }
    }

    public void mainMenu(){

        try {
            String option = in.readUTF();

            switch (option){
                case "Register":{
                    out.writeUTF("===============\n"+"Register\n"+"---------------\n"+"Username: ");
                    String n = in.readUTF();
                    out.writeUTF("Password: ");
                    String p = in.readUTF();
                    register(n,p);
                    showMenu();
                    mainMenu();
                    break;
                }
                case "Log In":{
                    out.writeUTF("===============\n"+"Log In\n"+"---------------\n"+"Username: ");
                    String n = in.readUTF();
                    out.writeUTF("Password: ");
                    String p = in.readUTF();
                    logIn(n,p);
                    break;
                }
                case "Exit": {
                    out.writeUTF("===============\n"+"Exit\n"+"===============");
                    exit();
                    break;
                }
            }
            out.writeUTF("Please Use a Valid Option\n"+"Press Enter to Continue");
            String cont = in.readUTF();
            showMenu();
            mainMenu();
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
        }

    }

    public void accMenu(){
        try {
            String option = in.readUTF();

            switch (option){
                case "New Email":{
                    out.writeUTF("===============\n"+"New Email\n"+"---------------\n"+"Receiver: ");
                    String r = in.readUTF();
                    out.writeUTF("Subject: ");
                    String su = in.readUTF();
                    out.writeUTF("Main Body: ");
                    String mb = in.readUTF();
                    newEmail(r,su,mb);
                    showAcc();
                    accMenu();
                    break;
                }
                case "Show Emails":{
                    showEmails();
                    showAcc();
                    accMenu();
                    break;
                }
                case "Read Email": {
                    out.writeUTF("===============\n"+"Read Email\n"+"---------------\n"+"Email ID: ");
                    int id = Integer.parseInt(in.readUTF());
                    readEmail(id-1);
                    showAcc();
                    accMenu();
                    break;
                }
                case "Delete Email":{
                    out.writeUTF("===============\n"+"Delete Email\n"+"---------------\n"+"Email ID: ");
                    int id = Integer.parseInt(in.readUTF());
                    deleteEmail(id-1);
                    showAcc();
                    accMenu();
                    break;
                }
                case "Log Out":{
                    logOut();
                    showMenu();
                    mainMenu();
                }
            }
            out.writeUTF("Please Use a Valid Option\n"+"Press Enter to Continue");
            String cont = in.readUTF();
            showAcc();
            accMenu();
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
        }
    }

    public void register(String name, String pass){
           for(int i = 0; i < accounts.size(); i++) {
               if (name.equals(accounts.get(i).getUsername())) {
                   try {
                       out.writeUTF("===============\n"+"Account Already Exists!\n"+"Please Try Again\n"+"Press Enter to Continue!");
                       String countinue = in.readUTF();
                   }catch (EOFException e){
                       System.out.println("EOF:"+e.getMessage());
                   } catch(IOException e) {
                       System.out.println("readline:"+e.getMessage());
                   }
                   return;
               }
           }
           Account acc = new Account(name, pass);
           accounts.add(acc);
           try {
               out.writeUTF("===============\n"+"Account Created\n"+"Press Enter to Continue!");
               String countinue = in.readUTF();
           }catch (EOFException e){
               System.out.println("EOF:"+e.getMessage());
           } catch(IOException e) {
               System.out.println("readline:"+e.getMessage());
           }
    }


    public void logIn(String name,String pass){
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (name.equals(accounts.get(i).getUsername()) && pass.equals(accounts.get(i).getPassword())) {
                try {
                    out.writeUTF("===============\n"+"Welcome Back " + name +"\nPress Enter to Continue!");
                    String countinue = in.readUTF();
                }catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
                } catch(IOException e) {
                    System.out.println("readline:"+e.getMessage());
                }
                this.name = name;
                showAcc();
                accMenu();
                return;
            }
        }
        try {
            out.writeUTF("===============\n"+"Authentication Failed Please Try Again\n"+"Press Enter to Continue!");
            String countinue = in.readUTF();
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
        }
        showMenu();
        mainMenu();
    }

    public void newEmail(String receiver,String subject,String mainBody){
        Email email = new Email(name,receiver,subject,mainBody);
        for (int i = 0; i < accounts.size(); i++){
            if(receiver.equals(accounts.get(i).getUsername())){
                accounts.get(i).getMailbox().add(email);
                try {
                    out.writeUTF("===============\n"+"Mail Sent\n"+"Press Enter to Continue!");
                    String countinue = in.readUTF();
                }catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
                } catch(IOException e) {
                    System.out.println("readline:"+e.getMessage());
                }
                return;
            }
        }
        try {
            out.writeUTF("===============\n"+"No User With This Name Was Found\n"+"Press Enter to Continue!");
            String countinue = in.readUTF();
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
        }
    }

    public void showEmails(){
        for(int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getUsername())){
                try {
                    StringBuilder emails = new StringBuilder();

                    emails.append("===============\n"+"Show Emails\n"+"---------------\n");
                    emails.append("ID " + "        " + " From               " + "  Subject \n");
                    for(int j = 0; j < accounts.get(i).getMailbox().size(); j++) {
                        if(accounts.get(i).getMailbox().get(j).getIsNew()){
                            emails.append("---------------\n"+(j+1)+". " + " New!   " + " " + accounts.get(i).getMailbox().get(j).getSender() + "    " + accounts.get(i).getMailbox().get(j).getSubject()+"\n");
                        }
                        else {
                            emails.append("---------------\n"+(j+1)+". " + "        " + " " + accounts.get(i).getMailbox().get(j).getSender() + "    " + accounts.get(i).getMailbox().get(j).getSubject()+"\n");
                        }
                    }
                    emails.append("===============\n"+"Press Enter to Continue!");
                    out.writeUTF(emails.toString());
                    String countinue = in.readUTF();
                }catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
                } catch(IOException e) {
                    System.out.println("readline:"+e.getMessage());
                }
            }
        }
    }

    public void readEmail(int id){
        for(int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getUsername())) {
                int j;
                for(j = 0; j < accounts.get(i).getMailbox().size(); j++) {
                    if (id == j) {
                        if (accounts.get(i).getMailbox().get(id).getIsNew()) {
                            accounts.get(i).getMailbox().get(id).setRead();
                        }
                        try {
                            out.writeUTF("===============\n"+"Email ID: " + (id+1) + "\nFrom: " + accounts.get(i).getMailbox().get(id).getSender()+ "\nSubject: " + accounts.get(i).getMailbox().get(id).getSubject()+"\n---------------\n"+accounts.get(i).getMailbox().get(id).getMainBody()+"\n===============\n" +"Press Enter to Continue!");
                            String countinue = in.readUTF();
                        }catch (EOFException e){
                            System.out.println("EOF:"+e.getMessage());
                        } catch(IOException e) {
                            System.out.println("readline:"+e.getMessage());
                        }
                        return;
                    }
                }
                try {
                    out.writeUTF("===============\n"+"Email Not Found\n"+"Press Enter to Continue!");
                    String countinue = in.readUTF();
                }catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
                } catch(IOException e) {
                    System.out.println("readline:"+e.getMessage());
                }
            }
        }
    }

    public void deleteEmail(int id){
        for(int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getUsername())) {
                int j;
                for(j = 0; j < accounts.get(i).getMailbox().size(); j++) {
                    if (id == j) {
                        accounts.get(i).getMailbox().remove(id);
                        try {
                            out.writeUTF("===============\n"+"Email Deleted\n"+"===============\n" +"Press Enter to Continue!");
                            String countinue = in.readUTF();
                        }catch (EOFException e){
                            System.out.println("EOF:"+e.getMessage());
                        } catch(IOException e) {
                            System.out.println("readline:"+e.getMessage());
                        }
                        return;
                    }
                }
                try {
                    out.writeUTF("===============\n"+"Email Not Found\n"+"Press Enter to Continue!");
                    String countinue = in.readUTF();
                }catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
                } catch(IOException e) {
                    System.out.println("readline:"+e.getMessage());
                }
            }
        }
    }

    public void logOut(){
        name = "";
        try {
            out.writeUTF("===============\n"+"Log Out\n"+"===============\n"+"You have Logged Out\n"+"Press Enter to Continue!");
            String countinue = in.readUTF();
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
        }
    }

    public void exit(){
        if(clientSocket!=null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
