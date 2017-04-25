package wk.easyonboard.common.contracts;

/**
 * Created by Luca Welker on 4/25/17.
 */

import java.util.Set;

/**
 * Created by Luca Welker on 4/25/17.
 */
public abstract class Launchable {
    private Set<Class<?>> controllers;

    public Set<Class<?>> getControllers() {
        if (controllers == null)
            controllers = createControllers();

        return controllers;
    }

    protected abstract Set<Class<?>> createControllers();

    public abstract int getPortNr();
}
