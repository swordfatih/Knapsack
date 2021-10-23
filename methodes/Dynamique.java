package methodes;

import sacados.Methode;
import sacados.Objet;
import sacados.SacADos;

import java.util.ArrayList;

/**
 * Classe implémentant la méthode dynamique
 */
public class Dynamique implements Methode {
    @Override
    public ArrayList<Objet> resoudre(SacADos sac) {
        int nb_ligne = sac.getObjets().size();
        int nb_colonne = poidsEntier(sac.getPoidsMaximal()) + 1;

        float[][] matrice = new float[nb_ligne][nb_colonne];

        // On initialise la première ligne
        for(int j = 0; j < nb_colonne; ++j) {
            if(poidsEntier(sac.getObjets().get(0).poids) > j) // Si la colonne est inférieure au poids, on met 0
                matrice[0][j] = 0;
            else // Sinon la valeur de l'objet
                matrice[0][j] = sac.getObjets().get(0).valeur;
        }

        // On construit le reste du tableau (compléxité : O(n²) )
        for(int i = 1; i < nb_ligne; ++i) {
            Objet objet = sac.getObjets().get(i);

            for (int j = 0; j < nb_colonne; ++j) {
                if (poidsEntier(objet.poids) > j) // Si la colonne est inférieure au poids, on garde la valeur du dessus (on ne peut pas ajouter l'objet)
                    matrice[i][j] = matrice[i - 1][j];
                else // Sinon, on met l'objet dans le sac et on prend la valeur la plus optimisée pour la place qu'il reste (ligne d'avant ; colonne - poids)
                    matrice[i][j] = Math.max(matrice[i - 1][j], matrice[i - 1][j - poidsEntier(objet.poids)] + objet.valeur);
            }
        }

        // On recherche la combinaison d'objet qui permet d'obtenir la meilleure valeur
        int j = poidsEntier(sac.getPoidsMaximal());
        int i = nb_ligne - 1;

        while(matrice[i][j] == matrice[i][j-1])
            j--; // On monte de ligne tant que c'est pareil

        ArrayList<Objet> resultat = new ArrayList<>();

        while(j > 0) {
            while (i > 0 && matrice[i][j] == matrice[i - 1][j])
                i--; // On monte de ligne tant que c'est pareil

            // Si c'est différent, on sait qu'on a mit l'objet i dans le sac pour avoir cette combinaison
            // Alors on l'ajoute à notre résultat et on décale de son poids vers la gauche pour avoir la
            // Meilleure combinaison sans lui

            j = j - poidsEntier(sac.getObjets().get(i).poids);

            if (j >= 0)
                resultat.add(sac.getObjets().get(i)); // On ajoute l'objet

            i--;
        }

        return resultat;
    }

    /**
     * Fonction convertissants les nombres flottantes en entier au centième près.
     * @param poids Le poids à convertir
     * @return Nombre entier
     */
    private int poidsEntier(float poids) {
        return (int) (poids * 100);
    }
}
