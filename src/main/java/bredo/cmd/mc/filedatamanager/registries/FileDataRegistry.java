package bredo.cmd.mc.filedatamanager.registries;

import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.unilink.utilities.Registry;

import java.util.LinkedHashSet;

public final class FileDataRegistry extends Registry<FileData> {

    //<editor-fold desc="Singleton & Constructor">
    private final static FileDataRegistry SINGLETON;

    static {
        SINGLETON = new FileDataRegistry("FileData");
    }

    public static FileDataRegistry getInstance() {
        return SINGLETON;
    }

    public FileDataRegistry(final String registryType) {
        super(registryType);
    }
    //</editor-fold>

    public static boolean register(final FileData fileData) {
        return getInstance().registerElement(fileData);
    }

    public static boolean unRegister(final FileData fileData) {
        return getInstance().unRegisterElement(fileData);
    }

    public static boolean unRegister(final String fileDataName) {
        return getInstance().unRegisterElement(fileDataName);
    }

    public static boolean contains(final FileData fileData) {
        return getInstance().containsElement(fileData);
    }

    public static boolean contains(final String fileDataName) {
        return getInstance().containsElement(fileDataName);
    }

    public static FileData retrieve(final String fileDataName) {
        return getInstance().retrieveElement(fileDataName);
    }
}
