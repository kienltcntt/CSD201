/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.*;
import LinkedList.Book_List;
import LinkedList.Reader_List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Kien
 */
public class MenuManager {

    private static final String BOOK_URL = "book.txt";

    public static int menu() {
        System.out.printf("%-3s%-10s", "", "---MAIN MENU---\n");
        System.out.println("1. Book list");
        System.out.println("2. Reader list");
        System.out.println("3. Lending list");
        System.out.println("0. Exit program");

        int choise = Validation.checkInputInt(0, 3);
        return choise;
    }

    //Bus
    //Load data from file
    public static void loadBookFromFile(Book_List mlb) {
        //System.out.println("Enter a file to load data:");
        //String url = Validation.checkInputString();
        try {
//            FileReader fr = new FileReader(BUS_URL);
//            BufferedReader br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] detailBus = line.split("[|]");
//                mlb.addLast(new Bus(detailBus[0].trim(), detailBus[1].trim(), Integer.parseInt(detailBus[2].trim()),
//                        Integer.parseInt(detailBus[3].trim()), Double.parseDouble(detailBus[4].trim()),
//                        Double.parseDouble(detailBus[5].trim())));
//            }
//            System.out.println("Load successful!");
//            fr.close();
//            br.close();
            FileReader fr = new FileReader(BOOK_URL);
            BufferedReader br = new BufferedReader(fr);
            String s;
            String[] a;
            Book x;
            String xCode, xTitle;
            int xQuantity, xLended;
            double xPrice;
            while (true) {
                s = br.readLine();
                if (s == null || s.trim().length() < 3) {
                    break;
                }
                a = s.split("[|]");
                xCode = a[0].trim();
                xTitle = a[1].trim();
                xQuantity = Integer.parseInt(a[2].trim());
                xLended = 0;
                xPrice = Double.parseDouble(a[3].trim());
                x = new Book(xCode, xTitle, xQuantity, xLended, xPrice);
                mlb.addLast(x);
            }
            fr.close();
            br.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //Input & add to the head
    public static void addToEnd(Book_List mlb) {
        while (true) {
            System.out.println("Enter bCode");
            String bCode = Validation.checkInputString();
            if (!Validation.checkCodeBook(mlb, bCode)) {
                System.out.println("Duplicates code");
                continue;
            }
            System.out.println("Enter book title:");
            String title = Validation.checkInputString();
            System.out.println("Enter number of quantity:");
            int quantity = Validation.checkInputInt(0, Integer.MAX_VALUE);
            System.out.println("Enter number of lended:");
            int lended = Validation.checkInputInt(0, Integer.MAX_VALUE);
            System.out.println("Enter price of book:");
            double price = Validation.checkInputDouble(0, Double.MAX_VALUE);
            System.out.println("Enter  arrival time:");
            Book b = new Book(bCode, title, quantity, lended, price);
            mlb.addLast(b);
            System.out.println("Add successful!");
            return;
        }
    }

    //Display data
    public static void displayBook(Book_List mlb) {
        System.out.println("List Bus:");
        mlb.traverse();
    }

    //Save bus list to file
    public static void saveToFile(Book_List mlb) {
        System.out.println("");
        try {
            FileWriter fw = new FileWriter(BOOK_URL);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < mlb.size(); i++) {
                Book b = mlb.get(i);
                pw.printf("%s|%s|%d|%d|%f|%f\n", b.getBcode(), b.getTitle(),
                        b.getQuantity(), b.getLended(), b.getPrice());
            }
            pw.close();
            fw.close();

        } catch (IOException e) {
        }
    }

    //Search by bcode
    public static void searchByBCode(Book_List mlb) {
        System.out.println("Enter a code to search");
        String bCode = Validation.checkInputString();
        boolean check = false;
        for (int i = 0; i < mlb.size(); i++) {
            if (mlb.get(i).getBcode().equalsIgnoreCase(bCode)) {
                System.out.println(mlb.get(i));
                check = true;
            }
        }
        if (!check) {
            System.out.println("Id not found");
        }
    }

    //Delete by bcode
    public static void deleteByBCode(Book_List mlb) {
        System.out.println("Enter a code to delete");
        String bCode = Validation.checkInputString();
        boolean check = false;
        for (int i = 0; i < mlb.size(); i++) {
            if (mlb.get(i).getBcode().equalsIgnoreCase(bCode)) {
                mlb.deleteAt(i);
                check = true;
            }
        }
        if (!check) {
            System.out.println("Id not found");
        } else {
            System.out.println("Delete successful.");
        }
    }

    //Sort by bcode
    public static void sortByBCode(Book_List mlb) {
        mlb.sortByBCode();
        System.out.println("List after sort:");
        displayBook(mlb);
    }

    //Add before position  k
    public static void addBeforePosition(Book_List mlb) {
        System.out.println("Enter a position to add:");
        int k = Validation.checkInputInt(0, mlb.size());
        while (true) {
            String bCode = Validation.checkInputString();
            if (!Validation.checkCodeBook(mlb, bCode)) {
                System.out.println("Duplicates code");
                continue;
            }
            String title = Validation.checkInputString();
            int quantity = Validation.checkInputInt(0, Integer.MAX_VALUE);
            int lended = Validation.checkInputInt(0, Integer.MAX_VALUE);
            double price = Validation.checkInputDouble(0, Double.MAX_VALUE);
            Book b = new Book(bCode, title, quantity, lended, price);
            mlb.insertBefore(k, b);
            break;
        }
    }

    //delete before position k
    public static void deleteBeforeBCode(Book_List mlb) {
        System.out.println("Enter a b code :");
        String bCode = Validation.checkInputString();
        boolean check = false;
        for (int i = 0; i < mlb.size(); i++) {
            if (mlb.get(i).getBcode().equalsIgnoreCase(bCode)) {
                mlb.deleteAt(i - 1);
                check = true;
            }
        }
        if (!check) {
            System.out.println("B code not found.");
        }
    }

    //Customer
    //Load data from file
    public static void loadReaderFromFile(Reader_List mlc) {
        System.out.println("Enter a file to load data:");
        String url = Validation.checkInputString();
        try {
            File f = new File(url);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] detailReader = line.split(":");
//                mlc.addLast(new Reader(detailReader[0], detailReader[1], detailReader[2]));
            }
            System.out.println("Load successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //add to end
    public static void addToEnd(Reader_List mcl) {
        while (true) {
            System.out.println("Enter reader code:");
            String rcode = Validation.checkInputString();
            if (!Validation.checkCodeReader(mcl, rcode)) {
                System.out.println("Duplicate code.");
                continue;
            }
            System.out.println("Enter reader name:");
            String name = Validation.checkInputString();
            System.out.println("Enter year:");
            int byear = Validation.checkYear();
            Reader c = new Reader(rcode, name, byear);
            mcl.addLast(c);
            System.out.println("Add successful!");
        }
    }

    //display data
    public static void displayReader(Reader_List mcl) {
        System.out.println("List Reader:");
        mcl.traverse();
    }
    //save to file

    //search by c code
    public static void searchByRCode(Reader_List mcl) {
        System.out.println("Enter a code to search");
        String rCode = Validation.checkInputString();
        for (int i = 0; i < mcl.size(); i++) {
            if (mcl.get(i).getRcode().equalsIgnoreCase(rCode)) {
                System.out.println(mcl.get(i));
            }
        }
    }

    //Delete by ccode
    public static void deleteByRCode(Reader_List mcl) {
        System.out.println("Enter a code to delete");
        String rCode = Validation.checkInputString();
        for (int i = 0; i < mcl.size(); i++) {
            if (mcl.get(i).getRcode().equalsIgnoreCase(rCode)) {
                mcl.deleteAt(i);
            }
        }
    }

//    public static void f2(Reader_List mlb) {
//        for (int i = 0; i < mlb.size(); i++) {
//            if (mlb.get(i).getBcode().equalsIgnoreCase("2")) {
//                mlb.get(i).setSeat(9);
//            }
//        }
//    }
//
//    public static void f3(Bus_List mlb) {
//        for (int i = 0; i < mlb.size(); i++) {
//            if (mlb.get(i).getBcode().equalsIgnoreCase("3")) {
//                mlb.deleteAt(i);
//            }
//        }
//    }
//
//    public static void f4(Bus_List mlb) {
//        mlb.addLast(new Bus("9", "E", 3, 1, 1, 8));
//    }
//
//    public static void f5(Bus_List mlb) {
//        mlb.sortBySeat();
//    }
}
