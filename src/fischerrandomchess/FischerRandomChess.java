/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fischerrandomchess;

import java.util.Scanner;


/**
 *
 * @author zhuan
 */
public class FischerRandomChess {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i = 0; i < 10; i++) {
            String line = sc.nextLine();
            
            int d=getDuplications(line);
            int possibility=getPossibilities(line);
            System.out.println(possibility/d);
        }
    }

    private static int getPossibilities(String line) {
        int sum = 0;
        char p = getUnarranged(line);
        if (p == 0) {
            return 1;
        }
        for (int i = 0; i < 8; i++) {
            if (line.charAt(i) == '_') {
                if (isValid(p, i, line)) {
                    String newLine = line.substring(0, i) + p + line.substring(i + 1);
                    sum += getPossibilities(newLine);
                }
            }
        }

        return sum;
    }

    private static boolean isValid(Character p, int index, String line) {
        if (line.charAt(index) != '_') {
            return false;
        }
        switch (p) {
            case 'K':
                int[] pos = new int[2];
                int j = 0;
                for (int i = 0; i < 8; i++) {
                    if (line.charAt(i) == 'R') {
                        pos[j] = i;
                        j++;
                    }
                }
                if (j > 2) {
                    return false;
                }
                if (j == 2) {
                    if (index < pos[0] && index < pos[1]) {
                        return false;
                    } else if (index > pos[0] && index > pos[1]) {
                        return false;
                    }
                }
                break;
            case 'B':
                for (int i = 0; i < 8; i++) {
                    if (line.charAt(i) == 'B') {
                        if ((index - i + 8) % 2 == 0) {
                            return false;
                        }
                    }
                }
                break;
            case 'N':
                break;
            case 'R':
                int p1 = -1;
                int p2 = -1;
                for (int i = 0; i < 8; i++) {
                    if (line.charAt(i) == 'K') {
                        p1 = i;
                    } else if (line.charAt(i) == 'R') {
                        p2 = i;
                    }
                }
                if (p1 != -1 && p2 != -1) {
                    if (p1 > index && p1 > p2) {
                        return false;
                    }
                    if (p1 < index && p1 < p2) {
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    private static char getUnarranged(String line) {
        int bs=2;
        int rs=2;
        int ns=2;
        int ks=1;
        int qs=1;
        for (int i=0;i<line.length();i++)
        {
            char c=line.charAt(i);
            switch (c) {
                case 'B':
                    bs--;
                    break;
                case 'N':
                    ns--;
                    break;
                case 'R':
                    rs--;
                    break;
                case 'K':
                    ks--;
                    break;
                case 'Q':
                    qs--;
                    break;
                default:
                    break;
            }
        }
        char[] unarranged = {'K', 'B', 'B', 'N', 'N', 'R', 'R', 'Q'};
        
        for (int i=0;i<unarranged.length;i++) {
            switch (unarranged[i]) {
                case 'K':
                    if (ks!=0) return 'K';
                    break;
                case 'Q':
                    if (qs!=0) return 'Q';
                    break;
                case 'B':
                    if (bs!=0) return 'B';
                    break;
                case 'R':
                    if (rs!=0) return 'R';
                    break;
                case 'N':
                    if (ns!=0) return 'N';
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    private static int getDuplications(String line) {
        int bs=2;
        int rs=2;
        int ns=2;
        for (int i=0;i<line.length();i++)
        {
            char c=line.charAt(i);
            switch (c) {
                case 'B':
                    bs--;
                    break;
                case 'N':
                    ns--;
                    break;
                case 'R':
                    rs--;
                    break;
                default:
                    break;
            }
        }
        if (bs==0) bs=1;
        if (ns==0) ns=1;
        if (rs==0) rs=1;
        return (bs*ns*rs);
    }
}
