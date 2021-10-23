package methodes;

import sacados.Methode;
import sacados.Objet;
import sacados.SacADos;

import java.util.ArrayList;

/**
 * Classe implémentant la méthode gloutonne
 */
public class Gloutonne implements Methode {
    /**
     * Fonction permettant de résoudre
     * @param sac Le sac à dos
     * @return La solution
     */
    @Override
    public ArrayList<Objet> resoudre(SacADos sac) {
        // On trie les objets du sac
        triRapide(sac.getObjets(), 0, sac.getObjets().size() - 1);

        // On met dans la liste de résultat les premiers objets tant que le poids ne dépasse pas la condition
        ArrayList<Objet> resultat = new ArrayList<>();

        for(Objet objet: sac.getObjets())
            if(Objet.getPoidsTotal(resultat) + objet.poids <= sac.getPoidsMaximal())
                resultat.add(objet);

        return resultat;
    }

    /**
     * Fonction de tri rapide
     * @param objets Les objets à trier
     * @param inf La borne inférieure
     * @param sup La borne supérieure
     */
    private void triRapide(ArrayList<Objet> objets, int inf, int sup)
    {
        // Condition d'arrêt
        if (inf < sup) {
            // On répartit les objets autour du pivot et on récupère le nouvel indice du pivot
            int pivot = repartir(objets, inf, sup);

            triRapide(objets, inf, pivot - 1); // Avant le pivot
            triRapide(objets, pivot + 1, sup); // Après le pivot
        }
    }

    /**
     * Fonction de répartition
     * @param objets Les objets à trier
     * @param inf La borne inférieure
     * @param sup La borne supérieure
     * @return La nouvelle position du pivot
     */
    private int repartir(ArrayList<Objet> objets, int inf, int sup) {
        float pivot = objets.get(sup).valeur / objets.get(sup).poids; // Le pivot est le dernier élement

        int curseur = inf - 1; // Position initiale du curseur à borne inférieure - 1

        for (int j = inf; j < sup; j++) // Boucle traversant le tableau de la borne inférieure à la borne supérieure
        {
            Objet premier = objets.get(j);
            if (premier.valeur / premier.poids > pivot) // Si l'objet est plus grand que le pivot
            {
                curseur++; // On avance le curseur

                // On swap avec le curseur (pour rapatrier à gauche et commencer à répartir)
                Objet temp = objets.get(curseur);
                objets.set(curseur, premier);
                objets.set(j, temp);
            }
        }

        // On rapatrie le pivot après le curseur
        Objet temp = objets.get(curseur + 1);
        objets.set(curseur + 1, objets.get(sup));
        objets.set(sup, temp);

        return (curseur + 1); // La nouvelle position du pivot
    }
}
