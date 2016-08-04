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
		String[] args = {
				"--serverPropsFilePath", "../config/frontend.properties",
				"--hardwareLayoutFilePath", "../config/HardwareLayout.json",
				"--partitionLayoutFilePath", "../config/PartitionLayout.json"
		};
		AmbryFrontendMain.main(args);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		AmbryFrontendActivator.context = null;
		
	}

}
