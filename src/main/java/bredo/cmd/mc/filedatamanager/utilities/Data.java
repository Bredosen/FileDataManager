package bredo.cmd.mc.filedatamanager.utilities;

import bredo.cmd.mc.unilink.utilities.RegistryElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

public final class Data extends RegistryElement {

    private final LinkedHashSet<String> comments;
    private final HashMap<String, Object> configurations;
    private Object data;
    private Class<?> dataType;

    public Data(final String path) {
        super(path);
        this.comments = new LinkedHashSet<>();
        this.configurations = new HashMap<>();
        this.data = null;
    }

    public String getPath() {
        return getName();
    }

    public LinkedHashSet<String> getComments() {
        return comments;
    }

    public void clearComments() {
        getComments().clear();
    }

    public boolean containsComments() {
        return !getComments().isEmpty();
    }

    public void addComment(final String comment) {
        getComments().add(comment);
    }

    public void setComments(final Collection<? extends String> comments) {
        clearComments();
        getComments().addAll(comments);
    }

    public HashMap<String, Object> getConfigurations() {
        return configurations;
    }

    public boolean containConfiguration(final String callName) {
        return getConfigurations().containsKey(callName);
    }

    public void addConfiguration(final String callName, final Object configuration) {
        getConfigurations().put(callName, configuration);
    }

    public Object getConfiguration(final String callName) {
        return getConfigurations().get(callName);
    }

    public boolean containsConfigurations() {
        return !getConfigurations().isEmpty();
    }

    public void clearConfigurations() {
        getConfigurations().clear();
    }

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
        this.dataType = data.getClass();
    }

    public Class<?> getDataType() {
        return dataType;
    }
}
