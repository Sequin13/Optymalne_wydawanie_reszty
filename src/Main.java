import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> kasa = new HashMap<>();
        kasa.put("5 zł", 1);
        kasa.put("2 zł", 3);
        kasa.put("1 zł", 5);
        kasa.put("50 gr", 10);
        kasa.put("20 gr", 20);
        kasa.put("10 gr", 200);
        kasa.put("5 gr", 100);
        kasa.put("2 gr", 100);
        kasa.put("1 gr", 10000);

        Scanner scan = new Scanner(System.in);

        double reszta = -1;
        while (reszta != 0) {
            try {
                System.out.println("Podaj resztę do wydania (wpisz 0, aby zakończyć):");
                reszta = scan.nextDouble();
                if (reszta != 0) {
                    WydajReszte(reszta, kasa);
                }
            } catch (Exception e) {
                reszta = -1;
                System.out.println("Niepoprawny format liczby, spróbuj ponownie.");
                scan.nextLine();
            }
        }
    }
    public static void WydajReszte (double reszta, Map<String, Integer> kasa) {
        int[] wartoscWGroszach = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        String[] monety = {"5 zł", "2 zł", "1 zł", "50 gr", "20 gr", "10 gr", "5 gr", "2 gr", "1 gr"};

            double resztaWGroszachDouble = reszta * 100;
            int resztaWGroszach = (int) resztaWGroszachDouble;

            if (resztaWGroszach > 0) {
                for (int i = 0; i < wartoscWGroszach.length; i++) {
                    int iloscMonet = resztaWGroszach / wartoscWGroszach[i];
                    if (iloscMonet > 0) {
                        int iloscMozliwychDoWydania = Math.min(iloscMonet, kasa.get(monety[i]));
                        if (iloscMozliwychDoWydania > 0) {
                            System.out.println("Wydaj " + iloscMozliwychDoWydania + " monet " + monety[i]);
                            resztaWGroszach -= iloscMozliwychDoWydania * wartoscWGroszach[i];
                            kasa.put(monety[i], kasa.get(monety[i]) - iloscMozliwychDoWydania);
                        }
                    }
                }
            } else {
                System.out.println("Reszta nie może być mniejsza od 0");
            }
    }
}