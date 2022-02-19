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
            {
                for (int i = 0; i < size; i++) {
                    dummyArray[i] = toDoListItems[i];
                }
                // dummyArray = Arrays.copyOf(toDoListItems, size * 2);
            }
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
            size = this.numItems;
            toDoListItems = dummyArray;
        }

    }

    public int getNumItems() {
        System.out.println("num items: " + this.numItems);
        return this.numItems;
    }

    public void emptyList() {

        toDoListItems = new ToDoListItem[1];
    }

    public void listItem() {
        int numberDone = 0;
        int i = 1;
        if (toDoListItems == null || this.numItems == 0) {
            System.out.println("You have no items on your schedule");
        } else {
            for (ToDoListItem item : this.toDoListItems) {
                System.out.println(i + ") " + item.toString());
                // if (item.contains("done")) {
                // numberDone++;
                // }
                i++;
            }
        }

    }

    public int find(String keyword) {

        int i = 0;
        int k = 0;
        System.out.println(k + "items in your to-to list marched " + keyword);
        for (ToDoListItem item : toDoListItems) {

            if (item.toString().contains(keyword)) {

                System.out.println(item.toString());
                k++;
            } else {
                return -1;
            }
            i++;
        }
        System.out.println(k + "items in your to-to list marched " + keyword);
        return i;
    }

    public ToDoListItem[] getList() {
        return toDoListItems;
    }

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
            toDoListItems = newToDoListItems;

        }

    }

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

    public void markAsNotDone(int index) {
        if (index < 0 || index >= this.getNumItems()) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
        }
        ToDoListItem task = toDoListItems[index];
        task.markNotDone();
        toDoListItems[index] = task;
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

    // public static void main(String[] args) {

    // ToDoList app = new ToDoList();

    // String task1 = new String("Hi");
    // String task0 = new String("yi");
    // String task2 = new String("ti");
    // String task3 = new String("gi");
    // String task4 = new String("ki");
    // String task5 = new String("ri");

    // app.addItem(task1);
    // app.addItem(task0);
    // app.addItem(task2);
    // app.addItem(task3);
    // app.addItem(task4);
    // app.addItem(task5);

    // app.getNumItems();

    // }
}