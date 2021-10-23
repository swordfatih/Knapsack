package sacados;

import methodes.Gloutonne;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe représentant un sac à dos avec une
 * méthode de résolution pour le problème du sac à dos
 */
public class SacADos {
    private ArrayList<Objet> objets; // Les objets dans le sac
    private Methode methode; // La méthode de résolution
    private float poidsMaximal; // Le poids maximal du sac

    /**
     * Constructeur par défaut
     * Méthode de résolution : gloutonne
     */
    private SacADos() {
        objets = new ArrayList<Objet>();
        methode = new Gloutonne();
        poidsMaximal = 0;
    }

    /**
     * Constructeur à partir d'un fichier
     * @param chemin Chemin du fichier contenant les objets
     * @param poidsMaximal Le poids maximal
     */
    public SacADos(String chemin, float poidsMaximal) {
        this();

        this.poidsMaximal = poidsMaximal;
        remplirSac(chemin);
    }

    /**
     * Constructeur à partir d'objets
     * @param objets Objets à mettre dans le sac
     * @param poidsMaximal Le poids maximal
     */
    public SacADos(ArrayList<Objet> objets, float poidsMaximal) {
        this();

        this.poidsMaximal = poidsMaximal;
        this.objets = objets;
    }

    /**
     * Résoudre avec la méthode de résolution choisie
     */
    public ArrayList<Objet> resoudre() {
        return methode.resoudre(this);
    }

    /**
     * Définir la méthode
     * @param methode
     */
    public void setMethode(Methode methode) {
        this.methode = methode;
    }

    /**
     * Récupérer les objets
     * @return Les objets dans le sac
     */
    public ArrayList<Objet> getObjets() {
        return objets;
    }

    /**
     * Récuperer le poids maximal
     * @return Le poids maximal
     */
    public float getPoidsMaximal() {
        return poidsMaximal;
    }

    /**
     * Remplir le sac avec un chemin donné
     * @param chemin
     */
    private void remplirSac(String chemin) {
        try {
            Scanner scanner = new Scanner(new File(chemin));

            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(";");
                objets.add(new Objet(words[0].trim(), Float.parseFloat(words[1].trim()), Float.parseFloat(words[2].trim())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(Objet objet : objets) {
            builder.append(objet + "\n");
        }

        return builder.toString();
    }
}
