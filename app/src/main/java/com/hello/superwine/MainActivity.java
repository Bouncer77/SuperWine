package com.hello.superwine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

public class MainActivity extends AppCompatActivity
                implements WorkoutListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {

            // Создание фрагмента.
            WorkoutDetailFragment details = new WorkoutDetailFragment();

            // Начало транзакции фрагмента
            // Возвращает диспетчер фрагментов для работы с фрагментами из библиотеки поддержки.
            // Начало транзакции фрагмента.
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            details.setWorkout(id);

            // Добавление фрагмента в ViewGroup.
            // ft.add(R.id.fragment_container, details);

            // Замена фрагмента.
            ft.replace(R.id.fragment_container, details);

            // Удаление фрагмента.
            // ft.remove(details);

            // Назначать переходную анимацию не обязательно.
            /* где transiston — тип анимации. Допустимые значения:
             TRANSIT_FRAGMENT_CLOSE (фрагмент удаляется из стека),
              TRANSIT_FRAGMENT_OPEN (фрагмент добавляется),
               TRANSIT_FRAGMENT_FADE (фрагмент растворяется или проявляется),
                TRANSIT_NONE (анимация отсутствует). По умолчанию анимация не используется.
            */
            ft.setTransition(TRANSIT_FRAGMENT_FADE);

            // помещает транзакцию в стек возврата
            // @param строка с именем, используемая для идентификации транзакции.
            ft.addToBackStack(null);
            ft.commit();

        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }
    }
}
