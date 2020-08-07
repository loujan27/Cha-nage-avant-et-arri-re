package miniprojetic;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {



        boolean continu = true;
        
        while (continu) {

            int choix = -1;
            Scanner scch = new Scanner(System.in);
            
            BaseDeRegles.clearScreen();
            
            
            System.out.println("Cliquer '1' pour le chainage avant ");
            
            System.out.println("Cliquer '2' pour le chainage arrière");
            
            System.out.println("ou d'autre pour quitter");
            
            
            choix = scch.nextInt();
            
            switch (choix) {
//**************************************************CHAINAGE AVANT***************************************************************************************/
                case 1:
                    BaseDeRegles.clearScreen();
                    System.out.println("CHAINAGE AVANT");
                    System.out.println("Entrez le(s) fait(s) de la base de faits,c'est à dire ceux qui sont vrais: ");
                    System.out.println("Appuyer sur 'n' lorsque vous avez terminé");
                    ArrayList<ArrayList<Integer>> bb = new ArrayList<>();
                    ArrayList<Integer> BDF = new ArrayList<>();
                    BDF = BaseDeFaits.lireFaitsInitiaux(BDF);
                    bb = BaseDeRegles.split(bb);
                    System.out.println("Les règles 'Les connaissances' qui seront utilisées (lues à partir du fichier):");
                    BaseDeRegles.afficher(bb);
                    System.out.println();
                    System.out.println("RESULTATS 'Buts': ie,(tous les faits que le système expert a pu inférer)");
                    System.out.println();
                    BDF = MoteurInference.chainageAvant(bb, BDF);
                    System.out.println("*****************************Fin CHAINAGE AVANT************************************************************");
                     
                   break;

//**************************************CHAINAGE ARRIERE***************************************************************************************************
                case 2:
                    BaseDeRegles.clearScreen();  
                    System.out.println("CHAINAGE Arrière");
                    System.out.println("Entrez le fait à démontrer: ");
                    Scanner sc = new Scanner(System.in);
                    String fd = sc.next().toUpperCase();
                        
                    MoteurInference.Buts.add((fd)); 
                    
                    System.out.println("Entrez le(s) fait(s) de la base de faits,c'est à dire ceux qui sont vrais:");         
                    System.out.println("Appuyer sur n lorsque vous avez terminé");
                    ArrayList<String> bdf;
                    bdf = BaseDeFaits.lireString();
                    bdf = MoteurInference.avantChainageArriere(bdf);
                    String cha = "";
                    System.out.println();
                    
                    System.out.println("RESULTATS DU CHAINAGE ARRIERE:");
                    
                    if (MoteurInference.chainageArriere(bdf)) {
                        for (int i = MoteurInference.regles.size() - 1; i >= 0; i--) {
                            String[] phrase = MoteurInference.regles.get(i).split(" ");
                            if (phrase.length == 1) {
                                cha += phrase[0] + " est vrai\n";
                            } else if (phrase.length == 2) {
                                cha += phrase[0] + " <-- " + phrase[1] + "\n";
                            } else if (phrase.length >= 3) {
                                cha += phrase[0] + " <-- " + phrase[1] + " , " + phrase[2]  + "\n";
                               }
                        }
                        cha += "Donc, le but à prouver " + fd + " est VRAI\n";
                    } 
                    
                    else {
                        cha += fd + " NE PEUT PAS ETRE PROUVU DANS CETTE BASE DE CONNAISSANCE\n";
                        cha += "Donc, le but à prouver " + fd + " est FAUX\n";
                    }
                    System.out.println();
                    System.out.println("RESULTAT:");
                    System.out.println(cha);
                    System.out.println();
                    System.out.println("*************************Fin CHAINAGE Arrière*******************************************************************");
                    break;

                    default:
                        continu = false;
                        BaseDeRegles.clearScreen();
            }
        }
        
        
        BaseDeRegles.clearScreen();  BaseDeRegles.clearScreen();  BaseDeRegles.clearScreen();
       
        System.out.flush();
        
        
    }
}
