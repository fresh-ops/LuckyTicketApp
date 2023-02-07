package com.fresh.luckyticketapp;

public class LuckyTicket {
    private int number;

    public LuckyTicket() {
    }

    public LuckyTicket(int number) {
        this.number = number;
    }

    /**
     * Метод, проверяющий билет на счастливость
     *
     * Пусть есть билет с номером abcdef
     * Сумма нечётных цифр s1 = b + d + f
     *  Сумма чётных цифр s2 = a + c + e
     *
     *  Билет является счастливым, если  s1 = s2
     * @return является ли билет счастливым
     */
    public boolean isLucky() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += Math.pow(-1, i) * (number / ((int) Math.pow(10, i)) % 10);
        }
        /*
        Заметим, что билет счастливый, если  s2 - s1 = 0

        С помощью степени числа -1 мы вычитаем нечётные цифры и прибавляем нечётные
        Расскрыв цикл получим:
        sum = f - e + d - c + b - a = (b + d + f) - (a + c + e) = s2 - s1

        Билет будет счастливым, если sum = 0
         */
        return sum  == 0;
    }

    /**
     * Метод поиска следующего счастливого билета
     * @return номер слудующего счастливого билета
     */
    public int findNextLucky() {
        int res = number;

        for (; res < 1_000_000; res++) {
            int sum = 0;
            for (int i = 0; i < 6; i++) {
                sum += Math.pow(-1, i) * (res / ((int) Math.pow(10, i)) % 10);
            }

            if (sum == 0) {
                break;
            }
        }
        return res;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
