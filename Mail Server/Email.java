public class Email {

    private boolean isNew;
    private String sender;
    private String receiver;
    private String subject;
    private String mainBody;

    public Email(String sender,String receiver,String subject,String mainBody){
        isNew = true;
        this.mainBody = mainBody;
        this.receiver = receiver;
        this.sender = sender;
        this.subject = subject;
    }

    public String getMainBody() { return mainBody; }

    public String getReceiver() { return receiver; }

    public String getSender() { return sender; }

    public String getSubject() { return subject; }

    public boolean getIsNew() { return isNew; }

    public void setRead(){ isNew = false; }
}
