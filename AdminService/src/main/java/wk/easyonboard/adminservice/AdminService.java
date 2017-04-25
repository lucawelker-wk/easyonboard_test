package wk.easyonboard.adminservice;

import wk.easyonboard.adminservice.controller.EmployeeController;
import wk.easyonboard.adminservice.controller.WorkflowController;
import wk.easyonboard.common.contracts.Launchable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Luca Welker on 4/25/17.
 */
public class AdminService extends Launchable {

    protected Set<Class<?>> createControllers() {
        Set<Class<?>> controllers = new HashSet<Class<?>>();
        controllers.add(WorkflowController.class);
        controllers.add(EmployeeController.class);

        return controllers;
    }

    public int getPortNr() {
        return 9081;
    }
}

