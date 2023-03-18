import java.util.Scanner;

public class UVa00272 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        boolean flag = true;
        String res = "";
        while(sc.hasNextLine()){
            line = sc.nextLine();
            for(int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if(ch == '\u001a') {
                    break;
                }
                if(ch=='\"') {
                    res = res + (flag?"``":"''");
                    flag=!flag;
                }
                else {
                    res = res + ch;
                }
            }
            res = res + "\n";
        }
        sc.close();
        System.out.print(res);
    }
}
