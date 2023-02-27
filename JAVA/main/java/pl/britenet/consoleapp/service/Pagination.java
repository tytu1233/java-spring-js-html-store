package pl.britenet.consoleapp.service;

import static pl.britenet.consoleapp.Constants.ON;
import java.util.*;

public class Pagination<T> {

    private int amount = 5;
    private int page = 1;


    private final Collection<T> paginationList;

    public Pagination(Collection<T> paginationList) {
        this.paginationList = paginationList;
    }



    public List<T> getRecords(String command) {

            switch (command) {
                case "exit" -> {
                    ON = false;
                    return new ArrayList<>();
                }
                case "++" -> setAmount(this.amount = this.amount + 1);
                case "--" -> setAmount(this.amount = this.amount - 1);
                case "+" -> setPage(this.page = this.page + 1);
                case "-" -> setPage(this.page = this.page - 1);
                case "=" -> {
                    Scanner scanner = new Scanner(System.in);
                    int set = 1;
                    System.out.println("Podaj stronę, która chcesz wyświetlić");
                    set = scanner.nextInt();
                    setPage(set);
                }
                case "?" -> {
                    System.out.println("++ - dodanie wiersza");
                    System.out.println("-- - usunięcie wiersza");
                    System.out.println("+ - następna strona");
                    System.out.println("- - poprzednia strona");
                    System.out.println("= - wyświetlenie konkretnej strony");
                    System.out.println("exit - powrót do głównego menu");
                }
                default -> {
                    System.out.println("Nieznana komenda");
                    return new ArrayList<>();
                }
            }

            List<T> newList = (List<T>) this.paginationList;

            if(getAmount() > paginationList.size()) {
                System.out.println("Nie można wyświetlić liczby rekordów większej od " + paginationList.size());
                setAmount(paginationList.size());
            } else if(getAmount()<1) {
                System.out.println("Nie można wyświetlić liczby rekordów mniejszej od 1");
                setAmount(1);
            }

            int numOfPages = (int)Math.ceil((double)this.paginationList.size()/getAmount());

            if(getPage() > numOfPages) {
                System.out.println("Nie ma strony większej od " + numOfPages);
                setPage(numOfPages);
            } else if(getPage() < 1) {
                System.out.println("Nie ma strony mniejszej od 1");
                setPage(1);
            }
            System.out.println("Liczba stron: " + getPage() +"/"+numOfPages);

            int fromIndex = (getPage() - 1) * getAmount();

            if (this.paginationList.isEmpty() || this.paginationList.size() <= fromIndex) {
                return Collections.emptyList();
            }

            return newList.subList(fromIndex, Math.min(fromIndex + getAmount(), paginationList.size()));

    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
