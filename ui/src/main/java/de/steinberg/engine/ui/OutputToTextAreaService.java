package de.steinberg.engine.ui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import lombok.Setter;

import javax.inject.Inject;

/**
 * Created by LKLeen on 29.12.2016.
 */
public class OutputToTextAreaService extends Service<Void> {

    @Setter
    TextArea output;

    @Inject
    OutputToTextAreaTask task;

    @Override
    protected Task<Void> createTask() {
        task.setOutput(output);
        return task;
    }
}
