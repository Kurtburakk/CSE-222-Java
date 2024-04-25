import java.sql.Timestamp;

/**
 * Represents an abstract element in a file system.
 */
public abstract class FileSystemElement {

    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    /**
     * Constructs a new FileSystemElement with a specified name and parent.
     *
     * @param name The name of the file system element.
     * @param parent The parent element in the file system hierarchy.
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.parent = parent;
    }

    /**
     * Returns the name of this file system element.
     *
     * @return The name of the element.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the timestamp indicating when this element was created.
     *
     * @return The creation timestamp of the element.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent of this file system element.
     *
     * @return The parent element, or null if this is the root element.
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets a new parent for this file system element.
     *
     * @param parent The new parent element to set.
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Abstract method to print information.
     * @param prefix A prefix string used for formatting the output, typically used for hierarchy indentation in tree displays.
     */
    public abstract void print(String prefix);
}
