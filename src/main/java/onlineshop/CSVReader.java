package onlineshop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import onlineshop.merchandise.Book;

public class CSVReader {

    public static List<Book> readBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Book book = new Book();
                book.setTitle(columns[0].replace("\"", ""));
                book.setDescription(columns[1].replace("\"", ""));
                book.setAuthor(columns[2].replace("\"", ""));
                book.setImage(columns[3]);
                book.setPages(Integer.parseInt(columns[4]));
                book.setPrice(Double.parseDouble(columns[5].replace(",", ".")));
                book.setCategory(columns[6].replace("\"", ""));
                book.setDiscount(Integer.parseInt(columns[7].replace(",", ".")));

                books.add(book);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return books;
    }
}
