import java.util.LinkedList;
import java.util.List;

/**
 * Represents a directory in a file system, capable of containing other directories and files.
 * This class extends {@link FileSystemElement} to provide specific implementations for directories.
 */
public class Directory extends FileSystemElement {

    private List<FileSystemElement> children;

    /**
     * Constructs a new Directory with a specified name and parent.
     * 
     * @param name The name of the directory.
     * @param parent The parent directory of this directory, which is a FileSystemElement.
     *               Typically, this should be another directory, although it is not strictly enforced.
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * Adds a new element (file or directory) to the directory.
     * 
     * @param element The file system element to add to this directory. This can be either a file or another directory.
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes an element (file or directory) from the directory.
     * 
     * @param element The file system element to remove from this directory.
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Returns a list of all elements (files and directories) contained within this directory.
     * 
     * @return A list of FileSystemElement instances, representing all the children of this directory.
     */
    public List<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Prints the directory's structure recursively, including all its children.
     * 
     * @param prefix A string prefix used for formatting the output to represent the hierarchy level (indentation).
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + "Directory: " + getName());
        for (FileSystemElement elem : children) {
            elem.print(prefix + "    ");  // Increase indentation for children
        }
    }
}
