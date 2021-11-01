import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class MailServerApp {

    public static void main(String[] args) {
        try{
            ServerSocket listenSocket = new ServerSocket(7896);
            ArrayList<Account> accounts = new ArrayList<>();
            //
            Account ac1 = new Account("kostas@smail.com","12345");
            Account ac2 = new Account("giannhs@smail.com","54321");
            Account ac3 = new Account("alexis@smail.com","12543");
            //
            Email em1 = new Email("kostas@smail.com","giannhs@smail.com","Job Interview","Our Appointment Has Been Rescheduled For Next Monday");
            Email em2 = new Email("kostas@smail.com","alexis@smail.com","Job Interview","Our Appointment Has Been Rescheduled For Next Monday");

            Email em3 = new Email("giannhs@smail.com","kostas@smail.com","Appliance No.1","I'd Like To Apply For The Job You Are Offering");
            Email em4 = new Email("giannhs@smail.com","alexis@smail.com","Car Offer","I'm Contacting You For The Car You Are Selling. Is It Still Available?");

            Email em5 = new Email("alexis@smail.com","kostas@smail.com"," Appliance No.2","I'd Like To Apply For The Job You Are Offering");
            Email em6 = new Email("alexis@smail.com","giannhs@smail.com","Car Sale","I'm Selling A VW Golf 2008', Black Color For 5.000 € ");

            Email em7 = new Email("spambot@smail.com","kostas@smail.com","Weightloss","WANT TO LOSE WEIGHT?\n" +
                    "\n" +
                    "The most powerful weightloss is now available\n" +
                    "without prescription. All natural Adipren720\n" +
                    "100% Money Back Guarantee!\n" +
                    "\n" +
                    "– Lose up to 19% Total Body Weight.\n" +
                    "– Up to 300% more Weight Loss while dieting.\n" +
                    "– Loss of 20-35% abdominal Fat.\n" +
                    "– Reduction of 40-70% overall Fat under skin.\n" +
                    "– Increase metabolic rate by 76.9% without Exercise.\n" +
                    "– Burns calorized fat.\n" +
                    "– Suppresses appetite for sugar.\n" +
                    "– Boost your Confidence level and Self Esteem.\n" +
                    "\n" +
                    "Get the facts about all-natural Adipren720: {LINK}");
            Email em8 = new Email("spambot@smail.com","giannhs@smail.com","Weightloss","WANT TO LOSE WEIGHT?\n" +
                    "\n" +
                    "The most powerful weightloss is now available\n" +
                    "without prescription. All natural Adipren720\n" +
                    "100% Money Back Guarantee!\n" +
                    "\n" +
                    "– Lose up to 19% Total Body Weight.\n" +
                    "– Up to 300% more Weight Loss while dieting.\n" +
                    "– Loss of 20-35% abdominal Fat.\n" +
                    "– Reduction of 40-70% overall Fat under skin.\n" +
                    "– Increase metabolic rate by 76.9% without Exercise.\n" +
                    "– Burns calorized fat.\n" +
                    "– Suppresses appetite for sugar.\n" +
                    "– Boost your Confidence level and Self Esteem.\n" +
                    "\n" +
                    "Get the facts about all-natural Adipren720: {LINK}");
            Email em9 = new Email("spambot@smail.com","alexis@smail.com","Weightloss","WANT TO LOSE WEIGHT?\n" +
                    "\n" +
                    "The most powerful weightloss is now available\n" +
                    "without prescription. All natural Adipren720\n" +
                    "100% Money Back Guarantee!\n" +
                    "\n" +
                    "– Lose up to 19% Total Body Weight.\n" +
                    "– Up to 300% more Weight Loss while dieting.\n" +
                    "– Loss of 20-35% abdominal Fat.\n" +
                    "– Reduction of 40-70% overall Fat under skin.\n" +
                    "– Increase metabolic rate by 76.9% without Exercise.\n" +
                    "– Burns calorized fat.\n" +
                    "– Suppresses appetite for sugar.\n" +
                    "– Boost your Confidence level and Self Esteem.\n" +
                    "\n" +
                    "Get the facts about all-natural Adipren720: {LINK}");
            //
            accounts.add(ac1);
            accounts.add(ac2);
            accounts.add(ac3);

            accounts.get(0).getMailbox().add(em3);
            accounts.get(0).getMailbox().add(em5);
            accounts.get(0).getMailbox().add(em7);

            accounts.get(1).getMailbox().add(em1);
            accounts.get(1).getMailbox().add(em6);
            accounts.get(1).getMailbox().add(em8);

            accounts.get(2).getMailbox().add(em2);
            accounts.get(2).getMailbox().add(em4);
            accounts.get(2).getMailbox().add(em9);
            //
            while(true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Request from client" + clientSocket.getInetAddress()+" at port "+ clientSocket.getPort());
                Connection c = new Connection(accounts,clientSocket);
            }
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;

    ArrayList<Account> accounts;
    Socket clientSocket;

    public Connection (ArrayList<Account> accounts,Socket clientSocket) {
        this.accounts = accounts;
        this.clientSocket = clientSocket;
        this.start();
    }

    public void run(){
        MailServer mailServer = new MailServer(accounts);
        mailServer.setClientSocket(clientSocket);
    }
}