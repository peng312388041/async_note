package com.bbs.kingdom.voltdb;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ProcedureCallback;

import com.bbs.kingdom.constants.BbsConstants;

public class VoltdbClient {
	private Client client;
	private String hostIp;
	private Integer port;
	private ClientConfig clientConfig;
	private static Logger logger = LogManager.getLogger();

	private void init(String hostIp, Integer port, String username, String password) {
		this.hostIp = hostIp;
		this.port = port;
		clientConfig = new ClientConfig(username, password);
		clientConfig.setTopologyChangeAware(true);
		client = ClientFactory.createClient(clientConfig);
		try {
			client.createConnection(hostIp, port);
		} catch (IOException e) {
			logger.error(e.getCause());
		}

	}

	public VoltdbClient(String hostIp) {
		init(hostIp, BbsConstants.VOLTDB_DEFAULT_PORT, null, null);
	}

	public VoltdbClient(String hostIp, String username, String password) {
		init(hostIp, BbsConstants.VOLTDB_DEFAULT_PORT, username, password);
	}

	public VoltdbClient(String hostIp, Integer port) {
		init(hostIp, port, null, null);
	}

	public VoltdbClient(String hostIp, Integer port, String username, String password) {
		init(hostIp, port, username, password);
	}

	public boolean callProcedure(ProcedureCallback callback, String procName, Object... parameters) {
		// rebuild client
		if (client == null || client.getConnectedHostList().isEmpty()) {
			client = ClientFactory.createClient(clientConfig);
			try {
				client.createConnection(hostIp, port);
				// client.createConnection(hostIp);
			} catch (IOException e) {
				logger.error(e.getCause());
			}
		}
		try {
			return client.callProcedure(callback, procName, parameters);
		} catch (IOException e) {
			logger.error(e.getCause());
			return false;
		}
	}
}
