import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("**** To quit programme enter q ****");
            System.out.println("Please enter Black hand:");
            String blackHands = sc.nextLine();
            if(blackHands.equalsIgnoreCase("q")) break;
            System.out.println("Please enter White hand:");
            String whiteHands = sc.nextLine();
            if(whiteHands.equalsIgnoreCase("q")) break;
            Hand blackHand = new Hand(blackHands,"Black");
            Hand whiteHand = new Hand(whiteHands,"White");
            String result = new Hand().getWinner(blackHand, whiteHand);
            if (result.equals("Tie.")) System.out.println(result);
            else System.out.println(result+" wins.");
            System.out.println();
        }
    }
}
