package de.steinberg.engine.core.engine.status;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by lars on 26.01.2017.
 */
public interface StatusAware {

    SimpleObjectProperty<Status> getStatusProperty();

}
