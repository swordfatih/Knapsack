package methodes;

import sacados.Methode;
import sacados.Objet;
import sacados.SacADos;

import java.util.ArrayList;

/**
 * Classe représentant un noeud de l'arbre
 */
class Noeud {
    private static Float borneInf = 0.f;
    private static Noeud solution = null;

    public static void initiliaser(SacADos sac)
    {
        solution = new Noeud(new SacADos(sac.getObjets(), sac.getPoidsMaximal()).resoudre());
        borneInf = Objet.getValeurTotale(solution.liste);
    }

    private final ArrayList<Objet> liste;

    /**
     * Constructeur par défaut
     * @param liste
     */
    public Noeud(ArrayList<Objet> liste) {
        this.liste = liste;
    }

    /**
     * Construire l'arbre de manière récursif
     * @param sac Le sac à dos
     * @param profondeur La profondeur où se trouve le noeud
     */
    public void construire(SacADos sac, int profondeur) {
        // 1- on met à jour la borne minimale s'il y a lieu
        float valeurTotale = Objet.getValeurTotale(liste);

        if(valeurTotale > borneInf) {
            borneInf = valeurTotale;
            solution = this;
        }

        // 2- conditions d'arrêt : profondeur dépassée ou borne supérieure inutile
        if(valeurTotale + Objet.getValeurTotale(sac.getObjets(), profondeur) <= borneInf || profondeur >= sac.getObjets().size())
            return;

        // 3- créations des noeuds fils
        ArrayList<Objet> nextListe = new ArrayList<>(liste);
        nextListe.add(sac.getObjets().get(profondeur));

        if(Objet.getPoidsTotal(nextListe) <= sac.getPoidsMaximal()) // Gauche
            new Noeud(nextListe).construire(sac, profondeur + 1);

        new Noeud(liste).construire(sac, profondeur + 1); // Droite
    }

    // Récuperer la solution
    public static ArrayList<Objet> getSolution(SacADos sac) {
        new Noeud(new ArrayList<>()).construire(sac, 0);
        return solution.liste;
    }
}

/**
 * Classe implémentant la méthode PSE
 */
public class PSE implements Methode {
    @Override
    public ArrayList<Objet> resoudre(SacADos sac) {
        // Initialiser la borne inférieure
        Noeud.initiliaser(sac);

        // Tri ordre décroissant avec la bibliothèque standard
        //sac.getObjets().sort((Objet first, Objet second) -> -1 * Float.compare(first.poids / first.valeur, second.poids / first.valeur));

        return Noeud.getSolution(sac);
    }
}