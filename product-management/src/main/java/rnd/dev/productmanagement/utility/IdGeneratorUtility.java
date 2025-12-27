package rnd.dev.productmanagement.utility;

import java.util.UUID;

public class IdGeneratorUtility {

    private IdGeneratorUtility() {

    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
