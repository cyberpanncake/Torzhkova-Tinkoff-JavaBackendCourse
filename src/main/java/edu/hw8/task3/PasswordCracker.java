package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;

public class PasswordCracker extends AbstractPasswordCracker {

    public PasswordCracker(Map<String, String> encryptedPasswords) {
        super(encryptedPasswords);
    }

    @Override
    public Map<String, String> crack() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        crackedPasswords = new HashMap<>();
        while (!encryptedPasswords.isEmpty()) {
            tryFindEncryptedPassword(passwordGenerator.next());
        }
        return crackedPasswords;
    }
}
