package com.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class Serializer {
    public static byte[] toBytes(Object input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(input);
        oos.flush();

        return baos.toByteArray();
    }
}
