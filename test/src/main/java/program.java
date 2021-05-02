
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class program
{
    public final static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseScreen(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> historySlangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);

    public static void GetHistory()
    {
        try
     {
        File f=new File("./data/history.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            historySlangWord.add(line);
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    public static void GetData(){
     try
     {
        File f=new File("E:\\Liehthong\\java_project\\java_dictionary\\test\\data\\slang.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //1.Find Slang Word
    public static void FindSlangWord()
    {
        clearScreen();
        System.out.print("What word u want to find: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        List<String> test=m.get(check);
        historySlangWord.add(check);
        System.out.println(test);
        pauseScreen();
        Menu();
    }

    //2.Find Definition
    public static void FindDefinition()
    {
        clearScreen();
        System.out.println("What definition u want to find: ");
        String check=word.nextLine();
        historySlangWord.add(check);
        List<String> answer=new ArrayList();
        for (String i: m.keySet())
        {
            if (m.get(i).contains(check))
            {
                answer.add(i);
            }

        }
        System.out.println(answer);
        pauseScreen();
        Menu();
    }

    //3.History Searching
    static void ShowHistorySlangWord()
    {
        clearScreen();
        System.out.println("Your history search is: ");
        for (String temp: historySlangWord)
        {
            System.out.println(temp);
        }
        pauseScreen();
        Menu();
    }

    //4.Add Slang Word
    public static void CreateSlangWord()
    {
        clearScreen();
        System.out.println("What is your new Slang Word: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        System.out.println("What is the definition: ");
        String check1=word.nextLine();
        List<String> t=new ArrayList();
        t.add(check1);
        if (m.containsKey(check))
        {
            System.out.println("Do u want to overwrite: (Y/N) ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) m.put(check,t);
            else
            {
                List<String> i=m.get(check);
                for (String j:i)
                {
                    t.add(j);
                }
                m.put(check,t);
            }
        }
        else
        {
            m.put(check,t);
            System.out.println("Add New Slang Word Successfully");
        }
        Menu();
    }


    //5.Edit SlangWord
    static void EditSlangWord(){
        clearScreen();
        System.out.print("What slangword u want to edit: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        if (!m.containsKey(check))
        {
            System.out.println("This slangword dont't exist");
            pauseScreen();
            Menu();
        }
        clearScreen();
        System.out.println("Here is the definition: " );

        List<String> showCase=m.get(check);
        List<String> rshowCase=new ArrayList();
        for (String i:showCase)
        {
            rshowCase.add(i);
        }
        int count=1;
        for (String i: showCase)
        {
            System.out.println(count+ "." + i);
            count++;
        }
        System.out.println("What word u want to change: ");
        int index=word.nextInt();
        clearScreen();

        System.out.println("What do u want: ");
        System.out.println("1. Replace Definition ");
        System.out.println("2. Delete Definition ");
        System.out.println("3. Add Definition ");
        System.out.println("YOUR CHOICE:");
        int choice=word.nextInt();
        String pass=word.nextLine();
        if (choice==1) 
        {
            rshowCase.remove(index-1);
            System.out.print("What is the new definition : ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        else if (choice==2)
        {
            if (rshowCase.size()==1) 
            {
                System.out.println("You can't delete this ");
                pauseScreen();
                Menu();
            }
            rshowCase.remove(index-1);
            m.put(check,rshowCase);
        }
        else if (choice==3)
        {
            System.out.print("What is the new definition : ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        Menu();
    }

    //6.Remove Slang Word
    static void RemoveSlangWord()
    {
        clearScreen();
        System.out.println("What slangword u want to remove: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("Are u sure u want to remove it: (Y/N) ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) m.remove(check);
        }
        Menu();
    }

    //7.Reset List
    public static void ResetSlangDictionary()
    {
        clearScreen();
        m.clear();
        try
     {
        File f=new File("./data/default.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
        System.out.println("Reset List To Default !!!");
        pauseScreen();
        Menu();
    }

    //8.Random Slang Word
    public static String RandomSlangWord(){
        clearScreen();
        int count=0;
        Random rd=new Random();
        int magicNumber=rd.nextInt(m.size());
        String ans="";
        for (String i: m.keySet())
        {
            if (count==magicNumber)
            {
                ans=i;
                break;
            }else count++;
        }
        return ans;
    }

    //9.Minigame
    public static void GuessGameA()
    {
        clearScreen();
        Random rand = new Random();
        List<String> poll=new ArrayList();

        String word1=RandomSlangWord();
        String qword=word1;
        List<String> w1=m.get(word1);
        word1=w1.get(rand.nextInt(w1.size()));
        String win=word1;
        poll.add(word1);

        String word2=RandomSlangWord();
        List<String> w2=m.get(word2);
        word2=w2.get(rand.nextInt(w2.size()));
        poll.add(word2);

        String word3=RandomSlangWord();
        List<String> w3=m.get(word3);
        word3=w3.get(rand.nextInt(w3.size()));
        poll.add(word3);

        String word4=RandomSlangWord();
        List<String> w4=m.get(word4);
        word4=w4.get(rand.nextInt(w4.size()));
        poll.add(word4);


        System.out.println("Question: What is the Definition for " + qword);

        word1=poll.get(rand.nextInt(poll.size()));
        poll.remove(word1);
        System.out.println("A.  " + word1);
        word2=poll.get(rand.nextInt(poll.size()));
        poll.remove(word2);
        System.out.println("B.  " + word2);
        word3=poll.get(rand.nextInt(poll.size()));
        poll.remove(word3);
        System.out.println("C.  " + word3);
        word4=poll.get(rand.nextInt(poll.size()));
        poll.remove(word4);
        System.out.println("D.  " + word4);

        System.out.println("Your Answer is: ");
        String choice=word.nextLine();

        if ( (choice.equals("A") || choice.equals("a")) && word1==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("B") || choice.equals("b")) && word2==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("C") || choice.equals("c")) && word3==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("D") || choice.equals("d")) && word4==win) System.out.println("Congratulations , Your Answer is correct");
        else System.out.println("Sorry , Your Answer is incorrect . The Answer is " + win);
        pauseScreen();
        Menu();
    }

    //10.Minigame
    public static void GuessGameB()
    {
        clearScreen();
        Random rand = new Random();
        List<String> poll=new ArrayList();
        String word1=RandomSlangWord();
        poll.add(word1);
        String word2=RandomSlangWord();
        poll.add(word2);
        String word3=RandomSlangWord();
        poll.add(word3);
        String word4=RandomSlangWord();
        poll.add(word4);
        List<String> qword=m.get(word1);
        String win=word1;
        System.out.println("Question: What is the Slang Word for " + qword.get(rand.nextInt(qword.size())));
        word1=poll.get(rand.nextInt(poll.size()));
        poll.remove(word1);
        System.out.println("A.  " + word1);
        word2=poll.get(rand.nextInt(poll.size()));
        poll.remove(word2);
        System.out.println("B.  " + word2);
        word3=poll.get(rand.nextInt(poll.size()));
        poll.remove(word3);
        System.out.println("C.  " + word3);
        word4=poll.get(rand.nextInt(poll.size()));
        poll.remove(word4);
        System.out.println("D.  " + word4);
        System.out.println("Your Answer is: ");
        String choice=word.nextLine();
        if ( (choice.equals("A") || choice.equals("a")) && word1==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("B") || choice.equals("b")) && word2==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("C") || choice.equals("c")) && word3==win) System.out.println("Congratulations , Your Answer is correct");
        else if ((choice.equals("D") || choice.equals("d")) && word4==win) System.out.println("Congratulations , Your Answer is correct");
        else System.out.println("Sorry , Your Answer is incorrect . The Answer is " + win);
        pauseScreen();
        Menu();
    }

    //Update History
    public static void updateHistory(){
        try {
            File f = new File("./data/history.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String temp : historySlangWord) {
                fw.write(temp + "\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    //Update File
    public static void updateFile()
    {
        try {
            File f = new File("./data/slang.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String i: m.keySet())
            {
                fw.write(i +"`");
                List<String> temp=m.get(i);
                for (int t=0;t<temp.size();t++)
                {
                    fw.write(temp.get(t));
                    if (t+1<temp.size()) fw.write("|");
                }
                fw.write("\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }


    //Menu
    public static void Menu(){
        clearScreen();
        System.out.println("1. Search by SlangWord ");
        System.out.println("2. Search by Definition ");
        System.out.println("3. Show history ");
        System.out.println("4. Add Slangword ");
        System.out.println("5. Edit Slangword ");
        System.out.println("6. Delete Slangword ");
        System.out.println("7. Reset to default ");
        System.out.println("8. Random Slangword ");
        System.out.println("9. Minigame find Definition ");
        System.out.println("10. Minigame find Slangword ");
        System.out.println("11. Clear History");
        System.out.println("12. Exit ");
        System.out.println("YOUR CHOICE:  ");
        int choice=word.nextInt();
        String pass=word.nextLine();
        if (choice==1) FindSlangWord();
        else if (choice==2) FindDefinition();
        else if (choice==3) ShowHistorySlangWord();
        else if (choice==4) CreateSlangWord();
        else if (choice==5) EditSlangWord();
        else if (choice==6) RemoveSlangWord();
        else if (choice==7) ResetSlangDictionary();
        else if (choice==8)
        {
            String luckyword=RandomSlangWord();
            System.out.print("Your Random Slang Word Is : ");
            System.out.println(luckyword);
            System.out.print("And The Definition Is:   ");
            List<String> t=m.get(luckyword);
            System.out.println(t);
            pauseScreen();
            Menu();
        }
        else if (choice==9) GuessGameA();
        else if (choice==10) GuessGameB();
        else if (choice==11)
        {
            clearScreen();
            System.out.println("Clearing History !!!");
            historySlangWord.clear();
            pauseScreen();
            Menu();
        }
        else
        {
            clearScreen();
            updateFile();
            updateHistory();
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        GetData();
        GetHistory();
        //System.out.println(m.size());
        Menu();
    }
}
