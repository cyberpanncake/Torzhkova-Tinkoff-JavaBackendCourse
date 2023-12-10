package edu.hw6.task6;

public record PortInfo(Protocol protocol, int port, String info) {
    private static final int PROTOCOL_LEN = 5;
    private static final int PORT_LEN = 9;

    @Override
    public String toString() {
        return ("%-" + PROTOCOL_LEN + "s%-" + PORT_LEN + "s%s").formatted(protocol.name(), port, info);
    }
}


