import java.util.HashMap;
import java.util.Scanner;

public class UVa12577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        String res = "";
        int ctr = 0;
        while(sc.hasNextLine()){
            line = sc.nextLine().trim();
            if("*".equals(line)) break;
            res+="Case "+ ++ctr + ": " + (line.equalsIgnoreCase("Hajj")?"Hajj-e-Akbar":"Hajj-e-Asghar") + "\n";
        }
        sc.close();
        System.out.print(res);
    }
}
