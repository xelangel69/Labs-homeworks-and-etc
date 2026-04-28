package com.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public final class Deserializer {
    public static Object toObject (byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);

        ObjectInputStream ois = new ObjectInputStream(bais);

        return ois.readObject();
    }
}
