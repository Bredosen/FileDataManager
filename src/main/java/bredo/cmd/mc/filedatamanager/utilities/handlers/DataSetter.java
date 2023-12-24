package bredo.cmd.mc.filedatamanager.utilities.handlers;

import bredo.cmd.mc.filedatamanager.utilities.Data;
import bredo.cmd.mc.filedatamanager.utilities.FileData;
import bredo.cmd.mc.unilink.validators.Validator;

import java.util.LinkedHashSet;

public final class DataSetter {

    private final FileData fileData;

    private Data currentData;

    public void selectCurrentData(final Data data) {
        this.currentData = data;
    }

    public Data currentData() {
        return currentData;
    }

    public void deselectCurrentData() {
        selectCurrentData(null);
    }

    public DataSetter(final FileData fileData) {
        this.fileData = fileData;
    }

    public FileData getFileData() {
        return fileData;
    }

    public void create(final String path) {
        create(path, false);
    }

    public void create(final String path, final boolean select) {
        final Data data = new Data(path);
        if (select) selectCurrentData(data);
        getFileData().getDefaultsData().register(data);
    }

    //<editor-fold desc="Value">
    public void setValue(final Data data, final Object value) {
        Validator.validateObject(data, "Data");
        Validator.validateObject(value, "Value");
        data.setData(value);
    }

    public void setValue(final String path, final Object value) {
        Validator.validateString(path, "Path");
        final Data data = getFileData().getDefaultsData().retrieve(path);
        setValue(data, value);
    }

    public void setValue(final Object value) {
        setValue(currentData(), value);
    }

    public Object getValue(final Data data) {
        return data.getData();
    }

    public Object getValue(final String path) {
        Validator.validateString(path, "Path");
        return getValue(getFileData().getDefaultsData().retrieve(path));
    }

    public Object getValue() {
        return getValue(currentData());
    }
    //</editor-fold>

    //<editor-fold desc="Comments">
    public void addComment(final Data data, final String comment) {
        Validator.validateObject(data, "Data");
        Validator.validateObject(comment, "Comments");
        data.addComment(comment);
    }

    public void addComment(final String path, final String comment) {
        Validator.validateString(path, "Path");
        final Data data = getFileData().getDefaultsData().retrieve(path);
        addComment(data, comment);
    }

    public void addComment(final String comment) {
        addComment(currentData(), comment);
    }

    public LinkedHashSet<String> getComments(final Data data) {
        return data.getComments();
    }

    public LinkedHashSet<String> getComments(final String path) {
        Validator.validateString(path, "Path");
        return getComments(getFileData().getDefaultsData().retrieve(path));
    }

    public LinkedHashSet<String> getComments() {
        return getComments(currentData());
    }
    //</editor-fold>

    //<editor-fold desc="Configurations">
    public void addConfiguration(final Data data, final String callName, final Object configuration) {
        Validator.validateObject(data, "Data");
        Validator.validateString(callName, "CallName");
        Validator.validateObject(configuration, "Configurations");
        data.addConfiguration(callName, configuration);
    }

    public void addConfiguration(final String path, final String callName, final Object configuration) {
        Validator.validateString(path, "Path");
        final Data data = getFileData().getDefaultsData().retrieve(path);
        addConfiguration(data, callName, configuration);
    }

    public void addConfiguration(final String callName, final Object configuration) {
        addConfiguration(currentData(), callName, configuration);
    }

    public Object getConfiguration(final Data data, final String callName) {
        return data.getConfiguration(callName);
    }

    public Object getConfiguration(final String path, final String callName) {
        Validator.validateString(path, "Path");
        return getConfiguration(getFileData().getDefaultsData().retrieve(path), callName);
    }

    public Object getConfiguration(final String callName) {
        return getConfiguration(currentData(), callName);
    }
    //</editor-fold>

    //<editor-fold desc="DataType">
    public Class<?> getDataType(final Data data) {
        Validator.validateObject(data, "Data");
        return data.getDataType();
    }

    public Class<?> getDataType(final String path) {
        Validator.validateString(path, "Path");
        return getDataType(getFileData().getDefaultsData().retrieve(path));
    }

    public Class<?> getDataType() {
        return getDataType(currentData());
    }
    //</editor-fold>

    public void reset() {
        deselectCurrentData();
    }
}
