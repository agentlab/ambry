package com.github.ambry.server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		String[] args = {
				"--serverPropsFilePath", "../config/server.properties",
				"--hardwareLayoutFilePath", "../config/HardwareLayout.json",
				"--partitionLayoutFilePath", "../config/PartitionLayout.json"
		};
		AmbryMain.main(args);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		
	}

}
