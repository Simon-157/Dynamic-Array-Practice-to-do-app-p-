
import java.util.Scanner;

public class App {
    static ToDoList app = new ToDoList();;

    // protected static String fileName = "scheduleFile.csv";

    public static void main(String[] args) {
        app.readListFromFile();
        Scanner input = new Scanner(System.in);
        Scanner str = new Scanner(System.in);

        System.out.println("WELCOME TO MYSCHEDULE APP\nChoose an option to continue.");
        System.out.println(
                "Make sure to quit before living to save all made changes \n1.View items on my list \n" +
                        "2.Add to my lsit \n" +
                        "3.Remove an item from my list \n" +
                        "4.Mark-done to an item \n" +
                        "5.Mark-not-done to an item \n" +
                        "6.Find an item in the list \n" +
                        "7.Quit \n");
        String newItem;
        while (true) {

            String opt = input.next();
            int option;
            try {
                option = Integer.parseInt(opt);

                if (option == 7) {

                    System.out.print("Saving items to file.....\n");
                    app.writeListToFile();
                    break;
                }
                // app.writeListToFile();
                if (option == 1) {

                    app.listItem();

                }

                if (option == 2) {
                    System.out.println("Enter the task to add to the list");
                    newItem = str.next();
                    app.addItem(newItem);
                    System.out.println(app.numItems);
                    System.out.println("An item successfully added to the list");
                }

                if (option == 3) {

                    System.out.println("Enter the position of the task to be removed: ");
                    int index = str.nextInt();
                    app.removeItemAt(index - 1);

                }

                if (option == 4) {
                    System.out.println("Enter the position of the task: ");
                    int index = str.nextInt();
                    app.markAsDone(index - 1);

                }

                if (option == 5) {
                    System.out.println("Enter the position of the task: ");
                    int index = str.nextInt();
                    app.markAsNotDone(index - 1);

                }

                if (option == 6) {
                    System.out.println("Enter a keyword to search for the task: ");
                    String keyword = str.nextLine();
                    app.find(keyword);

                }

                System.out.println("Choose an option to continue");

            } catch (NumberFormatException e) {
                System.out.println("Invalid option! Try again.");

            }

        }

        input.close();
        str.close();

    }

}
