import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Frame extends JFrame {
    private Panel p;
    private Menu m;
    private Question q;
    private QA qa;
    private int round;
    private int score;

    public Frame() {

        m = new Menu();

        java.util.Timer timer = new Timer();

        qa = new QA();
        qa.loadScores();

        this.setResizable(false);
        this.pack();
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        this.add(m);

        p = new Panel();

        round = 0;
        score = 0;

        m.getGame().addActionListener(e -> {
            m.setVisible(false);
            this.getContentPane().remove(m);
            this.getContentPane().add(p);
            p.setVisible(true);
        });

        updateQuestion();
        p.setLabels();

        p.getB1().addActionListener( e -> {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                if (p.getB1().getText().equals(p.getL1().getText())) {
                    p.getB1().setBackground(Color.GREEN);
                    JOptionPane.showMessageDialog(null, "Correct");
                    score++;
                }
                round++;
                endGame();
                updateQuestion();
                p.setLabels();
                p.getB1().setBackground(Color.RED);
                endGame();
                }
            }, 500);
        });
        p.getB2().addActionListener( e -> {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (p.getB2().getText().equals(p.getL1().getText())) {
                        p.getB2().setBackground(Color.GREEN);
                        JOptionPane.showMessageDialog(null, "Correct");
                        score++;
                    }
                    round++;
                    endGame();
                    updateQuestion();
                    p.setLabels();
                    p.getB2().setBackground(Color.RED);
                    endGame();
                }
            }, 500);
        });
        p.getB3().addActionListener( e -> {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (p.getB3().getText().equals(p.getL1().getText())) {
                        p.getB3().setBackground(Color.GREEN);
                        JOptionPane.showMessageDialog(null, "Correct");
                        score++;
                    }
                    round++;
                    endGame();
                    updateQuestion();
                    p.setLabels();
                    p.getB3().setBackground(Color.RED);
                }
            }, 500);
        });
        p.getB4().addActionListener( e -> {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (p.getB4().getText().equals(p.getL1().getText())) {
                        p.getB4().setBackground(Color.GREEN);
                        JOptionPane.showMessageDialog(null, "Correct");
                        score++;
                    }
                    round++;
                    endGame();
                    updateQuestion();
                    p.setLabels();
                    p.getB4().setBackground(Color.RED);
                }
            }, 500);
        });

        m.getQuit().addActionListener(e -> System.exit(0));
    }

    public void updateQuestion(){
        p.getLeb().setText(qa.getQuestionArray().get(round));
        p.getLeb2().setText(qa.getQuestion2Array().get(round));
        p.getL1().setText(qa.getAns1Array().get(round));
        p.getL2().setText(qa.getAns2Array().get(round));
        p.getL3().setText(qa.getAns3Array().get(round));
        p.getL4().setText(qa.getAns4Array().get(round));
    }

    public void endGame(){
        if(round == 6 || round > 6){
            JOptionPane.showMessageDialog(null, score+"/6");
            switch (score){
                case 0: {
                    JOptionPane.showMessageDialog(null, "Your Grade is F,You failed please try again");
                    break;
                }
                case 1: {
                    JOptionPane.showMessageDialog(null, "Your Grade is F,You failed please try again");
                    break;
                }
                case 2: {
                    JOptionPane.showMessageDialog(null, "Your Grade is D,You can do better");
                    break;
                }
                case 3: {
                    JOptionPane.showMessageDialog(null, "Your Grade is C,OK");
                    break;
                }
                case 4: {
                    JOptionPane.showMessageDialog(null, "Your Grade is B,Very Nice");
                    break;
                }
                case 5: {
                    JOptionPane.showMessageDialog(null, "Your Grade is A,Good Job");
                    break;
                }
                case 6: {
                    JOptionPane.showMessageDialog(null, "Your Grade is A+, Excellent!!");
                    break;
                }
            }
            p.setVisible(false);
            this.getContentPane().remove(p);
            this.getContentPane().add(m);
            m.setVisible(true);
            round = 0;

        }
    }

}
