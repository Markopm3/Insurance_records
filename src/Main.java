public class Main {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        ui.welcome();
        boolean choice = true;
        do {
            ui.choiceOfAction();
            int operation = ui.parsUsersChoice();
            switch (operation) {
                case 1: // přidání nového člověka do listu pojištěnců
                    ui.createInsured();
                    ui.showConfirmation();
                    break;
                case 2: // vypsání přidaných záznamů
                    ui.listInsuredPeople();
                    ui.showContinue();
                    break;
                case 3: // hledání v uložených záznamech
                    ui.searchForInsuredPerson();
                    ui.showContinue();
                    break;
                case 4: // mazání záznamů
                    ui.deleteInsuredPerson();
                    ui.showContinue();
                    break;
                case 5: // editace jména, příjmení, telefonního čisla a věku uložených pojištěnců
                    ui.editInsuredPerson();
                    break;
                case 6: // uložení pojištěnce do MySQL externí databáze
                    ui.createInsuredIntoDatabase();
                    break;
                case 7: // smazání pojištěnce z externí MySQL databáze
                    ui.deleteInsuredFromDatabase();
                    break;
                case 8: // ukončení cyklu a aplikace
                    choice = false;
                    break;
            }
        } while (choice);
    }
}




