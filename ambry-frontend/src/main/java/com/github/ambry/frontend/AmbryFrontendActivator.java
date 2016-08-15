package com.github.ambry.frontend;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AmbryFrontendActivator implements BundleActivator {
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		AmbryFrontendActivator.context = context;
		String confDir = System.getProperty("configDir");
		final String[] args = {
				"--serverPropsFilePath", confDir + "/frontend.properties",
				"--hardwareLayoutFilePath", confDir + "/HardwareLayout.json",
				"--partitionLayoutFilePath", confDir + "/PartitionLayout.json"
		};
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				AmbryFrontendMain.main(args);
			}
		}).start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		AmbryFrontendActivator.context = null;
		
	}

}
