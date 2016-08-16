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
		String confDir = System.getProperty("configDir");
		final String[] args = {
				"--serverPropsFilePath", confDir + "/server.properties",
				"--hardwareLayoutFilePath", confDir + "/HardwareLayout.json",
				"--partitionLayoutFilePath", confDir + "/PartitionLayout.json"
		};
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				AmbryMain.main(args);
			}
		}).start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		
	}

}
