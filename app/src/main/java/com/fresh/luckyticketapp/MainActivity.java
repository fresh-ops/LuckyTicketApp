package com.fresh.luckyticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText ticketNumberIn;
    private TextView ticketLuckOut;
    private Button checkTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticketNumberIn = findViewById(R.id.ticketNumberIn);
        ticketLuckOut = findViewById(R.id.ticketLuckOut);
        checkTicket = findViewById(R.id.checkTicket);

        checkTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        String number = ticketNumberIn.getText().toString();
        LuckyTicket ticket  = new LuckyTicket();

        try {
            if (number.length() != 6)
                throw new Exception("Номер должен иметь длинну 6");
            ticket.setNumber(Integer.parseInt(number));

            if (ticket.isLucky()) {
                ticketLuckOut.setText("Поздравляем, этот билет счастливый!");
            }
            else {
                ticketLuckOut.setText("К сожалению этот билет не счастливый, следующий счастливый будет с номером " +
                        formatTicketNumber(ticket.findNextLucky()));
            }
            ticketLuckOut.setBackground(null);
        }
        catch (NumberFormatException ex) {
            ticketLuckOut.setText("Ошибка: поле может содержать только цифры");
            ticketLuckOut.setBackgroundColor(getResources().getColor(R.color.error));
        }
        catch (Exception ex) {
            ticketLuckOut.setText("Ошибка: номер должен иметь длину 6. Вводите номер полностью с начальными нулями");
            ticketLuckOut.setBackgroundColor(getResources().getColor(R.color.error));
        }

        ticketLuckOut.setVisibility(View.VISIBLE);
    }

    private String formatTicketNumber(int number) {
        String res = Integer.toString(number);
        while (res.length() < 6) {
            res = "0" + res;
        }
        return res;
    }
}