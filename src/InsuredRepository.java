import java.util.ArrayList;

public class InsuredRepository {
    // ArrayList k uložení vytvořených pojištěnců
    ArrayList<Insured> insuredPeople = new ArrayList<>();

    public InsuredRepository() {}

    // metoda k uložení nově vytvořených lidí do ArrayListu pojištěných osob insuredPeople
    public void addPersonToRepository(Insured newPerson) {
        insuredPeople.add(newPerson);
    }

    // // Metoda k výpisu všech záznamu lidí uložených v Arraylistu insuredPeople
    public String listingOfInsuredPeople() {
        StringBuilder allListedPeople = new StringBuilder();
        for (Insured listing : insuredPeople) {
            allListedPeople.append(listing.toString());
        }
        return allListedPeople.toString();
    }
    // Metoda k výpisu konktrétního záznamu člověka uloženého v Arraylistu insuredPeople
    public ArrayList<Insured> searchInsuredPerson(String firstName, String lastName) {
        ArrayList<Insured> wantedPerson = new ArrayList<>();
        for (Insured foundPerson : insuredPeople) {
            if (foundPerson.getFirstName().equals(firstName) && foundPerson.getLastName().equals(lastName)) {
                wantedPerson.add(foundPerson);
            }
        }
        return wantedPerson;
    }
    // Metoda k odstranění záznamů lidí uloženách v Arraylistu insuredPeople
    public void deleteInsuredPerson(String firstName, String lastName) {
        ArrayList<Insured> removePerson = new ArrayList<>();
        for (Insured deletePerson : insuredPeople) {
            if ((deletePerson.getFirstName().equals(firstName) && deletePerson.getLastName().equals(lastName))) {
                removePerson.add(deletePerson);
            }
        }
        insuredPeople.removeAll(removePerson);
    }

    // Přepis zadaného prvního písmene jména na velké písmeno
    public String firstNameCapitalization(String firstName) {
        String firstNameCapitalized;
        char firstChar = Character.toUpperCase(firstName.charAt(0));
        String restOfString = firstName.substring(1);
        firstNameCapitalized = firstChar + restOfString;

        return firstNameCapitalized;
    }
    // Přepis zadaného prvního písmene příjmení na velké písmeno
    public String surnameCapitalization(String surname) {
        String surnameCapitalized;
        char firstChar = Character.toUpperCase(surname.charAt(0));
        String restOfString = surname.substring(1);
        surnameCapitalized = firstChar + restOfString;

        return surnameCapitalized;
    }
    // Metoda ke změně jména lidí uložených v ArrayListu
    public void editNameValue(String oldNameValue, String oldSurnameValue, String newValue) {
        for (Insured name: insuredPeople) {
            if ((name.getFirstName().equals(oldNameValue) && name.getLastName().equals(oldSurnameValue))) {
                name.setFirstName(newValue);
            }
        }
    }
    // // Metoda ke změně příjmení lidí uložených v ArrayListu
    public void editSurnameValue(String oldNameValue, String oldSurnameValue, String newValue) {
        for (Insured name: insuredPeople) {
            if ((name.getFirstName().equals(oldNameValue) && name.getLastName().equals(oldSurnameValue))) {
                name.setLastName(newValue);
            }
        }
    }
    // Metoda ke změně telefonního čísla lidí uložených v ArrayListu
    public void editTelephoneNumberValue(String NameValue, String SurnameValue, String newValue) {
        for (Insured name : insuredPeople) {
            if ((name.getFirstName().equals(NameValue) && name.getLastName().equals(SurnameValue))) {
                name.setTelephoneNumber(newValue);
            }
        }
    }
    // Metoda ke změně věku lidí uložených v ArrayListu
    public void editAgeValue(String oldNameValue, String oldSurnameValue, int newValue) {
        for (Insured name : insuredPeople) {
            if ((name.getFirstName().equals(oldNameValue) && name.getLastName().equals(oldSurnameValue))) {
                name.setAge(newValue);
            }
        }
    }
    // Zjištění, zda hledaný člověk je v seznamu pojištěnců
    public boolean isInsuredInList(String firstName, String surname) {
        for (Insured foundPerson : insuredPeople) {
            if (foundPerson.getFirstName().equals(firstName) && foundPerson.getLastName().equals(surname)) {
                return true;
            }
        }
        return false;
    }
}
