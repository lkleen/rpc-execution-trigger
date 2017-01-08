package org.larsworks.trading.data.collector.engine.action;

import de.steinberg.engine.core.engine.action.AbstractAction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:44
 */
@Slf4j
public class CollectDataAction extends AbstractAction {
    @Override
    public void execute() {
        log.info("triggered data collection");
    }
}
