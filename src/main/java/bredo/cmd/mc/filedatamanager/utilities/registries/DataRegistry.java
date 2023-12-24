package bredo.cmd.mc.filedatamanager.utilities.registries;

import bredo.cmd.mc.filedatamanager.interfaces.IDefaultsData;
import bredo.cmd.mc.filedatamanager.registries.SerializerMatrix;
import bredo.cmd.mc.filedatamanager.utilities.Data;
import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.filedatamanager.utilities.Serializer;
import bredo.cmd.mc.unilink.handlers.ExceptionHandler;
import bredo.cmd.mc.unilink.utilities.Registry;
import bredo.cmd.mc.unilink.validators.Validator;

public final class DataRegistry extends Registry<Data> {

    private final FileData fileData;

    public DataRegistry(final FileData fileData) {
        super("Data");
        this.fileData = fileData;
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

    public void setValue(final String path, final Object value) {
        if (getFileData().isCacheData()) {
            final Data data = retrieve(path);
            data.setData(value);
            return;
        }

        final String serializerName = getFileData().getSerializer();
        Validator.validateString(serializerName, "Serializer name");

        final Serializer serializer = SerializerMatrix.retrieve(serializerName);
        serializer.load(getFileData());

        final Data data = retrieve(path);
        data.setData(value);
        serializer.save(getFileData());
        clearRegistry();
    }

    public Object getValue(final String path) {
        if (getFileData().isCacheData()) {
            final Data data = retrieve(path);
            return data.getData();
        }

        final String serializerName = getFileData().getSerializer();
        final DefaultsDataRegistry defaultsDataRegistry = getFileData().getDefaultsData();

        final IDefaultsData iDefaultsData = defaultsDataRegistry.getiDefaultsData();
        if (iDefaultsData == null) {
            ExceptionHandler.throwLogException(new NullPointerException("IDefaultsData for " + serializerName + " cannot be null!"));
            return null;
        }

        iDefaultsData.defaultDataSetter(defaultsDataRegistry.createDataSetter());

        for (final Data data : defaultsDataRegistry.getRegistry())
            if (!getFileData().getDataRegistry().contains(data.getPath())) getFileData().getDataRegistry().register(data);

        Validator.validateString(serializerName, "Serializer name");

        final Serializer serializer = SerializerMatrix.retrieve(serializerName);
        serializer.load(getFileData());

        final Data data = retrieve(path);
        clearRegistry();
        getFileData().getDefaultsData().clearRegistry();

        return data.getData();
    }

    public FileData getFileData() {
        return fileData;
    }
}
