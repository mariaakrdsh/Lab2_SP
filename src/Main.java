import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter your filename");
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();

        FiniteAutomation automation = new FiniteAutomation(file);

        System.out.println("Enter length");
        int len = scanner.nextInt();
        if(automation.CheckLen(len))
            System.out.println("The automation accepts all words of len " + len);
        else
            System.out.println("The automation doesn't accept all words of len " + len);
    }
}