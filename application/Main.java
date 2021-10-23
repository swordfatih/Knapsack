package application;

import methodes.Dynamique;
import methodes.Gloutonne;
import methodes.PSE;
import sacados.Methode;
import sacados.Objet;
import sacados.SacADos;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Classe principale
 */
public class Main {
    private static String chemin;
    private static String methode;
    private static float poidsMaximal;

    /**
     * Point d'entrée du programme
     * @param args Arguments d'entrées
     */
    public static void main(String[] args) {
        // Si des arguments sont données
        if(args.length == 3) {
            chemin = args[0];
            methode = args[1];
            poidsMaximal = Integer.parseInt(args[2]);
        }
        // Sinon, on les demande par la console
        else {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Le chemin du fichier avec les objets: ");
            chemin = scanner.next();

            System.out.println("La méthode à utiliser pour résoudre (g/d/p): ");
            methode = scanner.next();

            System.out.println("Le poids maximal à ne pas dépasser: ");
            poidsMaximal = scanner.nextFloat();

            scanner.close();
        }

        // On créer le sac à dos et choisit la méthode de résolution
        SacADos sac = new SacADos(chemin, poidsMaximal);
        sac.setMethode(methodeFromString(methode));

        final long debut = System.nanoTime(); // On récupère le temps de début

        ArrayList<Objet> resultat = sac.resoudre(); // On résoud et récupère la liste

        final long fin = System.nanoTime(); // On récupère le temps de fin

        // Affichage
        System.out.println("-----------Objets-----------");

        for(Objet objet : resultat)
            System.out.println(objet);

        System.out.println("----------Resultats----------");

        System.out.println("Poids: " + Objet.getPoidsTotal(resultat));
        System.out.println("Valeur: " + Objet.getValeurTotale(resultat));
    
        System.out.println("----------Benchmark----------");
        System.out.println("Temps écoulé: " + TimeUnit.NANOSECONDS.toMillis(fin - debut) + "ms");
    }

    /**
     * Transformer une chaîne de caractère en méthode
     * @param s La chaîne
     * @return La méthode
     */
    private static Methode methodeFromString(String s) {
        if(s.startsWith("g"))
            return new Gloutonne();
        else if(s.startsWith("d"))
            return new Dynamique();
        else if(s.startsWith("p"))
            return new PSE();
        else
            return null;
    }
}
