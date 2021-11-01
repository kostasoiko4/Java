import java.util.ArrayList;

public class Account {

    private String username;
    private String password;
    private ArrayList<Email> mailbox;

    public Account(String username, String password){
        mailbox = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public ArrayList<Email> getMailbox() { return mailbox; }

    public String getPassword() { return password; }

    public String getUsername() { return username; }
}
