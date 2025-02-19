package BusinessLogic.Validators;

import Model.Product;

/**
 * Clasa CantitateDisponibilaValidator implementează interfața Validator pentru validarea cantității disponibile a unui produs.
 * Această clasă verifică dacă cantitatea disponibilă a unui produs este mai mare sau egală cu zero.
 */
public class CantitateDisponibilaValidator implements Validator<Product> {

    /**
     * Verifică dacă cantitatea disponibilă a produsului dat este mai mare sau egală cu zero.
     * @param t Produsul de validat.
     * @throws IllegalArgumentException Excepție aruncată în cazul în care cantitatea disponibilă este negativă.
     */
    public void validate(Product t) {

        if (t.getCantitate_disponibila() < 0) {
            throw new IllegalArgumentException("Cantitate negativa !");
        }

    }

}
