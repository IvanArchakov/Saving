import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(10, 5, 1, 13.1);
        GameProgress player2 = new GameProgress(20, 6, 2, 14.2);
        GameProgress player3 = new GameProgress(30, 7, 3, 15.3);
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);

        saveGame("C://Java//Games//savegames//save1.dat", player1);
        saveGame("C://Java//Games//savegames//save2.dat", player2);
        saveGame("C://Java//Games//savegames//save3.dat", player3);

        ArrayList<String> list = new ArrayList<>();
        list.add("C://Java//Games//savegames//save1.dat");
        list.add("C://Java//Games//savegames//save2.dat");
        list.add("C://Java//Games//savegames//save3.dat");

        zipFiles("C://Java//Games//savegames//zip1.zip", list);

        for (String ways : list) {
            File saveDat = new File(ways);
            if (saveDat.delete()) {
                System.out.println("Файл " + saveDat.getName() + " удален");
            } else {
                System.out.println("Файл " + saveDat.getName() + " не удален");
            }
        }
    }

    public static void saveGame(String way, GameProgress player) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String way, ArrayList<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way))) {
            for (String arr : list) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry zipEntry = new ZipEntry(arr);
                    zout.putNextEntry(zipEntry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
