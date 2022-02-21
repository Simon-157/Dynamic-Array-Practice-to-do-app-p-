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
     * If the number of items in the list is equal to the size of the array, then
     * create a new array that
     * is twice the size of the current array, copy the items from the current array
     * into the new array,
     * and then replace the current array with the new array
     * 
     * @param newTask the new task to be added to the list.
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

    /**
     * If the array is full, double its size
     */
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

    /**
     * Shift all the items in the array to the left by one position
     */
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
     * Returns the number of items in the queue
     * 
     * @return The number of items in the list.
     */
    public int getNumItems() {
        return this.numItems;
    }

    /**
     * Empty the list of ToDoListItems
     */
    public void emptyList() {

        toDoListItems = new ToDoListItem[1];
    }

    /**
     * Return the number of items in the to do list that are done.
     * 
     * @return The number of items done.
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

    /**
     * Prints out the items in the list
     */
    public void listItem() {
        if (toDoListItems == null || this.numItems == 0) {
            System.out.println("You have no items on your schedule");
        } else {
            this.getNumItemsDone();
            for (int index = 0; index < this.numItems; index++) {
                this.getItemAt(index);

            }
        }

    }

    /**
     * Prints the item at the specified index
     * 
     * @param index The position of the item you want to get.
     */
    public void getItemAt(int index) {
        try {
            ToDoListItem foundTask = toDoListItems[index];
            if (index == 0) {
                index++;
            }
            System.out.println(index + ") " + foundTask);
            // return foundTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The position you entered is invalid /no item found");

        }

    }

    /**
     * Remove an item from the ToDoList at the specified index
     * 
     * @param index The index of the item to remove.
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
     * Mark the task at the given index as done
     * 
     * @param index The index of the item to mark as done.
     */

    public void markAsDone(int index) {
        if (index < 0 || index >= this.numItems) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
        } else {
            toDoListItems[index].markDone();

        }

    }

    public void markAsNotDone(int index) {
        if (index < 0 || index >= this.getNumItems()) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
        }
        toDoListItems[index].markNotDone();
    }

    public int find(String keyword) {

        for (int index = 0; index < this.numItems; index++) {
            if (toDoListItems[index].getTask().toLowerCase().contains(keyword)) {
                return index;
            }
        }
        return -1;
    }

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
                ToDoListItem task = new ToDoListItem(taskList[0]);

                if (taskList[1].equals("done")) {
                    task.done = true;

                } else {
                    task.done = false;
                }
                this.addItem(task.toString());
            }

            file.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading From file.");
            e.printStackTrace();
        }
    }

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
