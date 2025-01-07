public class ValidationUtil {
    public static void validateUserRegistration(UserCredentials user) {
        if (user == null) {
            throw new ValidationException("User cannot be null");
        }
        
        // Complex validation rules
        if (user.getUsername().length() < 3) {
            throw new ValidationException("Username too short");
        }
        
        if (!isValidEmail(user.getEmail())) {
            throw new ValidationException("Invalid email format");
        }
    }

    private static boolean isValidEmail(String email) {
        // Implement email validation logic
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
