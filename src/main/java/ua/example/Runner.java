package ua.example;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.example.domain.entity.User;
import ua.example.repositories.UserRepository;

import javax.annotation.Resource;

@Component
public class Runner implements CommandLineRunner {

    @Resource
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 345; i++) {
            User user = User.builder()
                    .name(faker.name().firstName())
                    .surname(faker.name().lastName())
                    .age(faker.random().nextInt(18, 50))
                    .phoneNumber(getRandomPhone(faker))
                    .build();
            userRepository.save(user);
        }

    }

    private String getRandomPhone(Faker faker) {
        int[] array = {6, 9};
        int[] array2 = {0, 3, 5, 6, 7, 8, 9};
        int[] x = new int[7];
        for (int i = 0; i < x.length; i++) {
            x[i] = faker.random().nextInt(0, 9);
        }
        int y = array[faker.random().nextInt(0, 1)];
        int y2 = array2[faker.random().nextInt(0, 6)];
        return "+380-(" + y + y2 + ")-" + x[0] + x[1] + x[2] + "-" + x[3] + x[4] + "-" + x[5] + x[6];
    }
}
