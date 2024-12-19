package com.example.demo.models;

import java.security.SecureRandom;

public class PasswordGenerator {

    // Définir un ensemble de caractères pour le mot de passe
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    // Taille du mot de passe
    private static final int PASSWORD_LENGTH = 12;

    // Méthode pour générer un mot de passe sécurisé
    public String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        SecureRandom random = new SecureRandom();

        // Ajouter une lettre majuscule
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));

        // Ajouter une lettre minuscule
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));

        // Ajouter un chiffre
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        // Ajouter un caractère spécial
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Compléter le reste du mot de passe avec des caractères aléatoires
        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Mélanger les caractères du mot de passe pour plus de sécurité
        return shuffleString(password.toString());
    }

    // Méthode pour mélanger le mot de passe généré
    private String shuffleString(String password) {
        SecureRandom random = new SecureRandom();
        char[] passwordArray = password.toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }
        return new String(passwordArray);
    }
}

