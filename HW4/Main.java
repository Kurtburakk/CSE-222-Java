import java.util.Scanner;

/**
 * Main class for managing a console-based file system.
 * Provides options to navigate, modify, and interact with a hierarchical file system.
 */

public class Main {
    private static FileSystem fs = new FileSystem();
    private static Scanner scanner = new Scanner(System.in);
    static Directory currentDirectory;

    /**
     * The main method that initializes the file system and processes user commands.
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        currentDirectory = fs.getRoot(); // Get the root directory

        while (true) {
            System.out.println("=====File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move  file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date");
            System.out.println("9. Exit");
            System.out.println("Please select an option");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after reading integer input

            switch (choice) {
                case 1:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    changeDirectory();
                    break;
                case 2:
                    System.out.println("Listing contents of " + fs.getCurrentPath(currentDirectory));
                    listContents();
                    break;
                case 3:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    System.out.print("Create file or directory (f/d): ");
                    String fileType = scanner.nextLine().trim().toLowerCase();
                    if (fileType.equals("f")) {
                        System.out.println("Creating a file...");
                        createFile();
                    } else if (fileType.equals("d")) {
                        System.out.println("Creating a directory...");
                        createDirectory();
                    } else {
                        System.out.println("Invalid choice. Please enter 'f' for file or 'd' for directory.");
                    }                    
                    break;
                    case 4:
                    System.out.println("Enter name of file/directory to delete:");
                    String name = scanner.nextLine();
                    deleteFile(name);
                    deleteDirectory(name);
                    break;
                case 5:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    moveElement();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    printDirectoryTree();
                    break;
                case 8:
                    sortDirectoryByDate();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Changes the current directory based on user input.
     */

    private static void changeDirectory() {
        System.out.print("Enter the path of the directory: ");
        String path = scanner.nextLine();
        Directory newDirectory = fs.changeDirectory(path);
        if (newDirectory != null) {
            currentDirectory = newDirectory;
        }
    }
    /**
     * Lists the contents of the current directory.
     */
    private static void listContents() {
        fs.listContents(currentDirectory);
    }
    /**
     * Prompts the user to create either a file or a directory in the current directory.
     */
    private static void createFile() {
        System.out.print("Enter file name to create: ");
        String name = scanner.nextLine();
        fs.createFile(name, currentDirectory);
        System.out.println("File created: " + name);
    }
    /**
     * Creates a new directory in the current directory.
     */
    private static void createDirectory() {
        System.out.print("Enter directory name to create: ");
        String name = scanner.nextLine();
        fs.createDirectory(name, currentDirectory);
        System.out.println("Directory created: " + name);
    }
    /**
     * Deletes a file in the current directory.
     */
    private static void deleteFile(String name) {
        fs.deleteFile(name, currentDirectory);
    }
    /**
     * Deletes a directory in the current directory.
     */
    private static void deleteDirectory(String name) {
        fs.deleteDirectory(name, currentDirectory);
    }
    /**
     * Moves a file or directory to a new location specified by the user.
     */
    private static void moveElement() {
        System.out.print("Enter name of file/directory to move: ");
        String moveName = scanner.nextLine();
        System.out.print("Enter new path for the file/directory: ");
        String movePath = scanner.nextLine();
        fs.moveFileOrDirectory(moveName, movePath);
    }
    /**
     * Searches for a file or directory starting from the root.
     */
    private static void search() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        System.out.println("Searching from root...");
        boolean found = fs.search(query);
        if(!found)
            System.out.println("Not Found");
    }
    /**
     * Prints the entire directory tree starting from the root.
     */
    private static void printDirectoryTree() {
        fs.printDirectoryTree();
    }
    /**
     * Sorts and displays the contents of the current directory by date.
     */
    private static void sortDirectoryByDate() {
        fs.sortDirectoryByDate(currentDirectory);
    }
}
