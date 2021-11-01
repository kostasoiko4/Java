import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel {
    private Image backgroundP = null;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private ArrayList<JButton> buttons;
    private ArrayList<JLabel> labels;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel leb;
    private JLabel leb2;
    private final Random random = new Random();

    public Panel(){

        this.setLayout(null);
        this.setBackground(Color.GREEN);
        this.setVisible(true);

        backgroundP = getImage("game1.png");

        labels = new ArrayList<>();
        buttons = new ArrayList<>();

        l1 = new JLabel("");
        l2 = new JLabel("");
        l3 = new JLabel("");
        l4 = new JLabel("");

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();

        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);

        b1.setFont(new Font("idk", Font.BOLD, 13));
        b2.setFont(new Font("idk", Font.BOLD, 13));
        b3.setFont(new Font("idk", Font.BOLD, 13));
        b4.setFont(new Font("idk", Font.BOLD, 13));

        labels.add(l1);
        labels.add(l2);
        labels.add(l3);
        labels.add(l4);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);

        setLabels();

        leb = new JLabel("");
        leb2 = new JLabel("");

        leb.setFont(new Font("idk", Font.BOLD, 18));
        leb.setBounds(425,380,450,50);
        leb.setForeground(Color.WHITE);

        leb2.setFont(new Font("idk", Font.BOLD, 18));
        leb2.setBounds(425,400,460,50);
        leb2.setForeground(Color.WHITE);

        b1.setBounds(420,520,225,50);
        b2.setBounds(655,520,225,50);
        b3.setBounds(420,580,225,50);
        b4.setBounds(655,580,225,50);

        b1.setBackground(Color.RED);
        b2.setBackground(Color.RED);
        b3.setBackground(Color.RED);
        b4.setBackground(Color.RED);

        b1.setBorderPainted(true);

        this.add(leb);
        this.add(leb2);

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.validate();

    }

    public Image getImage(String path) {
        Image image1 = null;
        try {
            URL imageURL = Panel.class.getResource(path);
            image1 = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 404: Image not Found");
        }
        return image1;
    }

    /** This function paints the background.
     * @param g the graphics object.
     */

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(backgroundP , 0, 0, 1300, 720, this);
    }

    public int getRandom() {return random.nextInt(labels.size());}

    public void setLabels(){
        for (int i = 0; i < buttons.size();i++) {
            int a = getRandom();
            buttons.get(i).setText(labels.get(a).getText());
            labels.remove(a);
        }
        labels.add(l1);
        labels.add(l2);
        labels.add(l3);
        labels.add(l4);
    }

    public JLabel getLeb(){return leb;}
    public JLabel getLeb2(){return leb2;}
    public JLabel getL1(){return l1;}
    public JLabel getL2(){return l2;}
    public JLabel getL3(){return l3;}
    public JLabel getL4(){return l4;}
    public JButton getB1(){return b1;}
    public JButton getB2(){return b2;}
    public JButton getB3(){return b3;}
    public JButton getB4(){return b4;}
}
