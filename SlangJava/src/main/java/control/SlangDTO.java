/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */


public class SlangDTO {
    
    
    public HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public List<String> historySlangWord=new ArrayList();
    public SlangDTO()
    {
        GetData();
    }
    public void GetHistory()
    {
        try
        {
           File f=new File("history.txt");
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
    public void GetData()
    {
        try
        {
            File f=new File("slang.txt");
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
    public List<String> FindSlangWord(String check)
    {
        check=check.toUpperCase();
        List<String> test=m.get(check);
        historySlangWord.add(check);
        return test;
    }
        //2.Find Definition
    public List<String> FindDefinition(String check)
    {
        historySlangWord.add(check);
        List<String> answer=new ArrayList();
        for (String i: m.keySet())
        {
            if (m.get(i).contains(check))
            {
                answer.add(i);
            }

        }
        return answer;
    }
    
    //3.History Searching
    public void ShowHistorySlangWord()
    {
        GetHistory();
        for (String temp: historySlangWord)
        {
            System.out.println(temp);
        }
    }

    //4.Add Slang Word
    public void CreateSlangWord(String check, String check1)
    {
        check=check.toUpperCase();
        List<String> t=new ArrayList();
        t.add(check1);
        if (m.containsKey(check))
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Do you want to overwrite");
            if (confirm==0) m.put(check,t);
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
        }
    }


    //5.Edit SlangWord
    public void EditSlangWord(String check, String check1)
    {
        check=check.toUpperCase();
        List<String> t=new ArrayList();
        t.add(check1);
        if (m.containsKey(check))
        {
            int confirm = JOptionPane.showConfirmDialog(null,"Do you want to overwrite");
            if (confirm==0) m.put(check,t);
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
        }
    }

    //6.Remove Slang Word
    public void RemoveSlangWord(String check)
    {
        if (m.containsKey(check))
        {
            int confirm=JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (confirm==0) m.remove(check);
        }
    }

    //7.Reset List
    public void ResetSlangDictionary()
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
    public String RandomSlangWord(){
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
    public void updateHistory(){
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
    public void updateFile()
    {
        try {
            File f = new File("slang.txt");
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
            System.out.println(ex.toString());
        }
    }
}
