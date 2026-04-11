package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encriptador {
    static void main(String[] args) {
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        String hash = enconder.encode("admin");
        System.out.println(hash);
    }
}
