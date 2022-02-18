import java.util.Scanner;

public class ToDoList {
    protected int numItems;
    protected int size;
    ToDoListItem[] toDoListItems;

    public ToDoList() {
        // toDoListItems = new String[size];
        numItems = 0;
        size = 1;
        toDoListItems = new ToDoListItem[size];

    }

    public void addItem(String newTask) {

        if (numItems == size) {
            helper();
        }
        ToDoListItem task = new ToDoListItem(newTask);
        toDoListItems[numItems] = task;
        numItems++;
        shiftItems();
        // System.out.println("number of items is " + numItems);

    }

    private void helper() {
        ToDoListItem[] dummyArray = null;
        if (numItems == size) {
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

        if (numItems > 0) {
            dummyArray = new ToDoListItem[numItems];
            for (int i = 0; i < numItems; i++) {
                dummyArray[i] = toDoListItems[i];
            }
            size = numItems;
            toDoListItems = dummyArray;
        }

    }

    public int getNumItems() {
        System.out.println("num items: " + numItems);
        return numItems;
    }

    public void emptyList() {
        toDoListItems = null;
    }

    public void listItem() {
        int numberDone = 0;
        int i = 1;
        if (toDoListItems == null || numItems == 0) {
            System.out.println("You have no items on your schedule");
        } else {
            for (ToDoListItem item : toDoListItems) {
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
        if (index < 0 || index >= this.getNumItems()) {
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

    // public static void main(String[] args) {

    // ToDoList app = new ToDoList();

    // ToDoListItem task1 = new ToDoListItem("Hi");
    // ToDoListItem task0 = new ToDoListItem("yi");
    // ToDoListItem task2 = new ToDoListItem("ti");
    // ToDoListItem task3 = new ToDoListItem("gi");
    // ToDoListItem task4 = new ToDoListItem("ki");
    // ToDoListItem task5 = new ToDoListItem("ri");

    // app.addItem(task1.toString());
    // app.addItem(task0.toString());
    // app.addItem(task2.toString());
    // app.addItem(task3.toString());
    // app.addItem(task4.toString());
    // app.addItem(task5.toString());

    // app.getNumItems();

    // }

}