package sacados;

import java.util.ArrayList;

/**
 * Interface représentant une méthode capable de résoudre le problème du sac à dos
 */
public interface Methode {
    ArrayList<Objet> resoudre(SacADos sac);
}
