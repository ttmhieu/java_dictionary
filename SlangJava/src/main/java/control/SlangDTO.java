/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ACER
 */


public class SlangDTO {
    
    
    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> historySlangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);
    
    public void GetData(String path)
    {
        try
        {
            File f=new File(path);
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
        System.out.print("What word u want to find: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        List<String> test=m.get(check);
        historySlangWord.add(check);
        System.out.println(test);
    }
        //2.Find Definition
    public static void FindDefinition()
    {
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
    }
    
    //3.History Searching
    static void ShowHistorySlangWord()
    {
        System.out.println("Your history search is: ");
        for (String temp: historySlangWord)
        {
            System.out.println(temp);
        }
    }

    //4.Add Slang Word
    public static void CreateSlangWord()
    {
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
    }


    //5.Edit SlangWord
    static void EditSlangWord(){
        System.out.print("What slangword u want to edit: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        if (!m.containsKey(check))
        {
            System.out.println("This slangword dont't exist");
        }
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
    }

    //6.Remove Slang Word
    static void RemoveSlangWord()
    {
        System.out.println("What slangword u want to remove: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("Are u sure u want to remove it: (Y/N) ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) m.remove(check);
        }
    }

    //7.Reset List
    public static void ResetSlangDictionary()
    {
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
    }

    //8.Random Slang Word
    public static String RandomSlangWord(){
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
}
