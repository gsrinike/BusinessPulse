package com.infosys.tool.business.pulse.task.businessprocess.application.server;

import com.infosys.tool.business.pulse.application.server.ServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerPropertyData;

public interface ServerPropertyLoader {

	ServerPropertyData load(ServerProperty serverProperty);
}
