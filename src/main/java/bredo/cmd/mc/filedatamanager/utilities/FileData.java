package bredo.cmd.mc.filedatamanager.utilities;

import bredo.cmd.mc.filedatamanager.utilities.registries.DataRegistry;
import bredo.cmd.mc.filedatamanager.utilities.registries.DefaultsDataRegistry;
import bredo.cmd.mc.filedatamanager.utilities.managers.FileManager;
import bredo.cmd.mc.unilink.utilities.RegistryElement;

/**
 * Represents a file data element in the context of a file data management system.
 * This class encapsulates details about a specific file including its name, directory,
 * extension, and serialization settings. It integrates closely with {@link FileManager},
 * {@link DataRegistry}, and {@link DefaultsDataRegistry} to manage file data efficiently.
 * <p>
 * This class extends {@link RegistryElement}, inheriting its registry-related functionalities.
 * </p>
 *
 * @see FileManager
 * @see DataRegistry
 * @see DefaultsDataRegistry
 */
public final class FileData extends RegistryElement {

    private final DataRegistry dataRegistry;
    private final FileManager fileManager;
    private final DefaultsDataRegistry defaultsDataRegistry;

    // File properties
    private String fileName;
    private String fileDirectory;
    private boolean usePluginDirectory; // Determines if the file is located in the plugin directory or elsewhere.
    private String fileExtension;
    private String serializer;
    private boolean cacheData;

    /**
     * Constructs a new {@code FileData} object with a specified name.
     * Initializes internal registries and sets default values for file properties.
     *
     * @param name The name of the file data object.
     */
    private FileData(final String name) {
        super(name);
        this.dataRegistry = new DataRegistry(this);
        this.fileManager = new FileManager(this);
        this.defaultsDataRegistry = new DefaultsDataRegistry(this);

        // Default file settings
        setFileName("fileData");
        setFileExtension("yml");
        setFileDirectory("");
        setUsePluginDirectory(true);
        setSerializer("defaultJsonSerializer");
        setCacheData(true);
    }

    /**
     * Factory method for creating a new {@code FileData} instance.
     *
     * @param name The name to be assigned to the new {@code FileData} object.
     *
     * @return A new instance of {@code FileData} with the specified name.
     */
    public static FileData createFileData(final String name) {
        return new FileData(name);
    }

    /**
     * Sets the file's directory, name, and extension.
     *
     * @param fileDirectory The directory where the file is located.
     * @param fileName      The name of the file.
     * @param fileExtension The extension of the file.
     */
    public void setFile(final String fileDirectory, final String fileName, final String fileExtension) {
        setFileDirectory(fileDirectory);
        setFileName(fileName);
        setFileExtension(fileExtension);
    }

    /**
     * Retrieves the name of the file.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the name of the file.
     *
     * @param fileName The name to set for the file.
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves the directory of the file.
     *
     * @return The file directory.
     */
    public String getFileDirectory() {
        return fileDirectory;
    }

    /**
     * Sets the directory of the file.
     *
     * @param fileDirectory The directory to set for the file.
     */
    public void setFileDirectory(final String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Checks if the file is using the plugin directory.
     *
     * @return {@code true} if the file is located in the plugin directory, {@code false} otherwise.
     */
    public boolean isUsePluginDirectory() {
        return usePluginDirectory;
    }

    /**
     * Sets whether the file is located in the plugin directory.
     *
     * @param usePluginDirectory {@code true} to use the plugin directory, {@code false} otherwise.
     */
    public void setUsePluginDirectory(final boolean usePluginDirectory) {
        this.usePluginDirectory = usePluginDirectory;
    }

    /**
     * Retrieves the file extension.
     *
     * @return The file extension.
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Sets the file extension.
     *
     * @param fileExtension The extension to set for the file.
     */
    public void setFileExtension(final String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * Retrieves the serializer used for file data.
     *
     * @return The serializer identifier.
     */
    public String getSerializer() {
        return serializer;
    }

    /**
     * Sets the
     * <p>
     * serializer to be used for file data.
     *
     * @param serializer The serializer identifier to set.
     */
    public void setSerializer(final String serializer) {
        this.serializer = serializer;
    }

    /**
     * Retrieves the {@link DataRegistry} associated with this file data.
     *
     * @return The {@code DataRegistry} instance.
     */
    public DataRegistry getDataRegistry() {
        return dataRegistry;
    }

    /**
     * Retrieves the {@link FileManager} associated with this file data.
     *
     * @return The {@code FileManager} instance.
     */
    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     * Retrieves the {@link DefaultsDataRegistry} associated with this file data.
     *
     * @return The {@code DefaultsDataRegistry} instance.
     */
    public DefaultsDataRegistry getDefaultsData() {
        return defaultsDataRegistry;
    }

    /**
     * Checks if data caching is enabled.
     *
     * @return {@code true} if data caching is enabled, {@code false} otherwise.
     */
    public boolean isCacheData() {
        return cacheData;
    }

    /**
     * Sets the data caching preference.
     *
     * @param cacheData {@code true} to enable data caching, {@code false} to disable it.
     */
    public void setCacheData(final boolean cacheData) {
        this.cacheData = cacheData;
    }
}