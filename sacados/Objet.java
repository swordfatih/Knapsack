package sacados;

import java.util.List;

/**
 * Classe représentant une structure objet
 */
public class Objet {
    public String nom;
    public float valeur;
    public float poids;

    /**
     * Constructeur par défaut
     * @param nom
     * @param poids
     * @param valeur
     */
    public Objet(String nom, float poids, float valeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.poids = poids;
    }

    public String toString() {
        return String.format("[NOM] %-20s [POIDS] %-7.2f [VALEUR] %-7.2f [QUOTIENT] %.2f", nom, poids, valeur, valeur / poids);
    }

    /**
     * Fonction faisant la somme des poids
     * @param objets Objets à traiter
     * @return Somme des poids
     */
    public static float getPoidsTotal(List<Objet> objets) {
        float somme = 0;

        for(Objet objet: objets)
            somme += objet.poids;

        return somme;
    }

    /**
     * Fonction faisant la somme des valeurs
     * @param objets Objets à traiter
     * @return Somme des valeurs
     */
    public static float getValeurTotale(List<Objet> objets) {
        float somme = 0.f;

        for(Objet objet: objets)
            somme += objet.valeur;

        return somme;
    }

    /**
     * Fonction faisant la somme des valeurs à partir d'une indice donnée
     * @param objets Objets à traiter
     * @return Somme des valeurs
     */
    public static float getValeurTotale(List<Objet> objets, int profondeur) {
        float somme = 0.f;

        for(int i = profondeur; i < objets.size(); ++i)
            somme += objets.get(i).valeur;

        return somme;
    }
}
