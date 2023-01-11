package com.company.lab2;

// 1. Реалізуйте програмний код, даний у лістингу (у додатку), та протестуйте його при різних значеннях параметрів.
// Модифікуйте програму, використовуючи методи управління потоками, так, щоб її робота була завжди коректною.
// Запропонуйте три різних варіанти управління. 30 балів.

// 2. Реалізуйте приклад Producer-Consumer application
// (див. https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html ).
// Модифікуйте масив даних цієї програми, які читаються, у масив чисел заданого розміру (100, 1000 або 5000)
// та протестуйте програму.  Зробіть висновок про правильність роботи програми. 20 балів.

// 3. Реалізуйте роботу електронного журналу групи, в якому зберігаються оцінки з однієї дисципліни трьох груп студентів.
// Кожного тижня лектор і його 3 асистенти виставляють оцінки з дисципліни за 100-бальною шкалою. 40 балів

// 4. Зробіть висновки про використання методів управління потоками в java. 10 балів.

public class UnsynchBankTest {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10_000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(b, i, INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }
}