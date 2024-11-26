package ssf.day17.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomNumberService {
    private Random rand = new SecureRandom();

    public int getRandomInt() {
        return this.getRandomInt(100);
    }
    public int getRandomInt(int bound) {
        return rand.nextInt(bound);
    }
}
