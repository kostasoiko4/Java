import java.io.*;
import java.util.ArrayList;

public class QA {
    private static QA q;
    private String filepath;
    private String Questions;

    private ArrayList<String> questions;
    private ArrayList<String> questions2;
    private ArrayList<String> ans1;
    private ArrayList<String> ans2;
    private ArrayList<String> ans3;
    private ArrayList<String> ans4;

    public QA(){

        filepath = new File("").getAbsolutePath();
        Questions = "Questions";
        ans1 = new ArrayList<>();
        ans2 = new ArrayList<>();
        ans3 = new ArrayList<>();
        ans4 = new ArrayList<>();
        questions = new ArrayList<>();
        questions2 = new ArrayList<>();
    }

    public static QA getInstance(){
        if(q == null){
            q = new QA();
        }
        return q;

        /**This function adds a player to the leader board if his score is high enough
         *
         */


    }

    /**
     * This function loads the leader board scores
     */

    public void loadScores(){
        try{
            File f = new File(filepath,Questions);
            if(!f.isFile()){
                createSaveData();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            ans1.clear();
            ans2.clear();
            ans3.clear();
            ans4.clear();
            questions.clear();
            questions2.clear();

            String[] a1 = reader.readLine().split("-");
            String[] a2 = reader.readLine().split("-");
            String[] a3 = reader.readLine().split("-");
            String[] a4 = reader.readLine().split("-");
            String[] question = reader.readLine().split("-");
            String[] question2 = reader.readLine().split("-");

            for(int i =0;i<a1.length;i++){
                ans1.add(a1[i]);
            }
            for(int i =0;i<a2.length;i++){
                ans2.add(a2[i]);
            }
            for(int i =0;i<a3.length;i++){
                ans3.add(a3[i]);
            }
            for(int i =0;i<a4.length;i++){
                ans4.add(a4[i]);
            }
            for(int i =0;i<question.length;i++){
                questions.add(question[i]);
            }
            for(int i =0;i<question2.length;i++){
                questions2.add(question2[i]);
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function saves the scores to the file
     */

    public void saveScores(){
        FileWriter output = null;
        try{
            File f = new File(filepath,Questions);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write(ans1.get(0)+"-"+ans1.get(1)+"-"+ans1.get(2)+"-"+ans1.get(3)+"-"+ans1.get(4)+"-"+ans1.get(5)+"-");
            writer.newLine();
            writer.write(ans2.get(0)+"-"+ans2.get(1)+"-"+ans2.get(2)+"-"+ans2.get(3)+"-"+ans2.get(4)+"-"+ans2.get(5)+"-");
            writer.newLine();
            writer.write(ans3.get(0)+"-"+ans3.get(1)+"-"+ans3.get(2)+"-"+ans3.get(3)+"-"+ans3.get(4)+"-"+ans3.get(5)+"-");
            writer.newLine();
            writer.write(ans4.get(0)+"-"+ans4.get(1)+"-"+ans4.get(2)+"-"+ans4.get(3)+"-"+ans4.get(4)+"-"+ans4.get(5)+"-");
            //writer.close();
            writer.newLine();
            writer.write(questions.get(0)+"-"+questions.get(1)+"-"+questions.get(2)+"-"+questions.get(3)+"-"+questions.get(4)+"-"+questions.get(5)+"-");
            //writer.close();
            writer.newLine();
            writer.write(questions2.get(0)+"-"+questions2.get(1)+"-"+questions2.get(2)+"-"+questions2.get(3)+"-"+questions2.get(4)+"-"+questions2.get(5)+"-");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function creates an empty file of the saved data if it doesn't exist
     */

    private void createSaveData(){
        FileWriter output = null;
        try{
            File f = new File(filepath,Questions);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            //Answer 1
            writer.write("a1-a1-a1-a1-a1-a1");
            writer.newLine();
            //Answer 2
            writer.write("a2-a2-a2-a2-a2-a2");
            writer.newLine();
            //Answer 3
            writer.write("a3-a3-a3-a3-a3-a3");
            writer.newLine();
            //Answer 4
            writer.write("a4-a4-a4-a4-a4-a4");
            writer.newLine();
            //Question
            writer.write("q1-q2-q3-q4-q5-q6");
            writer.newLine();
            writer.write("q1.1-q2.1-q3.1-q4.1-q5.1-q6.1");
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Theese are the getters for all the parameters
     */

    public ArrayList<String> getAns1Array(){
        return ans1;
    }
    public ArrayList<String> getAns2Array(){
        return ans2;
    }
    public ArrayList<String> getAns3Array(){
        return ans3;
    }
    public ArrayList<String> getAns4Array(){
        return ans4;
    }
    public ArrayList<String> getQuestionArray(){
        return questions;
    }
    public ArrayList<String> getQuestion2Array(){
        return questions2;
    }
}
