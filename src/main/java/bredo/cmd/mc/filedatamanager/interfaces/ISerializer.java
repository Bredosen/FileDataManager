package bredo.cmd.mc.filedatamanager.interfaces;

import bredo.cmd.mc.filedatamanager.utilities.registries.DataRegistry;
import bredo.cmd.mc.filedatamanager.utilities.managers.FileManager;

/**
 * Interface for serializing and deserializing data within the file data manager.
 * <p>
 * This interface defines the core functionality for saving (serializing) and loading (deserializing)
 * data in a standardized format. Implementations of this interface should handle the specific logic
 * required to convert between the application's data structures and a persistent data format,
 * typically a file.
 * </p>
 *
 * @see DataRegistry
 * @see FileManager
 */
public interface ISerializer {

    /**
     * Saves the current state of the data in the {@link DataRegistry} to a file using {@link FileManager}.
     * <p>
     * This method should serialize the data contained within the provided {@link DataRegistry} instance
     * and use the provided {@link FileManager} to write this serialized data to a file. The exact file
     * format and serialization mechanism are dependent on the implementation.
     * </p>
     *
     * @param dataRegistry The data registry containing the data to be serialized and saved.
     * @param fileManager The file manager responsible for handling the file operations.
     * @see DataRegistry
     * @see FileManager
     */
    void save(final DataRegistry dataRegistry, final FileManager fileManager);

    /**
     * Loads the state of data from a file into the {@link DataRegistry} using {@link FileManager}.
     * <p>
     * This method should deserialize data from a file, handled by the provided {@link FileManager},
     * into the provided {@link DataRegistry} instance. The specific file format and deserialization
     * logic are determined by the implementation.
     * </p>
     *
     * @param dataRegistry The data registry where the deserialized data will be stored.
     * @param fileManager The file manager responsible for reading the data from a file.
     * @see DataRegistry
     * @see FileManager
     */
    void load(final DataRegistry dataRegistry, final FileManager fileManager);
}
