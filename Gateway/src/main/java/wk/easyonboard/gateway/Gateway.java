package wk.easyonboard.gateway;

import wk.easyonboard.common.contracts.Launchable;
import wk.easyonboard.gateway.controller.EmployeeController;
import wk.easyonboard.gateway.controller.WorkflowController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Luca Welker on 4/25/17.
 */
public class Gateway extends Launchable {

    protected Set<Class<?>> createControllers() {
        Set<Class<?>> controllers = new HashSet<Class<?>>();
        controllers.add(WorkflowController.class);
        controllers.add(EmployeeController.class);
        return controllers;
    }

    public int getPortNr() {
        return 9080;
    }
}
