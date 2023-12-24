package bredo.cmd.mc.filedatamanager.manipulator;

import bredo.cmd.mc.filedatamanager.registries.FileDataRegistry;
import bredo.cmd.mc.filedatamanager.validators.FileDataValidator;
import bredo.cmd.mc.unilink.validators.Validator;

/**
 * Provides a set of static methods for manipulating file data in a specified format.
 * This class includes functionalities for selecting, deselecting, retrieving, and modifying data
 * within a file data registry.
 *
 * @see bredo.cmd.mc.filedatamanager.registries.FileDataRegistry
 * @see bredo.cmd.mc.filedatamanager.validators.FileDataValidator
 */
public final class DataManipulator {

    /**
     * The name of the currently selected file data.
     */
    private static String selectedFileDataName;

    /**
     * Selects a file data by its name for subsequent operations.
     * This method validates the file data name before setting it as the selected file data.
     *
     * @param fileDataName The name of the file data to select.
     * @throws IllegalArgumentException If the file data name is invalid.
     * @see FileDataValidator#validateFileDataName(String)
     */
    public static void selectFileData(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        selectedFileDataName = fileDataName;
    }

    /**
     * Returns the name of the currently selected file data.
     *
     * @return The name of the selected file data, or {@code null} if none is selected.
     */
    public static String selectedFileData() {
        return selectedFileDataName;
    }

    /**
     * Deselects the currently selected file data.
     */
    public static void deselectFileData() {
        selectedFileDataName = null;
    }

    /**
     * Retrieves a data value from the file data registry based on the provided file data name and path.
     *
     * @param fileDataName The name of the file data from which to retrieve the value.
     * @param path The path within the file data to the desired value.
     * @return The value retrieved from the specified path in the file data, or {@code null} if not found.
     * @throws IllegalArgumentException If the file data name or path is invalid.
     * @see FileDataRegistry#retrieve(String)
     */
    public static Object get(final String fileDataName, final String path) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(path, "Path");

        return FileDataRegistry.retrieve(fileDataName).getDataRegistry().getValue(path);
    }

    /**
     * Retrieves a data value from the currently selected file data based on the provided path.
     *
     * @param path The path within the file data to the desired value.
     * @return The value retrieved from the specified path in the selected file data, or {@code null} if not found.
     * @throws IllegalStateException If no file data is currently selected.
     * @see #get(String, String)
     * @see #selectedFileData()
     */
    public static Object get(final String path) {
        return get(selectedFileData(), path);
    }

    /**
     * Sets a value in the file data registry for a specified file data name and path.
     *
     * @param fileDataName The name of the file data in which to set the value.
     * @param path The path within the file data where the value should be set.
     * @param value The value to be set at the specified path.
     * @throws IllegalArgumentException If the file data name, path, or value is invalid.
     * @see FileDataRegistry#retrieve(String)
     */
    public static void set(final String fileDataName, final String path, final Object value) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(path, "Path");
        Validator.validateObject(value, "Value");
        FileDataRegistry.retrieve(fileDataName).getDataRegistry().setValue(path, value);
    }

    /**
     * Sets a value in the currently selected file data based on the provided path.
     *
     * @param path The path within the file data where the value should be set.
     * @param value The value to be set at the specified path.
     * @throws IllegalStateException If no file data is currently selected.
     * @see #set(String, String, Object)
     * @see #selectedFileDataName
     */
    public static void set(final String path, final Object value) {
        set(selectedFileDataName, path, value);
    }
}
