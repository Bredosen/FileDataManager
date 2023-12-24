
        package bredo.cmd.mc.filedatamanager.interfaces;

import bredo.cmd.mc.filedatamanager.utilities.handlers.DataSetter;

/**
 * Interface representing the default data settings for a component in a Minecraft command system.
 * <p>
 * This interface provides a contract for implementing classes to define their default data settings
 * using the {@link DataSetter} utility. It's primarily used in the context of Minecraft command
 * systems to standardize the way data is set across different implementations.
 * </p>
 *
 * @see DataSetter
 */
public interface IDefaultsData {

    /**
     * Sets default data using the provided {@link DataSetter}.
     * <p>
     * Implementing classes should use this method to define their default data settings. The method
     * is intended to be implemented in such a way that it initializes or resets data to its default
     * state. This is crucial in scenarios where data consistency and initial setup are important, 
     * such as in a Minecraft command system where various components require standardized initial data.
     * </p>
     *
     * @param dataSetter The {@code DataSetter} to be used for setting default data.
     *                   Must not be {@code null}.
     * @see DataSetter
     */
    void defaultDataSetter(final DataSetter dataSetter);
}