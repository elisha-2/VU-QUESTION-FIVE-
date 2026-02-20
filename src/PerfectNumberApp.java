import java.util.Scanner;

public class PerfectNumberApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = 0;

        try {
            System.out.print("Enter a positive integer: ");
            number = input.nextInt();

            if (number <= 0) {
                System.out.println("Please enter a positive integer.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        int sum = 0;

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        if (sum == number) {
            System.out.println(number + " is a Perfect Number.");
        } else {
            System.out.println(number + " is NOT a Perfect Number.");
        }
    }
}