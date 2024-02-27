import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in, "Windows-1250");

    InsuredRepository repository = new InsuredRepository();

    MySQL database = new MySQL();

    public UserInterface() {
    }

    public void welcome() {
        System.out.println("-----------------------------");
        System.out.println("Evidence pojištěných");
        System.out.println("-----------------------------");
        System.out.println();
    }
    // metoda k výpisu možností tohoto programu
    public void choiceOfAction() {
        System.out.println("Vyberte si prosím požadovanou akci:");
        String[] action = {"Přidat nového pojištěného", "Vypsat všechny pojištěné", "Vyhledat pojištěného",
                "Smazat pojištěného", "Úprava uloženého pojištěnce", "Přidat pojištěnce do MySQL databáze" ,
                "Odstranit pojištěnce z MySQL databáze", "Konec"};
        for (int i = 0; i < action.length; i++) {
            System.out.println("[" + (i + 1) + "]: " + action[i]);
        }
    }

    public int parsUsersChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Neplatný vstup. Zadejte prosím celé číslo.");
        }
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void showConfirmation() {
        System.out.println("Data byla uložena. Pokračujte stiskem libovolné klávesy...");
        scanner.nextLine();
    }

    public void showContinue() {
        System.out.println("Pokračujte stiskem libovolné klávesy...");
        scanner.nextLine();
        System.out.println();
    }
    // Metoda k získání jména a příjmení od uživatele k vyhledání v ArrayListu insuredPeople
    public void searchForInsuredPerson() {
        System.out.println();
        String firstNameSearch;
        String surnameSearch;
        while (true) {
            try {
                System.out.println("Zadejte jméno hledaného pojištěnce:");
                firstNameSearch = scanner.nextLine().trim();
                if (firstNameSearch.isEmpty()) {
                    throw new Exception("Jméno nesmí být prázdné.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("Zadejte příjmení hledaného pojištěnce:");
                surnameSearch = scanner.nextLine().trim();
                if (surnameSearch.isEmpty()) {
                    throw new Exception("Příjmení nesmí být prázdné.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println();
        if (repository.searchInsuredPerson(repository.firstNameCapitalization(firstNameSearch), repository.surnameCapitalization(surnameSearch)).isEmpty()) {
            System.out.println("Pojištěnec " + repository.firstNameCapitalization(firstNameSearch) + " " + repository.surnameCapitalization(surnameSearch) + " nebyl v databázi nalezen.");
        }
        ArrayList<Insured> foundInsuredPerson = repository.searchInsuredPerson(repository.firstNameCapitalization(firstNameSearch), repository.surnameCapitalization(surnameSearch));
        for (Insured insured: foundInsuredPerson) {
            System.out.println(insured.toString());
        }
    }
    // Metoda k vytvoření nového pojištěnce v ArrayListu insuredPeople
    public void createInsured() {
        String firstNameCreation = getInput("Zadejte jméno nového pojištěného:", "Jméno");
        String surnameCreation = getInput("Zadejte příjmení nového pojištěného:", "Příjmení");
        String telephoneNumberCreation = getInput("Zadejte telefonní číslo nového pojištěného:", "Telefonní číslo");
        int ageCreation = getIntegerInput();

        System.out.println();
        repository.addPersonToRepository(new Insured(repository.firstNameCapitalization(firstNameCreation), repository.surnameCapitalization(surnameCreation), telephoneNumberCreation, ageCreation));
    }
    // Metoda ke zjednodušení inputu uživatele a ověření výjimek pro větší přehlednost kódu u jména, příjmení a telefonního čísla
    private String getInput(String prompt, String fieldName) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                if (input.isBlank()) {
                    throw new Exception(fieldName + " nesmí být prázdné.");
                } else if (fieldName.contains("Telefonní číslo") && !input.matches("^\\d+$")) {
                    throw new Exception(fieldName + " musí obsahovat pouze číslice.");
                } else if (!fieldName.contains("Telefonní číslo") && input.matches(".*\\d.*")) {
                    throw new Exception(fieldName + " nesmí obsahovat číslice.");
                } else if (!input.matches("^\\p{L}+$") && !fieldName.contains("Telefonní číslo")) {
                    throw new Exception(fieldName + " může obsahovat pouze písmena bez speciálních znaků.");
                } else {
                    return input;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // Metoda ke zjednodušení inputu uživatele a ověření výjimek pro větší přehlednost kódu u věku
    private int getIntegerInput() {
        while (true) {
            try {
                System.out.println("Zadejte věk nového pojištěného:");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("Pole pro věk nesmí být prázdné.");
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Pole pro věk musí obsahovat pouze číslice.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // Metoda k vypsání všech pojištěnců v ArrayListu insuredPeople
    public void listInsuredPeople() {
        System.out.println(repository.listingOfInsuredPeople());
        if (repository.listingOfInsuredPeople().isEmpty()) {
            System.out.println("Nejsou uloženi žádní pojištěnci.");
            System.out.println();
        }
    }
    // Metoda k odstranění určitého člověka v ArrayListu insuredPeople pomocí jeho jména a příjmení
    public void deleteInsuredPerson() {
        System.out.println();
        System.out.println("Zadejte jméno pojištěnce:");
        String nameOfInsuredForDeletion = scanner.nextLine().trim();
        System.out.println("Zadejte příjmení pojištěného:");
        String surnameOfInsuredForDeletion = scanner.nextLine().trim();

        if (!repository.isInsuredInList(repository.firstNameCapitalization(nameOfInsuredForDeletion), repository.surnameCapitalization(surnameOfInsuredForDeletion))) {
            System.out.println();
            System.out.println("Zadaný pojištěnec nebyl v databázi nalezen.");
            System.out.println();
        } else {
            repository.deleteInsuredPerson(repository.firstNameCapitalization(nameOfInsuredForDeletion), repository.surnameCapitalization(surnameOfInsuredForDeletion));
            System.out.println();
            System.out.println("Zadaný pojištěnec byl smazán");
            System.out.println();
        }
    }
    // Metoda k změně určitého člověka v ArrayListu insuredPeople pomocí jeho jména a příjmení
    public void editInsuredPerson() {
        while (true) {
            System.out.println();
            System.out.println("Vyberte si prosím požadovanou akci:");
            String[] actions = {"Změna jména pojištěného", "Změna příjmení pojištěného", "Změna telefonního čísla pojištěného", "Změna věku pojištěného", "Konec"};

            for (int i = 0; i < actions.length; i++) {
                System.out.println("[" + (i + 1) + "]: " + actions[i]);
            }

            int choiceOfAction = readIntInput("Zvolte prosím číslo úkonu: ");

            switch (choiceOfAction) {
                case 1:
                    editName();
                    break;
                case 2:
                    editSurname();
                    break;
                case 3:
                    editTelephoneNumber();
                    break;
                case 4:
                    editAge();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Neplatná volba, zvolte prosím znovu.");
            }
        }
    }
    // Metoda k změně jména
    private void editName() {
        String firstName = readStringInput("Zadejte jméno pojištěného: ");
        String lastName = readStringInput("Zadejte příjmení pojištěného: ");
        String newFirstName = readStringInput("Zadejte nové jméno pojištěného: ");
        repository.editNameValue(repository.firstNameCapitalization(firstName), repository.firstNameCapitalization(lastName), repository.firstNameCapitalization(newFirstName));
        System.out.println("Jméno pojištěnce bylo změněno.");
    }
    // Metoda k změně příjmení
    private void editSurname() {
        String firstName = readStringInput("Zadejte jméno pojištěného: ");
        String lastName = readStringInput("Zadejte příjmení pojištěného: ");
        String newLastName = readStringInput("Zadejte nové příjmení pojištěného: ");
        repository.editSurnameValue(repository.firstNameCapitalization(firstName), repository.firstNameCapitalization(lastName), repository.firstNameCapitalization(newLastName));
        System.out.println("Příjmení pojištěnce bylo změněno.");
    }
    // Metoda k změně telefonního čísla
    private void editTelephoneNumber() {
        String firstName = readStringInput("Zadejte jméno pojištěného: ");
        String lastName = readStringInput("Zadejte příjmení pojištěného: ");
        String newTelephoneNumber = readStringInput("Zadejte nové telefonní číslo pojištěného: ");
        repository.editTelephoneNumberValue(repository.firstNameCapitalization(firstName), repository.firstNameCapitalization(lastName), newTelephoneNumber);
        System.out.println("Telefonní číslo pojištěnce bylo změněno.");
    }
    // Metoda k změně věku
    private void editAge() {
        String firstName = readStringInput("Zadejte jméno pojištěného: ");
        String lastName = readStringInput("Zadejte příjmení pojištěného: ");
        int newAge = readIntInput("Zadejte nový věk pojištěného: ");
        repository.editAgeValue(repository.firstNameCapitalization(firstName), repository.firstNameCapitalization(lastName), newAge);
        System.out.println("Věk pojištěnce byl změněn.");
    }
    // Metoda ke zjednodušení inputu uživatele pro větší přehlednost kódu
    private String readStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
    // Metoda k ověření inputu uživatele
    private int readIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup. Zadejte prosím celé číslo.");
            }
        }
    }
    // Metoda k vytvoření záznamu v mé MySQL databázi
    public void createInsuredIntoDatabase() {
        String firstNameCreation = getInput("Zadejte jméno nového pojištěného:", "Jméno");
        String surnameCreation = getInput("Zadejte příjmení nového pojištěného:", "Příjmení");
        String telephoneNumberCreation = getInput("Zadejte telefonní číslo nového pojištěného:", "Telefonní číslo");
        int ageCreation = getIntegerInput();

        System.out.println();
        database.saveInsured(new Insured(firstNameCreation, surnameCreation, telephoneNumberCreation, ageCreation));
        System.out.println();
    }
    // Metoda ke smazání záznamu v mé MySQL databázi
    public void deleteInsuredFromDatabase() {
        System.out.println("Zadejde ID pojištěnce v MySQL databázi.");
        int personId = Integer.parseInt(scanner.nextLine()); // the id of the person to delete

        try {
            System.out.println();
            database.deletePerson(personId);
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Při mazání pojištěnce nastala chyba: " + e.getMessage());
        }
    }
}