/*
https://www.acmicpc.net/problem/10930
1초 - 256MB
입력 길이 최대 50

아이디어
1. SHA256
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());

        for (byte b : md.digest()) {
            System.out.printf("%02x", b);
        }
    }
}
