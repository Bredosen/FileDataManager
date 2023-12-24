package bredo.cmd.mc.filedatamanager.manipulator;

import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.registries.FileDataRegistry;
import bredo.cmd.mc.filedatamanager.serializers.DefaultJsonSerializer;
import bredo.cmd.mc.filedatamanager.validators.FileDataValidator;
import bredo.cmd.mc.unilink.validators.Validator;

/**
 * retrieve gives warning, but the method, only outputs FileData or throws exception.
 */
public final class FileDataManipulator {

    //<editor-fold desc="Selected FileData name">
    private static String selectedFileDataName;

    public static void selectFileData(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        selectedFileDataName = fileDataName;
    }

    public static String selectedFileData() {
        return selectedFileDataName;
    }

    public static void deselectFileData() {
        selectedFileDataName = null;
    }
    //</editor-fold>

    //<editor-fold desc="General File">
    public static void setFile(final String fileDataName, final String fileDirectory, final String fileName, final String fileExtension, final boolean usePluginDirectory) {
        setFileDirectory(fileDataName, fileDirectory);
        setFileName(fileDataName, fileName);
        setFileExtension(fileDataName, fileExtension);
        setUsePluginDirectory(fileDataName, usePluginDirectory);
    }

    public static void setFile(final String fileDirectory, final String fileName, final String fileExtension, final boolean usePluginDirectory) {
        setFile(selectedFileData(), fileDirectory, fileName, fileExtension, usePluginDirectory);
    }
    //</editor-fold>

    //<editor-fold desc="File Name">
    public static void setFileName(final String fileDataName, final String fileName) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(fileName, "File name");
        FileDataRegistry.retrieve(fileDataName).setFileName(fileName);
    }

    public static void setFileName(final String fileName) {
        setFileName(selectedFileData(), fileName);
    }

    public static String getFileName(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).getFileName();
    }

    public static String getFileName() {
        return getFileName(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="File Directory">

    public static void setFileDirectory(final String fileDataName, final String fileDirectory) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(fileDirectory, "File directory", false);
        FileDataRegistry.retrieve(fileDataName).setFileDirectory(fileDirectory);
    }

    public static void setFileDirectory(final String fileDirectory) {
        setFileName(selectedFileData(), fileDirectory);
    }

    public static String getFileDirectory(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).getFileDirectory();
    }

    public static String getFileDirectory() {
        return getFileDirectory(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="Cache Data">
    public static void setCacheData(final String fileDataName, final boolean cacheData) {
        FileDataValidator.validateFileDataName(fileDataName);
        FileDataRegistry.retrieve(fileDataName).setCacheData(cacheData);
    }

    public static void setCacheData(final boolean cacheData) {
        setCacheData(selectedFileData(), cacheData);
    }

    public static boolean getCacheData(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).isCacheData();
    }

    public static boolean getCacheData() {
        return getCacheData(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="Use Plugin Directory">
    public static void setUsePluginDirectory(final String fileDataName, final boolean usePluginDirectory) {
        FileDataValidator.validateFileDataName(fileDataName);
        FileDataRegistry.retrieve(fileDataName).setUsePluginDirectory(usePluginDirectory);
    }

    public static void setUsePluginDirectory(final boolean usePluginDirectory) {
        setUsePluginDirectory(selectedFileData(), usePluginDirectory);
    }

    public static boolean getUsePluginDirectory(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).isUsePluginDirectory();
    }

    public static boolean getUsePluginDirectory() {
        return getUsePluginDirectory(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="File Extension">
    public static void setFileExtension(final String fileDataName, final String fileExtension) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(fileExtension, "File extension");
        FileDataRegistry.retrieve(fileDataName).setFileExtension(fileExtension);
    }

    public static void setFileExtension(final String fileExtension) {
        setFileExtension(selectedFileData(), fileExtension);
    }

    public static String getFileExtension(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).getFileExtension();
    }

    public static String getFileExtension() {
        return getFileExtension(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="Serializer">
    public static void setSerializer(final String fileDataName, final String serializer) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateString(serializer, "Serializer");
        FileDataRegistry.retrieve(fileDataName).setSerializer(serializer);
    }

    public static void setSerializer(final String serializer) {
        setSerializer(selectedFileData(), serializer);
    }

    public static void setDefaultJsonSerializer(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        FileDataRegistry.retrieve(fileDataName).setSerializer(DefaultJsonSerializer.DEFAULT_JSON_SERIALIZER_NAME);
    }

    public static void setDefaultJsonSerializer() {
        setDefaultJsonSerializer(selectedFileData());
    }

    public static String getSerializer(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).getSerializer();
    }

    public static String getSerializer() {
        return getSerializer(selectedFileData());
    }
    //</editor-fold>

    //<editor-fold desc="IDefaultsData">
    public static void setIDefaultsData(final String fileDataName, final IDefaultsData iDefaultsData) {
        FileDataValidator.validateFileDataName(fileDataName);
        Validator.validateObject(iDefaultsData, "IDefaultsData");
        FileDataRegistry.retrieve(fileDataName).getDefaultsData().setIDefaultsData(iDefaultsData);
    }

    public static void setIDefaultsData(final IDefaultsData iDefaultsData) {
        setIDefaultsData(selectedFileData(), iDefaultsData);
    }

    public static IDefaultsData getIDefaultsData(final String fileDataName) {
        FileDataValidator.validateFileDataName(fileDataName);
        return FileDataRegistry.retrieve(fileDataName).getDefaultsData().getiDefaultsData();
    }

    public static IDefaultsData getIDefaultsData() {
        return getIDefaultsData(selectedFileData());
    }
    //</editor-fold>v
}