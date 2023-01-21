import java.util.Scanner;

public class UVa10550 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        String res = "";
        do {
            line = sc.nextLine();
            if(line.equals("0 0 0 0")) break;
            String[] ips = line.split("\\s+");
            int s = Integer.parseInt(ips[0]);
            int b1 = Integer.parseInt(ips[1]);
            int b2 = Integer.parseInt(ips[2]);
            int b3 = Integer.parseInt(ips[3]);
            b1-=s;
            b2-=s;
            b3-=s;
            int out = 720 + 360 + 9 * (b1 + Math.abs(b2-b1) + Math.abs(b3-b2)); 
            res = res + out + "\n";
        }while(!line.equals("0 0 0 0"));
        sc.close();
        System.out.print(res);
    }
}
