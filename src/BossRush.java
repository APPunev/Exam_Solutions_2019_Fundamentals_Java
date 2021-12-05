import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BossRush {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String regex = "\\|(?<name>[A-Z]{3,})\\|:#(?<title>[A-Za-z]+ [A-Za-z]+)#";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                System.out.printf("%s, The %s%n",matcher.group("name"), matcher.group("title"));
                System.out.println(">> Strength: " + matcher.group("name").length());
                System.out.println(">> Armor: " + matcher.group("title").length());
            }else{
                System.out.println("Access denied!");
            }


        }
    }
}
