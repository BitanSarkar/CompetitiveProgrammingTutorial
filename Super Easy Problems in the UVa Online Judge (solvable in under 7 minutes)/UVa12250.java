import java.util.Scanner;

public class UVa12250 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        String res = "";
        int ctr = 0;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            if(line.contains("#")) break;
            switch (line.trim().toUpperCase()) {
                case "HELLO":
                    res = res + "Case "+ ++ctr +": ENGLISH\n";
                    break;
                case "HOLA":
                    res = res + "Case "+ ++ctr +": SPANISH\n";
                    break;
                case "HALLO":
                    res = res + "Case "+ ++ctr +": GERMAN\n";
                    break;
                case "BONJOUR":
                    res = res + "Case "+ ++ctr +": FRENCH\n";
                    break;
                case "CIAO":
                    res = res + "Case "+ ++ctr +": ITALIAN\n";
                    break;
                case "ZDRAVSTVUJTE":
                    res = res + "Case "+ ++ctr +": RUSSIAN\n";
                    break;
                default:
                    res = res + "Case "+ ++ctr +": UNKNOWN\n";
                    break;
            }
        }
        sc.close();
        System.out.print(res);
    }
}
