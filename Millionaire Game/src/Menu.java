import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Menu extends JPanel {

    private JButton game;
    private JButton quit;
    private JButton edit;
    private Image backgroundM = null;

    public Menu(){

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLUE);
        this.setVisible(true);

        GridBagConstraints c = new GridBagConstraints();

        game = new JButton("New Game");

        edit = new JButton("Edit");

        quit = new JButton("Quit");

        backgroundM = getImage("menu.jpg");

        game.setBackground(Color.BLACK);
        quit.setBackground(Color.BLACK);

        game.setForeground(Color.WHITE);
        quit.setForeground(Color.WHITE);

        c.insets = new Insets(10, 0, 0, 10);

        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        this.add(game,c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(quit,c);
        this.validate();
    }

    public JButton getGame(){return game;}

    public JButton getQuit() {return quit;}

    public JButton getEdit(){return edit;}

    public Image getImage(String path) {
        Image image1 = null;
        try {
            URL imageURL = Menu.class.getResource(path);
            image1 = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 404: Image not Found");
        }
        return image1;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage( backgroundM , 0, 0, 1300, 720, this);
    }
}
