package bredo.cmd.mc.filedatamanager.utilities.registries;

import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.utilities.Data;
import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.filedatamanager.utilities.handlers.DataSetter;
import bredo.cmd.mc.unilink.utilities.Registry;

import java.util.LinkedHashSet;

public final class DefaultsDataRegistry extends Registry<Data> {

    private final FileData fileData;
    private IDefaultsData iDefaultsData;
    private DataSetter dataSetter;

    public DefaultsDataRegistry(final FileData fileData) {
        super("Data");
        this.fileData = fileData;
        this.iDefaultsData = null;
    }

    public boolean register(final Data data) {
        return registerElement(data);
    }

    public boolean unRegister(final Data data) {
        return unRegisterElement(data);
    }

    public boolean unRegister(final String path) {
        return unRegisterElement(path);
    }

    public boolean contains(final Data data) {
        return containsElement(data);
    }

    public boolean contains(final String path) {
        return containsElement(path);
    }

    public Data retrieve(final String path) {
        return retrieveElement(path);
    }

    public DataSetter createDataSetter() {
        if (dataSetter == null) dataSetter = new DataSetter(getFileData());

        dataSetter.reset();

        return dataSetter;
    }

    public FileData getFileData() {
        return fileData;
    }

    public IDefaultsData getiDefaultsData() {
        return iDefaultsData;
    }

    public void setIDefaultsData(final IDefaultsData iDefaultsData) {
        this.iDefaultsData = iDefaultsData;
    }

}
