import java.util.Arrays;
import java.util.Scanner;

public class UVa12279 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        String res = "";
        int ctr = 0;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            if("0".equals(line.trim())) break;
            int[] z = {0};
            int[] nz = {0};
            Arrays.stream(sc.nextLine().trim().split("\\s+"))
            .forEach(n -> {
                if(Long.parseLong(n)==0l){
                    z[0]++;
                }
                else {
                    nz[0]++;
                }
            });
            res+="Case "+ ++ctr + ": " + (nz[0]-z[0]) + "\n";
        }
        sc.close();
        System.out.print(res);
    }
}
