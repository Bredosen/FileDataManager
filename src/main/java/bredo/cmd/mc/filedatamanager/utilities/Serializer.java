package bredo.cmd.mc.filedatamanager.utilities;

import bredo.cmd.mc.filedatamanager.interfaces.ISerializer;
import bredo.cmd.mc.unilink.utilities.RegistryElement;

public final class Serializer extends RegistryElement {

    private ISerializer iSerializer;

    private Serializer(final String name) {
        super(name);

    }

    public static Serializer createInstance(final String name) {
        final Serializer serializer = new Serializer(name);
        return serializer;
    }

    public void save(final FileData fileData) {
        getiSerializer().save(fileData.getDataRegistry(), fileData.getFileManager());
    }

    public void load(final FileData fileData) {
        getiSerializer().load(fileData.getDataRegistry(), fileData.getFileManager());
    }

    public ISerializer getiSerializer() {
        return iSerializer;
    }

    public void setiSerializer(final ISerializer iSerializer) {
        this.iSerializer = iSerializer;
    }
}
