package com.read.dima;

import com.read.dima.Models.*;
import com.read.dima.Repo.BookRepo;
import com.read.dima.Repo.UserRepo;
import com.read.dima.Repo.User_BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class StartupData implements CommandLineRunner {

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final User_BookRepo userBookRepo;

    @Autowired
    public StartupData(BookRepo bookRepo, UserRepo userRepo, User_BookRepo userBookRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.userBookRepo = userBookRepo;
    }

    @Override
    public void run(String... args) {
        Random random = new Random();

        // ======== BOOKS ========
        String[] authors = {
                "Тарас Шевченко","Ліна Костенко","Михайло Коцюбинський","Іван Франко","Олесь Гончар",
                "Джордж Орвелл","Ернест Гемінґвей","Джоан Ролінґ","Стівен Кінг","Артур Конан Дойл",
                "Рей Бредбері","Олександр Дюма","Жуль Верн","Віктор Гюго","Агата Крісті"
        };
        String[] titles = {
                "Тіні забутих предків","Кобзар","Сто років самотності","Війна і мир","Майстер і Маргарита",
                "Гаррі Поттер і філософський камінь","1984","Анна Кареніна","Сяйво","Острів скарбів",
                "Злочин і кара","Володар перснів","Собаче серце","Пригоди Шерлока Холмса","Мартін Іден"
        };

        Genre[] genres = Genre.values();

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Book b = new Book();
            b.setYear(1900 + random.nextInt(124)); // 1900-2023
            b.setAvtor(authors[random.nextInt(authors.length)]);
            b.setName(titles[random.nextInt(titles.length)] + " " + (i+1));
            b.setGenre(genres[random.nextInt(genres.length)]); // enum
            books.add(b);
        }
        bookRepo.saveAll(books);

        // ======== USERS ========
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            User u = new User();
            u.setUsername("user" + (i+1));
            u.setPassword("pass" + (i+1));
            u.setDate(LocalDate.of(
                    1970 + random.nextInt(40),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)));
            u.setRole(UserRole.USER);
            u.setCountReadBooks(random.nextLong(50));
            users.add(u);
        }
        userRepo.saveAll(users);

        // ======== USER_BOOK ========
        List<User_Book> userBooks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User_Book ub = new User_Book();
            User u = users.get(random.nextInt(users.size()));
            Book b = books.get(random.nextInt(books.size()));
            ub.setUser(u);
            ub.setBook(b);

            LocalDate takeDate = LocalDate.now().minusDays(random.nextInt(60));
            ub.setDateTake(takeDate);

            // 50% returned
            if (random.nextBoolean()) {
                LocalDate backDate = takeDate.plusDays(7 + random.nextInt(20));
                ub.setBookBack(true);
                ub.setDateCurrentBack(backDate);
                ub.setDateBack(takeDate.plusDays(30));
            } else {
                ub.setBookBack(false);
                ub.setDateCurrentBack(null);
                ub.setDateBack(takeDate.plusDays(30));
            }

            userBooks.add(ub);
        }
        userBookRepo.saveAll(userBooks);

        System.out.println("=== Startup Data Loaded ===");
    }
}
