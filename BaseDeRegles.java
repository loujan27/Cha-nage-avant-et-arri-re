

package miniprojetic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseDeRegles {

public final static int ET = 111;
public final static int ALORS = 222;

public static ArrayList<ArrayList<Integer>> split(ArrayList<ArrayList<Integer>> listlist) {
    Scanner sc = null;
    File file = new File("regles.txt");
    String temp = null;
    String[] parse = null;
    listlist= new ArrayList<>();
    ArrayList<Integer> tempListe = null;
    try {
        sc = new Scanner(file);
    }
    catch (FileNotFoundException e) {
        
        e.printStackTrace();
        System.out.println("Le ficher de la base de règles est introuvable !!");
    }
    while (sc.hasNextLine()) {
        tempListe =new ArrayList<Integer>();
        temp = sc.nextLine();
        parse = temp.split(" ");
        for (int index = 0; index < parse.length; index++) {
            switch (parse[index]) {
                case "et":
                    tempListe.add(ET);
                    break;
                case "alors":
                    tempListe.add(ALORS);
                    break;
                default:
                    char caractere = parse[index].charAt(0);
                    int ascii = (int) caractere;
                    tempListe.add(ascii);
                    break;
            }
        }
     listlist.add(tempListe);
    }
    return listlist;
}

//Modéliser la base de règles comme suit: R(Xi): liste des prémisses => liste des actions. 
public static void afficher(ArrayList<ArrayList<Integer>> listlist) {
    for (int i = 0; i < listlist.size(); i++) {
        System.out.println();
        System.out.print("R"+(i+1)+":" );
        for (int j = 0; j < listlist.get(i).size(); j++) {
            int c = listlist.get(i).get(j);
            afficherChar(c);
        }
    }
}

    public static void afficherRegleByNum(ArrayList<ArrayList<Integer>> st, int num) {
        System.out.print(" La règle utilisée: ");
        for (int j = 0; j < st.get(num).size(); j++) {
            int c = st.get(num).get(j);
            afficherChar(c);
        }

    }

    public static void afficherChar(int c){
        switch (c) {
                case 111:
                    System.out.print("ET");
                    break;
                case 222:
                    System.out.print("--> ");
                    break;

                default:
                    char ch = (char) c;
                    System.out.print(" " + ch + " ");
            }
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}


