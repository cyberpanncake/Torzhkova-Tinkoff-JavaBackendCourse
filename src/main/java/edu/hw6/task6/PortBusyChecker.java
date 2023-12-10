package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PortBusyChecker {
    private static final Map<Integer, String> PORTS = Map.of(
        21, "FTP (File Transfer Protocol)",
        22, "SSH (Secure Shell)",
        25, "SMTP (Simple Mail Transfer Protocol)",
        53, "DNS (Domain Name System)",
        80, "HTTP (HyperText Transfer Protocol)",
        445, "Microsoft-DS Active Directory",
        3306, "MySQL Database",
        3389, "Remote Desktop Protocol (RDP)",
        5432, "PostgreSQL Database",
        27017, "MongoDB Database"
    );
    private static final int MAX_PORT_COUNT = 49151;

    private PortBusyChecker() {
    }

    public static List<PortInfo> getBusyPorts() {
        List<PortInfo> portInfos = new ArrayList<>();
        for (int i = 0; i < MAX_PORT_COUNT; i++) {
            if (isUdpBusy(i)) {
                portInfos.add(new PortInfo(Protocol.UDP, i, PORTS.getOrDefault(i, "")));
            }
            if (isTcpBusy(i)) {
                portInfos.add(new PortInfo(Protocol.TCP, i, PORTS.getOrDefault(i, "")));
            }
        }
        return portInfos;
    }

    private static boolean isUdpBusy(int port) {
        try (DatagramSocket ignored = new DatagramSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private static boolean isTcpBusy(int port) {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public static String toString(List<PortInfo> ports) {
        return ports.stream()
            .map(PortInfo::toString)
            .collect(Collectors.joining("\n"));
    }
}
