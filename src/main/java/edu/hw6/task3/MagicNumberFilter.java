package edu.hw6.task3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Arrays;

public class MagicNumberFilter implements AbstractPathFilter {
    private final String[] hexStrings;

    public MagicNumberFilter(int... args) {
        hexStrings = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            hexStrings[i] = Integer.toHexString(args[i]);
        }
    }

    @Override
    public boolean accept(Path path) throws IOException {
        int bufferLen = hexStrings.length;
        try (FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(bufferLen);
            int count = fileChannel.read(buffer);
            if (count == -1) {
                return false;
            }
            buffer.flip();
            String[] hexReaded = new String[bufferLen];
            for (int i = 0; i < bufferLen; i++) {
                hexReaded[i] = "%02x".formatted(buffer.get());
            }
            return Arrays.equals(hexReaded, hexStrings);
        }
    }
}
