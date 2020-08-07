package miniprojetic;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoteurInference {

    public static ArrayList<String> regles=new ArrayList<>();
    public static ArrayList<String> Buts=new ArrayList<>();
    public static ArrayList<String> dejavisite = new ArrayList<>();

//********************************************** Chainage Avant******************************************************************************************
    public static ArrayList<Integer> chainageAvant(ArrayList<ArrayList<Integer>> struct, ArrayList<Integer> baseDeFaits) {
        boolean continu = true;
        int iteration = 0;
        BaseDeFaits.afficherBaseDeFaits(baseDeFaits, iteration);
        System.out.println();
        while (!struct.isEmpty() && (continu)) {
            int cpt = 0;
            continu = false;
            boolean maj = false;
            boolean quitter = false;
            while ((cpt < struct.size()) && (!maj)) {
                int idx = 0;
                while ((idx < struct.get(cpt).size()) && (struct.get(cpt).get(idx) != 222) && (!quitter)) {
                    if ((struct.get(cpt).get(idx) != 111) && (!baseDeFaits.contains(struct.get(cpt).get(idx)))) {
                        quitter = true;
                    }
                    idx++;
                }
                if (!quitter) {
                    baseDeFaits.add(struct.get(cpt).get(idx + 1));
                    iteration++;
                    BaseDeFaits.afficherBaseDeFaits(baseDeFaits, iteration);
                    BaseDeRegles.afficherRegleByNum(struct, cpt);
                    System.out.println();
                    struct.remove(struct.get(cpt));
                    maj = true;
                    continu = true;
                }
                cpt++;
                quitter = false;
            }
        }
        return baseDeFaits;
    }

// pretraitement sur les règles

    public static String  preTraitementRegle(String x,String y) {

        String[] parse = x.split(" ");
        for (int i = 0; i < parse.length; i++) {
            if ((parse[i].contentEquals("et")) || (parse[i].contentEquals("alors"))) {
                
                parse[i] = " ";
            } }
        y= parse[parse.length-1];
        for (int j=parse.length-2;j>=0;j--){
            y+= parse[j];
        }
        return y;
    }

// Avant chainage Arrière

    public static ArrayList<String> avantChainageArriere(ArrayList<String>regles){
       
        File file = new File("regles.txt");
        Scanner sc=null;
        String res= null;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Le ficher de la base de règles est introuvable !!");
        }
        while (sc.hasNextLine()){

            res =preTraitementRegle(sc.nextLine(),res);
            regles.add(res);
        }

        return  regles;
    }

//C'est pour vérifier si une règle contient comme conclusion le but à démontrer

    private static boolean contenirBut(String c, String d) {
        String hh = c.split(" ")[0];
        return hh.equals(d);
    }
    
//****************************************************** CHAINEGE Arrière********************************************************************************


    public static boolean chainageArriere(ArrayList<String> baseRegles) {
        ArrayList<String> temp = new ArrayList<>();
        if (Buts.isEmpty()) {
            return true;
        }
        
        System.out.println();
        System.out.println("Buts à evaluer: " + Buts);
        String sousBut = Buts.remove(Buts.size() - 1);
        System.out.println("En cours d'évaluation " + sousBut + "...");
        dejavisite.add(sousBut);
        for (int i = 0; i < baseRegles.size(); i++) {
            String clause = baseRegles.get(i);
            if (contenirBut(clause, sousBut)) {
                if (!regles.contains(clause)) {
                    regles.add(clause);
                }

                String[] proposition = clause.split(" ");
                // ajout
                if (!clause.contentEquals(sousBut))
                    
                    
                System.out.println(sousBut + " <-- trouvé comme conclusion de ("+clause.substring(0, 1)+"<--"+clause.substring(2)+")");
                else
                System.out.println(sousBut + " <-- se trouve dans la base de faits");
                for (int j = 1; j < proposition.length; j++) {
                    if (!temp.contains(proposition[j])) {
                        temp.add(proposition[j]);
                    }
                }
                System.out.println("Ensemble des règles appliquées: " + regles);
            }
            
            if (temp.size() != 0) {
                for (int k = 0; k < temp.size(); k++) {
                    if (!Buts.contains(temp.get(k)) &&
                            !regles.contains(temp.get(k)) &&
                            !dejavisite.contains(temp.get(k))) {
                        Buts.add(temp.get(k));
                    }
                }
                if (chainageArriere(baseRegles)) {
                    return true;
                }
            }
        }
        return false;
    }

}


