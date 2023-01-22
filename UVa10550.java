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
            b1=(b1-s+40)%40;
            b2=(b2-s+40)%40;
            b3=(b3-s+40)%40;
            int out = 720 + 360 + 9 * (40-b1 + (b2-b1+40)%40 +  (40-b3+b2+40)%40); 
            res = res + out + "\n";
        }while(!line.equals("0 0 0 0"));
        sc.close();
        System.out.print(res);
    }
}
