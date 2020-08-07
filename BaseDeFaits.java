/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojetic;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseDeFaits {

    public static ArrayList<Integer> lireFaitsInitiaux(ArrayList<Integer> BDF) {
        boolean yes = true;
        char c,d;
        Scanner sc = new Scanner(System.in);
        while (yes) {
            c= sc.next().charAt(0);
            d= Character.toUpperCase(c);

            if (d=='N')
                yes=false;
            else
                BDF.add((int)d);
        }
        return BDF;
    }

 
    public static ArrayList<String> lireString(){
        ArrayList<String> ret = new ArrayList<>();

        boolean yes = true; String c,d ;
        Scanner sc = new Scanner(System.in);
        while (yes){
            c= sc.next().toUpperCase();
            if (c.contentEquals("N"))
                yes=false;
            else
                ret.add(c);
        }
        return ret;

    }
 public static void afficherBaseDeFaits(ArrayList<Integer> BDF, int m) {
        switch (m){
            default:
                System.out.print(m + "ième Itération: BaseDeFaits= {");
                break;
            case 1:
                System.out.print(m + "ière Itération: BaseDeFaits= {");
                break;
            case 0:
                System.out.print("\t\t\t\t "+"BaseDeFaitsInitiale= {");
                break;
        }
        for (int i = 0; i < BDF.size(); i++) {
            int ascii = BDF.get(i);
            char c = (char) ascii;
            if (i != BDF.size() - 1)
                System.out.print(c + ",");
            else
                System.out.print(c);
        }
        System.out.print("}");
    }




}











