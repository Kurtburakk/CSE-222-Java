import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a file system that manages directories and files.
 * Provides functionality to create, delete, move, search, and list directories and files,
 * as well as sort directory contents by creation date.
 */

public class FileSystem {

    private Directory root;
    private Directory currentDirectory;
    /**
     * Constructs a FileSystem with an initial root directory.
     */
    public FileSystem() {
        this.root = new Directory("root", null);
        this.currentDirectory = root;
    }
    /**
     * Creates a new file in a specified parent directory.
     *
     * @param name   The name of the new file.
     * @param parent The directory in which the file will be created.
     */
    public void createFile(String name, Directory parent) {
        File file = new File(name, parent);
        parent.addElement(file);
    }
    /**
     * Creates a new directory within a specified parent directory and sets it as the current directory.
     *
     * @param name   The name of the new directory.
     * @param parent The directory in which the new directory will be created.
     */
    public void createDirectory(String name, Directory parent) {
        Directory directory = new Directory(name, parent);
        parent.addElement(directory);
    }    
    /**
     * Deletes a file with a specific name in a given directory.
     *
     * @param name   The name of the file to delete.
     * @param parent The directory from which the file will be deleted.
     */
    public void deleteFile(String name, Directory parent) {
        List<FileSystemElement> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            FileSystemElement child = children.get(i);
            if (child instanceof File && child.getName().equals(name)) {
                children.remove(i);
                System.out.println("File deleted: " + name);
                return;
            }
        }
    }
    /**
     * Deletes a directory and all its contents recursively.
     *
     * @param name   The name of the directory to delete.
     * @param parent The directory from which the directory will be deleted.
     */
    public void deleteDirectory(String name, Directory parent) {
        List<FileSystemElement> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            FileSystemElement child = children.get(i);
            if (child instanceof Directory && child.getName().equals(name)) {
                // Recursively delete all contents of the directory
                deleteContents((Directory) child);
                // After deleting all contents, remove the directory itself
                children.remove(i);
                System.out.println("Directory deleted: " + name);
                if (currentDirectory == child) { // Check if the deleted directory is the current directory
                    currentDirectory = parent; // Update current directory to parent
                }
                return;
            }
        }
    }
    /**
     * Recursively deletes all contents of a directory.
     *
     * @param directory The directory whose contents will be deleted.
     */
    private void deleteContents(Directory directory) {        // Recursive method to delete all contents of a directory
        List<FileSystemElement> children = directory.getChildren();
        for (int i = 0; i < children.size(); i++) {
            FileSystemElement child = children.get(i);
            if (child instanceof File) {
                // Delete files directly
                children.remove(i);
                i--; // Adjust index after removing element
            } else if (child instanceof Directory) {
                // Recursively delete subdirectories and their contents
                deleteContents((Directory) child);
                // After deleting contents of the subdirectory, remove the subdirectory itself
                children.remove(i);
                i--; // Adjust index after removing element
            }
        }
    }
        /**
     * Moves a file or directory to a new path.
     *
     * @param name    The name of the file or directory to move.
     * @param newPath The new path where the file or directory will be moved.
     */
    public void moveFileOrDirectory(String name, String newPath) {
        // Find the element to move in the current directory
        FileSystemElement elementToMove = null;
        System.out.println("Move: Current directory: " + getCurrentPath(currentDirectory));
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if (element.getName().equals(name)) {
                elementToMove = element;
                break;
            }
        }
    
        if (elementToMove == null) {
            System.out.println("File/directory not found: " + name);
            return;
        }
    
        // Find the new parent directory
        Directory newParent = findDirectoryByPath(newPath);
        if (newParent == null) {
            System.out.println("Destination directory not found: " + newPath);
            return;
        }
    
        // Check for a valid move operation to avoid a directory being moved into one of its subdirectories
        Directory checkParent = newParent;
        while (checkParent != null) {
            if (checkParent == elementToMove) {
                System.out.println("Invalid move operation: Cannot move a directory into one of its own subdirectories.");
                return;
            }
            checkParent = checkParent.getParent() instanceof Directory ? (Directory) checkParent.getParent() : null;
        }
    
        // Remove the element from the current directory
        this.currentDirectory.getChildren().remove(elementToMove);
    
        // Add the element to the new directory
        newParent.addElement(elementToMove);
    
        // Update the parent reference if the moved element is a directory
        if (elementToMove instanceof Directory) {
            ((Directory) elementToMove).setParent(newParent);
            // Update currentDirectory if the moved directory was the current one
            if (currentDirectory == elementToMove) {
                currentDirectory = newParent;
            }
        }
    
        System.out.println("Moved: " + name + " from " + getCurrentPath(this.currentDirectory) + " to " + newPath);
    }
    /**
    * Finds and returns the directory at the specified path.
    * This method navigates through the file system from a starting directory, based on whether the path is absolute or relative.
    * If any part of the path does not correspond to an existing directory, the method returns {@code null}.
    *
    * @param path the file system path to the directory, which may be absolute or relative
    * @return the {@code Directory} object at the specified path or {@code null} if the path is not valid or does not exist
    */
    private Directory findDirectoryByPath(String path) {
        // Determine the starting point based on whether the path is absolute or relative
        Directory current = path.startsWith("/") ? root : currentDirectory;
    
        // Split the path into parts for traversal
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                // Ignore empty parts and the current directory symbol '.'
                continue;
            } else if (part.equals("..")) {
                // Navigate up to the parent directory if it's not null and it's a Directory
                if (current.getParent() instanceof Directory) {
                    current = (Directory) current.getParent();
                }
                continue;
            }
    
            // Attempt to find the next subdirectory within the current directory
            boolean found = false;
            for (FileSystemElement element : current.getChildren()) {
                if (element instanceof Directory && element.getName().equals(part)) {
                    current = (Directory) element;
                    found = true;
                    break;
                }
            }
    
            if (!found) {
                return null; // Return null if any part of the path doesn't exist
            }
        }
        return current;
    }

    /**
     * Searches for a file or directory by name starting from the root directory.
     *
     * @param name The name of the file or directory to find.
     * @return true if the file or directory is found, false otherwise.
     */

    public boolean search(String name) {
        return searchHelper(root, name, "");
    }

    /**
     * Recursively searches for a file system element with a specified name starting from a given directory.
     * @param directory the directory to start the search from
     * @param name the name of the file system element to find
     * @param currentPath the current path accumulated from the root to the current directory, used to build the full path for printing
     * @return {@code true} if the element is found, {@code false} otherwise
     */

    private boolean searchHelper(Directory directory, String name, String currentPath) {
        for (FileSystemElement element : directory.getChildren()) {
            if (element.getName().equals(name)) {
                // If the element is found, print the full path
                printPathhelperSearch(currentPath, name);
                return true;
            }
            if (element instanceof Directory) {
                // Recursively search within subdirectories
                String newPath = currentPath.isEmpty() ? element.getName() : currentPath + "/" + element.getName();
                if (searchHelper((Directory) element, name, newPath)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
    * Prints the full path to a file system element.
    * This method is called when an element with the specified name is found in the file system.
    * 
    * @param path the current path leading to the directory where the element is found
    * @param name the name of the element whose path is to be printed
    */
    private void printPathhelperSearch(String path, String name) {
        // Print the full path
        System.out.println("Found: /" + path + "/" + name);
    }
    /**
     * Prints the directory tree starting from the root.
     */
    public void printDirectoryTree() {
        System.out.println("Path to current directory from root:");
        printDirectoryTreeHelper(root, "");
    }
    /**
    * Recursively prints the directory tree starting from a specified directory.
    *
    * @param directory the directory from which to start printing the tree
    * @param prefix the string used to indent entries in the tree; increases with recursion depth
    */
    private void printDirectoryTreeHelper(Directory directory, String prefix) {
        System.out.print(prefix);
        if (directory.equals(currentDirectory)) {
            System.out.println("* " + directory.getName() + "/" + " (current directory)");
        } else {
            System.out.println("* " + directory.getName() + "/");
        }
        for (FileSystemElement element : directory.getChildren()) {
            if (element instanceof Directory) {
                printDirectoryTreeHelper((Directory) element, prefix + "  ");
            } else {
                System.out.println(prefix + "  " + element.getName());
            }
        }
    }
    /**
    * Lists the contents of a specified directory to the standard output.
    * @param directory the directory whose contents are to be listed
    */
    public void listContents(Directory directory) {
        for (FileSystemElement element : directory.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("* " + element.getName() + "/");
            } else if (element instanceof File) {
                System.out.println(element.getName());
            }
        }
    }
    /**
     * Sorts the contents of a directory by the creation date.
     *
     * @param directory The directory whose contents will be sorted.
     */
    public void sortDirectoryByDate(Directory directory) {
        List<FileSystemElement> children = directory.getChildren();

        // Sort the children list by creation date using a custom comparator
        Collections.sort(children, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement a, FileSystemElement b) {
                // Get creation dates of a and b
                Timestamp dateA = a.getDateCreated();
                Timestamp dateB = b.getDateCreated();

                // Handle null creation dates
                if (dateA == null && dateB == null) {
                    return 0; // Treat as equal
                } else if (dateA == null) {
                    return 1; // Null dates come last
                } else if (dateB == null) {
                    return -1; // Null dates come last
                }

                // Compare creation dates
                return dateA.compareTo(dateB);
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Sorted contents of " + this.getCurrentPath(currentDirectory) + " by date created:");
        for (FileSystemElement element : directory.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("* " + element.getName() + "/" + " (" + dateFormat.format(element.getDateCreated()) + ")");
            } else if (element instanceof File) {
                System.out.println(element.getName() + " (" + dateFormat.format(element.getDateCreated()) + ")");
            }
        }
    }
    /**
     * Gets the current path from the root to a given directory.
     *
     * @param directory The directory for which to find the path.
     * @return A string representing the path.
     */
    public String getCurrentPath(Directory directory) {
        StringBuilder path = new StringBuilder();
        Directory temp = directory;
        while (temp != null) {
            path.insert(0, "/" + temp.getName());
            temp = (Directory) temp.getParent();
        }
        return path.toString();
    }
    /**
     * Changes the current directory to a new specified path.
     *
     * @param path The path to the new directory.
     * @return The new directory object or null if the path is invalid.
     */
    public Directory changeDirectory(String path) {
        // Check if the path starts with "/" indicating it's an absolute path
        if (path.startsWith("/")) {
            // Start from the root directory
            currentDirectory = root;
            // Split the path into individual directory names
            String[] directories = path.substring(1).split("/");
            // Traverse the directories
            for (String dir : directories) {
                if (dir.isEmpty()) continue;
                for (FileSystemElement element : currentDirectory.getChildren()) {
                    if (element instanceof Directory && element.getName().equals(dir)) {
                        currentDirectory = (Directory) element;
                        break;
                    }
                }
            }
        }
        return currentDirectory;
    }
    /**
     * Returns the root directory of the file system.
     *
     * @return The root directory.
     */
    public Directory getRoot() {
        return root;
    }
}
