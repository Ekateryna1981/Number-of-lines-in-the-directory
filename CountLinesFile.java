/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Example;

/**
 *
 * @author Katerina Nosenko
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import java.util.Scanner;
  class Node{
     int size;
     String name;
     int level;
     Boolean isCatalog;
     Node(String name, int size, int level, boolean catalog) {
       this.name=name;
       this.size=size;
       this.level=level;
       this.isCatalog=catalog;
     }
     String getName(){
        return name;
     }
     int getSize(){
        return size;
     }
     void setSize(int s){
        size=s;
     }
     int getLevel(){
        return level;
     }
     boolean getIsCatalog(){
        return isCatalog;
     }
  }
public class CountLinesFile {
   
  static String deliteComment(String s){
    int end = 0;
    if(s == null)s  ="";
     StringBuffer sb = new StringBuffer(s);
     int start = sb.indexOf("/*");
     
     while(start >= 0&end>=0){
      start = sb.indexOf("/*");
      end = sb.indexOf("*/" , start);
      
     if(sb.lastIndexOf("*/" , start) > 0) start = sb.lastIndexOf("/*", end);
     if(start >= 0 && end >= 0)
     sb.delete(start, end+2);
      start = sb.indexOf("/*");
     }
     start = 0;
     while(start <= sb.lastIndexOf("//")){
         start = sb.indexOf("//");
         end = sb.indexOf("\n", start);
          if(start >= 0 && end >= 0)
         sb.delete(start, end);
     }
     String text = sb.toString();
     return text;   
  }
  
  static String deliteSpace(String s){
      if (s != null){
       return s.replaceAll("(?m)^[ \t]*\r?\n", "");   
      }
      else return null;
  }

  static int countLines(String s){
      if (s == null)s = "";
      String[] arrOfStr = s.split("\n"); 
      return arrOfStr.length;
  }
  
  static int numWithoutComment(String s){
      if (s == null)s = "";
     return countLines(deliteSpace(deliteComment(s)));

  }
 
  static String getFile(String FILE_NAME) {
    try{
      String s = "";
      Scanner in = new Scanner(new File(FILE_NAME));
      while(in.hasNext())
         s += in.nextLine() + "\n";
      in.close();
      return s;}
    catch(FileNotFoundException e){
            System.out.println("Файл не найден");
            return "";
    }
    }

  private static ArrayList<Node> hm=new ArrayList<Node>();
  
  public static ArrayList<Node> readFiles(File baseDirectory,int n){
     if(hm.size()==0){
        int size = 0;
        int level = 0;
        boolean isCatalog;
        String name = baseDirectory.getName();
        if(baseDirectory.isFile()){
            isCatalog = false;
            size = numWithoutComment(getFile(baseDirectory.toString()));
        }
        else{
            isCatalog = true;
        }
        hm.add(new Node(name, size, level, isCatalog));
     }
     if (baseDirectory.isDirectory()){
        for (File file : baseDirectory.listFiles()) {
            if(file.isFile()){
                int size = numWithoutComment(getFile(file.toString()));
                int level = n;
                String name = leftPad( n*2 ) + file.getName();
                boolean isCatalog = false;
                hm.add(new Node(name, size, level, isCatalog));
                
            }else {
                int size = 0;
                int level = n;
                String name = leftPad(n * 2) + file.getName();
                boolean isCatalog = true;
                hm.add(new Node(name, size, level, isCatalog));
                readFiles(file, n + 1);
            }
        }
    }
    return hm;
}

    static void printResult(ArrayList<Node> al){
        int maxLevel = 0;
       for(Node n : al){
           if(maxLevel < n.getLevel()) maxLevel = n.getLevel();
       }
       int size = 0;
       int topKnot =- 1;
       while(maxLevel >= 1){
        for(int i = 0; i < al.size();i ++){
             if(topKnot > -1 && size>0 && al.get(i).getLevel() < maxLevel){
                Node n = al.get(topKnot);
                n.setSize(size);
                al.set(topKnot, n);
                size = 0;
                topKnot =- 1; 
            }
           if(al.get(i).isCatalog && al.get(i).getLevel() == maxLevel-1 && topKnot == -1) {topKnot = i;}

           if(al.get(i).getLevel() == maxLevel){
                size += al.get(i).getSize();
            }
           if(topKnot >-1 && al.size() == i + 1){
                Node n = al.get(topKnot);
                n.setSize(size);
                al.set(topKnot, n);
                size = 0;
                topKnot = -1;
           } 
        }
        maxLevel --;
       }
         for(Node n : al){
           System.out.println(n.getName() + " " + n.getSize());
       }
        hm.clear();    
  }
  public static String leftPad(int n) {
      String sum = "";
      for(int i = 0; i < n; i++)
          sum += " ";
      return sum;
   }
  public static void main(String args[]) throws IOException{
    Scanner in = new Scanner(System.in);
    System.out.println("Input Directory");
    String phrase1 = in.nextLine();
    printResult(readFiles(new File(phrase1),1));
    in.close();
  }
}
