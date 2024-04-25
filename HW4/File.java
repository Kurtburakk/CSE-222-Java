/**
 * Represents a file in a file system. This class extends {@link FileSystemElement} to provide specific
 */
public class File extends FileSystemElement {

    /**
     * Constructs a new File instance with a specified name and parent.
     * 
     * @param name The name of the file.
     * @param parent The parent directory of the file. It must be a {@link FileSystemElement} that typically
     *               should be a directory, not another file, to maintain a logical file system structure.
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }

    /**
     * Prints the file's name with a specified prefix, used to format the file's name in the file system display.
     * This method overrides the print method in {@link FileSystemElement}.
     * 
     * @param prefix A string prefix to format the display, typically used for indentation in a tree display.
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + "File: " + getName());
    }
}
