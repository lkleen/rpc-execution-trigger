package de.steinberg.rpc.execution.trigger.master.network;

import de.steinberg.rpc.execution.trigger.core.exception.RpcExecutionTriggerException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

/**
 * Created by lkleen on 23.11.2016.
 */
public class NetworkInterfacesInfo {

    public static String create() {
        try {
            String interfaces = "";
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                interfaces += "----------------------------------------\n";
                interfaces += createInterfaceString (networkInterface);
            }
            return interfaces;
        } catch (Exception e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    private static String createInterfaceString(NetworkInterface networkInterface) {
        String str = networkInterface.getDisplayName() + "\n";
        for (InetAddress address : Collections.list(networkInterface.getInetAddresses())) {
            str += address.getHostName() + ": " + address.getHostAddress() + "\n";
        }
        return str;
    }

}
