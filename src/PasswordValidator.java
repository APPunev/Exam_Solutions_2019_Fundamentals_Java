import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder password = new StringBuilder(scanner.nextLine());

        String command = scanner.nextLine();
        while(!command.equals("Complete")){
            String[] token = command.split("\\s+");
            String action = token[0];

            switch (action){
                case "Make":
                    int changeIndex = Integer.parseInt(token[2]);
                    String caseType = token[1];
                    if (caseType.equals("Upper")) {
                        char c = password.charAt(changeIndex);
                        c = Character.toUpperCase(c);
                        password.setCharAt(changeIndex,c);
                    }else{
                        char c = password.charAt(changeIndex);
                        c = Character.toLowerCase(c);
                        password.setCharAt(changeIndex,c);
                    }
                    System.out.println(password);
                    break;
                case "Insert":
                    int indexInsert = Integer.parseInt(token[1]);
                    char c = token[2].charAt(0);
                    if (indexInsert >= 0 && indexInsert <= password.length() - 1) {
                        password.insert(indexInsert,c);
                    }
                    System.out.println(password);
                    break;
                case "Replace":
                    char current = token[1].charAt(0);
                    int replaceValue = Integer.parseInt(token[2]);
                    if (password.indexOf(String.valueOf(current)) != -1) {
                        int calcCharValue = current + replaceValue;
                        char replaceChar = (char) calcCharValue;
                        while(password.toString().contains(String.valueOf(current))) {
                            String text = password.toString().replace(String.valueOf(current), String.valueOf(replaceChar));
                            password = new StringBuilder(text);
                        }
                    }
                    System.out.println(password);
                    break;
                case "Validation":
                    if (password.length() < 8) {
                        System.out.println("Password must be at least 8 characters long!");
                    }
                    if (!content(password)) {
                        System.out.println("Password must consist only of letters, digits and _!");
                    }
                    if (!upperCase(password)) {
                        System.out.println("Password must consist at least one uppercase letter!");
                    }
                    if (!lowerCase(password)) {
                        System.out.println("Password must consist at least one lowercase letter!");
                    }
                    if (!digitContent(password)) {
                        System.out.println("Password must consist at least one digit!");
                    }
                    break;

            }
            command = scanner.nextLine();
        }
    }

    private static boolean digitContent(StringBuilder password) {
        String pass = password.toString();
        boolean hasDigit = false;
        for (char c:pass.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        if (hasDigit) {
            return true;
        }else{
            return false;
        }
    }

    private static boolean lowerCase(StringBuilder password) {
        String pass = password.toString();
        boolean hasLowerCase = false;
        for (char c:pass.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
        }
        if (hasLowerCase) {
            return true;
        }else{
            return false;
        }
    }

    private static boolean upperCase(StringBuilder password) {
        String pass = password.toString();
        boolean hasUpperCase = false;
        for (char c:pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
        }
        if (hasUpperCase) {
            return true;
        }else{
            return false;
        }
    }

    private static boolean content(StringBuilder password) {
        String pass = password.toString();
        boolean charIsValid = false;
        for (char c:pass.toCharArray()) {
            if (c == '_' || Character.isLetterOrDigit(c)) {
                charIsValid = true;
            }else{
                return false;
            }
        }
        return true;
    }
}
