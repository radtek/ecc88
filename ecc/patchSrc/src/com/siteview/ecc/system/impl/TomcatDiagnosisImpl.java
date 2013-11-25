package com.siteview.ecc.system.impl;

import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.zkoss.util.resource.Labels;

import com.siteview.ecc.system.Diagnosis;

public class TomcatDiagnosisImpl extends Diagnosis {
	private final static Map<String, String> map = new LinkedMap();
	
	public TomcatDiagnosisImpl(){
		if (map.size()==0){
			map.put("os.name",Labels.getLabel("NameOfOperatingSystem") );
			map.put("sun.os.patch.level",Labels.getLabel("OperatingSystemVersion"));
			map.put("java.version",Labels.getLabel("VersionOfJavaRuntimeEnvironment"));
			map.put("java.home",Labels.getLabel("JavaInstallationDirectory") );
			map.put("java.class.path",Labels.getLabel("JavaClassPath") );
			map.put("java.library.path",Labels.getLabel("PathListLibraryWhenSearchLoad"));
			map.put("java.io.tmpdir",Labels.getLabel("TemporaryFileDefaultPath") );
			map.put("user.name",Labels.getLabel("UserAccountName"));
			map.put("user.home",Labels.getLabel("User'sHomeDirectory"));
			map.put("user.dir",Labels.getLabel("CurrentWorkingDirectoryOfUser"));
		}
	}
	

	@Override
	public String getDescription() {
		return Labels.getLabel("WebEccHttpServerDetectionInfo");
	}

	@Override
	public String getName() {
		return Labels.getLabel("WebEccHttpServerDetection");
	}

	@Override
	public void execute() throws Exception {
		for (String key : map.keySet()){
			String value = System.getProperty(key);
			getResultList().add(map.get(key) + " : " + value);
		}


		long totalMemory = Runtime.getRuntime().totalMemory();
		long maxMemory = Runtime.getRuntime().maxMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usedMemory = totalMemory - freeMemory;

		getResultList().add(Labels.getLabel("AssignedTomcatMemory:") + totalMemory / 1024 / 1024 + "M");
		getResultList().add(Labels.getLabel("TomcatCanUseMemory:") + maxMemory / 1024 / 1024 + "M");
		getResultList().add(Labels.getLabel("TomcatUsedMemory:") + usedMemory / 1024 / 1024 + "M");
		getResultList().add(Labels.getLabel("TomcatRemainingMemory:") + freeMemory / 1024 / 1024 + "M");
		
		if (OS.isWinNT() == false)
			throw new Exception(Labels.getLabel("SystemWindowsNTPlatformUsing"));
		if ((maxMemory / 1024 / 1024) < 500)
			throw new Exception(Labels.getLabel("TomcatMemoryRecommendedMore500M"));
		if ((usedMemory / 1024 / 1024) > 128)
			throw new Exception(Labels.getLabel("TomcatUsedMuchMemory"));

	}

}
