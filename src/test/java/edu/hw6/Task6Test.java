package edu.hw6;

import java.util.List;

import edu.hw6.task6.PortInfo;
import edu.hw6.task6.PortBusyChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task6Test {

    @Test
    public void getBusyPortsTest() {
        List<PortInfo> busyPorts = PortBusyChecker.getBusyPorts();
        Assertions.assertFalse(busyPorts.isEmpty());
    }
}
