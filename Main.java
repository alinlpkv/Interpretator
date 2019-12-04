import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan= new Scanner(System.in);
        Calc c = new Calc();
        while (true)
            c.compile(scan.nextLine().toCharArray());

    }
}