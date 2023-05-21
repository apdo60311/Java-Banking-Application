package Shared;

public class Validation {
        // validate id (if id doesn't contain only digits)
        public static boolean validateId(String id) {
            try {
                Integer.parseInt(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        // validate amount 
        public static boolean validateAmount(String amount) {
            try {
                int parsedAmount = Integer.parseInt(amount);
                if (parsedAmount > 0) {
                    return true;
                }
                else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        // validate username
        public static boolean validateUsername(String username) {
            if (username.isBlank() || username.length() < 4 || username.contains(" ")) {
                return false;
            }else {
                return true;
            }
        }
        public static PasswordStrength validatePassword(String password) {
            boolean containPunc = password.matches(".*[\\p{Punct}].*");
            boolean containNumbers = password.matches(".*\\d.*");
            boolean containUppercase = password.matches(".*[A-Z].*");
    
            if (!password.isEmpty()) {
                if (password.length() > 4) {
                    if (containPunc && containNumbers && containUppercase) {
                        return PasswordStrength.STRONG;
                    } else if(containNumbers && containUppercase){
                        return PasswordStrength.AVERAGE;
                    }else {
                        return PasswordStrength.TOOWEAK;
                    }                    
                } else {
                    return PasswordStrength.INVALID;
                }
        
            }else {
                return PasswordStrength.EMPTY;
            }
        }
    
}
