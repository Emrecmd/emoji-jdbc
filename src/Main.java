import controller.PersonController;
import entity.Person;

import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PersonController controller = new PersonController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("ekleme -> 1");
        System.out.println("silme -> 2");
        System.out.println("güncelleme -> 3");
        System.out.println("listeleme -> 4");
        System.out.print("işlemi giriniz :");
        int islem = scanner.nextInt();

        if (islem == 1) {
            System.out.print("isim :");
            scanner.nextLine();
            String isim = scanner.nextLine();

            System.out.print("yaş :");
            int yas = scanner.nextInt();
            scanner.nextLine();

            System.out.print("emoji :");
            String emoji = scanner.nextLine();

            controller.addPerson(isim, yas, emoji);
            return;
        }
        if (islem == 2) {
            System.out.print("id :");
            int id = scanner.nextInt();

            controller.deletePerson(id);
            return;
        }
        if (islem == 3) {
            System.out.print("id :");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("isim :");
            String isim = scanner.nextLine();

            System.out.print("yaş :");
            int yas = scanner.nextInt();
            scanner.nextLine();

            System.out.print("emoji :");
            String emoji = scanner.nextLine();

            controller.updatePersons(id, isim, yas, emoji);
            return;
        }
        if (islem == 4) {
            for (Person p : controller.getAllPersons()) {
                System.out.println(p.getEmoji() + " - " + p.getAge() + " - " + p.getName());
            }
        }
        else {
            System.out.println("geçersiz işlem. çıkış yapılıyor...");
            return;
        }
    }
}