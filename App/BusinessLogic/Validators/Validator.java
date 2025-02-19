package BusinessLogic.Validators;

/**
 * Interfața Validator definește validarea unui tip de date generice.
 * Această interfață trebuie implementată de clasele care doresc să ofere funcționalitate de validare.
 * @param <T> Tipul de date care va fi validat.
 */
public interface Validator<T> {

    /**
     * Metodă pentru validarea unui obiect de tipul specificat.
     * @param t Obiectul care trebuie validat.
     */
    public void validate(T t);
}
