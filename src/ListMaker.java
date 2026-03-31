import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

	public static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;

		do {
			displayList();
			displayMenu();

			String cmd = SafeInput.getRegExString(in, "Enter a command", "[AaDdIiPpQq]");
			cmd = cmd.toUpperCase();

			switch (cmd) {
				case "A":
					addItem(in);
					break;
				case "D":
					deleteItem(in);
					break;
				case "I":
					insertItem(in);
					break;
				case "P":
					System.out.println("Printing current list...");
					break;
				case "Q":
					done = quitProgram(in);
					break;
			}
			System.out.println();

		} while (!done);
	}

	private static void displayList() {
		System.out.println("=========================================");
		if (list.isEmpty()) {
			System.out.println("  The list is currently empty.");
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("  %d. %s\n", i + 1, list.get(i));
			}
		}
		System.out.println("=========================================");
	}

	private static void displayMenu() {
		System.out.println("A - Add an item to the list");
		System.out.println("D - Delete an item from the list");
		System.out.println("I - Insert an item into the list");
		System.out.println("P - Print (display) the list");
		System.out.println("Q - Quit the program");
	}

	private static void addItem(Scanner in) {
		String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
		list.add(item);
		System.out.println("Item added successfully.");
	}

	private static void deleteItem(Scanner in) {
		if (list.isEmpty()) {
			System.out.println("The list is empty. Nothing to delete.");
			return;
		}
		int itemNum = SafeInput.getRangedInt(in, "Enter the item number to delete", 1, list.size());
		int index = itemNum - 1;
		String removedItem = list.remove(index);
		System.out.println("Removed: " + removedItem);
	}

	private static void insertItem(Scanner in) {
		if (list.isEmpty()) {
			System.out.println("List is empty. Adding item to the start of the list.");
			addItem(in);
			return;
		}
		int insertNum = SafeInput.getRangedInt(in, "Enter the item number to insert at", 1, list.size() + 1);
		int index = insertNum - 1;
		String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
		list.add(index, item);
		System.out.println("Item inserted successfully.");
	}

	private static boolean quitProgram(Scanner in) {
		return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
	}
}