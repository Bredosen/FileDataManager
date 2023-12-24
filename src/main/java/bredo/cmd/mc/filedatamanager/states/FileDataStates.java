package bredo.cmd.mc.filedatamanager.states;

import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.registries.FileDataRegistry;
import bredo.cmd.mc.filedatamanager.registries.SerializerMatrix;
import bredo.cmd.mc.filedatamanager.utilities.Data;
import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.filedatamanager.utilities.Serializer;
import bredo.cmd.mc.filedatamanager.utilities.registries.DefaultsDataRegistry;
import bredo.cmd.mc.filedatamanager.validators.FileDataValidator;
import bredo.cmd.mc.unilink.handlers.ExceptionHandler;
import bredo.cmd.mc.unilink.validators.Validator;
import org.bukkit.plugin.java.JavaPlugin;

public final class FileDataStates {

    //<editor-fold desc="Selected Plugin & FileData">
    private static JavaPlugin selectedPlugin;
    private static String selectedFileDataName;

    public static void select(final JavaPlugin javaPlugin, final String fileDataName) {
        selectPlugin(javaPlugin);
        selectFileData(fileDataName);
    }

    public static void selectPlugin(final JavaPlugin javaPlugin) {
        Validator.validateObject(javaPlugin, "Plugin");
        selectedPlugin = javaPlugin;
    }

    public static void selectFileData(final String fileDataName) {
        Validator.validateString(fileDataName, "FileDataName");
        selectedFileDataName = fileDataName;
    }

    public static JavaPlugin selectedPlugin() {
        return selectedPlugin;
    }

    public static String selectedFileData() {
        return selectedFileDataName;
    }

    public static void deselect() {
        deselectPlugin();
        deselectFileData();
    }

    public static void deselectPlugin() {
        selectedPlugin = null;
    }

    public static void deselectFileData() {
        selectedFileDataName = null;
    }
    //</editor-fold>

    public static void load(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);

        final FileData fileData = FileDataRegistry.retrieve(fileDataName);
        if (!fileData.isCacheData()) throw new IllegalStateException("Cannot load FileData, as cashed has been turned off");

        final String serializerName = fileData.getSerializer();
        Validator.validateString(serializerName, "Serializer name");

        final Serializer serializer = SerializerMatrix.retrieve(serializerName);
        serializer.load(fileData);
    }

    public static void load() {
        load(selectedFileDataName);
    }

    public static void loadDefaults(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);

        final FileData fileData = FileDataRegistry.retrieve(fileDataName);
        if (!fileData.isCacheData()) throw new IllegalStateException("Cannot load FileData defaults, as cashed has been turned off");

        final DefaultsDataRegistry defaultsDataRegistry = fileData.getDefaultsData();

        final IDefaultsData iDefaultsData = defaultsDataRegistry.getiDefaultsData();
        if (iDefaultsData == null) {
            ExceptionHandler.throwLogException(new NullPointerException("The IDefaultsData for " + fileDataName + " cannot be null!"));
            return;
        }

        iDefaultsData.defaultDataSetter(defaultsDataRegistry.createDataSetter());

        for (final Data data : defaultsDataRegistry.getRegistry())
            if (!fileData.getDataRegistry().contains(data.getPath())) fileData.getDataRegistry().register(data);
    }

    public static void loadDefaults() {
        loadDefaults(selectedFileDataName);
    }

    public static void save(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);

        final FileData fileData = FileDataRegistry.retrieve(fileDataName);
        if (!fileData.isCacheData()) throw new IllegalStateException("Cannot save FileData, as cashed has been turned off");

        final String serializerName = fileData.getSerializer();
        Validator.validateString(serializerName, "Serializer name");

        final Serializer serializer = SerializerMatrix.retrieve(serializerName);
        serializer.save(fileData);
    }

    public static void save() {
        save(selectedFileDataName);
    }

    public static void create(final String fileDataName, final JavaPlugin javaPlugin) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateObject(javaPlugin, "Plugin");
        final FileData fileData = FileDataRegistry.retrieve(fileDataName);
        fileData.getFileManager().createFile(javaPlugin);
    }

    public static void create(final String fileDataName) {
        create(fileDataName, selectedPlugin);
    }

    public static void create() {
        create(selectedFileDataName, selectedPlugin);
    }

    public static boolean exists(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        final FileData fileData = FileDataRegistry.retrieve(fileDataName);
        return fileData.getFileManager().created();
    }

    public static boolean exists() {
        return exists(selectedFileDataName);
    }

}
