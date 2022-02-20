import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class ToDoList {
    int numItems;
    int size;

    ToDoListItem[] toDoListItems;

    public ToDoList() {
        // toDoListItems = new String[size];
        this.numItems = 0;
        this.size = 1;
        toDoListItems = new ToDoListItem[this.size];

    }

    /**
     * @param newTask
     */
    public void addItem(String newTask) {

        if (this.numItems == size) {
            helper();
        }
        ToDoListItem task = new ToDoListItem(newTask);
        toDoListItems[this.numItems] = task;
        this.numItems++;
        shiftItems();
        // System.out.println("number of items is " + this.numItems);

    }

    private void helper() {
        ToDoListItem[] dummyArray = null;
        if (this.numItems == size) {
            dummyArray = new ToDoListItem[size * 2];

            for (int i = 0; i < size; i++) {
                dummyArray[i] = toDoListItems[i];
            }
            // dummyArray = Arrays.copyOf(toDoListItems, size * 2);

        }
        toDoListItems = dummyArray;
        size = size * 2;
    }

    public void shiftItems() {
        ToDoListItem[] dummyArray = null;

        if (this.numItems > 0) {
            dummyArray = new ToDoListItem[this.numItems];
            for (int i = 0; i < this.numItems; i++) {
                dummyArray[i] = toDoListItems[i];
            }
        }
        size = this.numItems;
        toDoListItems = dummyArray;

    }

    /**
     * @return int
     */
    public int getNumItems() {
        return this.numItems;
    }

    public void emptyList() {

        toDoListItems = new ToDoListItem[1];
    }

    /**
     * @return int
     */
    public int getNumItemsDone() {
        int numberDone = 0;
        for (ToDoListItem item : this.toDoListItems) {
            if (item.isDone()) {
                numberDone++;
            }
        }
        System.out.println(
                "You have " + this.getNumItems() + " items on your schedule, " + numberDone + " of which are done.");
        return numberDone;

    }

    public void listItem() {

        int i = 1;
        if (toDoListItems == null || this.numItems == 0) {
            System.out.println("You have no items on your schedule");
        } else {
            this.getNumItemsDone();
            for (ToDoListItem item : this.toDoListItems) {
                System.out.println(i + ") " + item.toString());
                i++;
            }
        }

    }

    /**
     * @param index
     */
    public void getItemAt(int index) {
        try {
            ToDoListItem foundTask = toDoListItems[index];
            System.out.println(index + ") " + foundTask);
            // return foundTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The position you entered is invalid /no item found");

        }

    }

    /**
     * @param index
     */
    public void removeItemAt(int index) {

        if (index < 0 || index >= toDoListItems.length || toDoListItems == null) {
            return;
        } else {

            ToDoListItem[] newToDoListItems = new ToDoListItem[toDoListItems.length - 1];

            for (int j = 0, b = 0; j < toDoListItems.length; j++) {
                if (j == index) {
                    continue;
                }
                newToDoListItems[b++] = toDoListItems[j];
            }
            numItems--;
            toDoListItems = newToDoListItems;

        }

    }

    /**
     * @param index
     */
    public void markAsDone(int index) {
        if (index < 0 || index >= this.numItems) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
        } else {
            ToDoListItem task = toDoListItems[index];
            task.markDone();
            toDoListItems[index] = task;
        }
        // toDoListItems[index] = task.toString();

    }

    /**
     * @param index
     */
    public void markAsNotDone(int index) {
        if (index < 0 || index >= this.getNumItems()) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
        }
        ToDoListItem task = toDoListItems[index];
        task.markNotDone();
        toDoListItems[index] = task;
    }

    /**
     * @param keyword
     * @return int
     */
    public int find(String keyword) {

        for (int index = 0; index < this.numItems; index++) {
            if (toDoListItems[index].getTask().toLowerCase().contains(keyword)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * @param startingIndex
     * @param keyword
     * @return int
     */
    public int findNext(int startingIndex, String keyword) {
        for (int index = startingIndex; index < this.numItems; index++) {
            if (toDoListItems[index].getTask().toLowerCase().contains(keyword)) {
                return index;
            }
        }
        return -1;

    }

    public void writeListToFile() {

        String fileName = "scheduleFile.csv";

        try {
            FileWriter scheduleFile = new FileWriter(fileName);
            for (ToDoListItem item : toDoListItems) {
                String status = "not done";
                if (item.isDone()) {
                    status = "done";
                }
                scheduleFile.write(item.getTask() + ',' + status + "\n");
            }
            scheduleFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    public void readListFromFile() {

        File scheduleFile = new File("scheduleFile.csv");
        try {
            // this.emptyList();
            Scanner file = new Scanner(scheduleFile);
            while (file.hasNextLine()) {
                String[] taskList = file.nextLine().split(",", 2);
                String task = taskList[0];
                this.addItem(task);
                for (ToDoListItem item : toDoListItems) {
                    if (taskList[1].equals("done")) {
                        item.markDone();

                    } else {
                        item.markNotDone();
                    }

                }

            }

            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading From file.");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    /* Driver code */
    public static void main(String[] args) {

        ToDoList app = new ToDoList();

        String task1 = new String("Hi");
        String task0 = new String("yi");
        String task2 = new String("ti");
        String task3 = new String("gi");
        String task4 = new String("ki");
        String task5 = new String("ri");

        app.addItem(task1);
        app.addItem(task0);
        app.addItem(task2);
        app.addItem(task3);
        app.addItem(task4);
        app.addItem(task5);

        app.getNumItems();

        // }
    }
}
