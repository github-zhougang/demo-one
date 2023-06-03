package com.spring.demo.condition;

/**
 * @author Zhou Gang
 * @date 2023/2/14 15:49
 */
public class Test4 {

    public static void main(String[] args) {
        System.out.println(isPrime(73));
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        System.out.println(Math.sqrt(n));
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
