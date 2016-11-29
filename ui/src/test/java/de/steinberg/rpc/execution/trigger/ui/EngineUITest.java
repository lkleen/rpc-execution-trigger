package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.DefaultEngine;
import de.steinberg.rpc.execution.trigger.core.engine.DefaultListener;
import de.steinberg.rpc.execution.trigger.core.engine.Listener;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by lkleen on 11/29/2016.
 */
public class EngineUITest extends Application {

    public static void main(String... args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Listener listener1 = new DefaultListener();
        listener1.addAction(new ActionA());
        listener1.addAction(new ActionB());

        Listener listener2 = new DefaultListener();
        listener2.addAction(new ActionA ());

        Listener listener3 = new DefaultListener();
        listener3.addAction(new ActionA ());

        Monitor monitor1 = new MonitorMock();
        monitor1.addListener(listener3);

        Monitor monitor2 = new MonitorMock();
        monitor2.addListener(listener1);
        monitor2.addListener(listener2);

        DefaultEngine engine = new DefaultEngine();
        engine.addMonitor(monitor1);
        engine.addMonitor(monitor2);

        EngineUI engineUI = new EngineUI();
        engineUI.initialize(engine, stage);
    }
}
