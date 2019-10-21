package com.hello.superwine;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 *
 * Выводит список комплексов упражнений
 */
public class WorkoutListFragment extends ListFragment {

    private Listener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; ++i) {
            names[i] = Workout.workouts[i].getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), // Получить контекст от LayoutInflater
                android.R.layout.simple_list_item_1,
                names);

        setListAdapter(adapter); // Связать адаптер массива со списковым представлением

        // Вызов метода onCreateView() суперкласса предо-
        //ставляет макет по умолчанию для ListFragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*Вызывается, когда фрагмент свя-
    зывается с активностью. Напом-
    ним, что класс Activity является
    субклассом Context*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    /*Сообщает слушателю,
    когда пользователь вы-
    брал вариант в ListView.*/
    @Override
    public void onListItemClick(ListView listView, // списковое представление
                                View itemView, // выбранный вариант списка
                                int position, // позиция
                                long id) { // идентификатор записи в используемых данных
        if (listener != null) {
            /*Вызывается метод itemClicked() активности с передачей идентификатора комплекса,
              выбранного пользователем.*/
            listener.itemClicked(id);
        }
    }

    interface Listener {
        void itemClicked(long id);
    };

}
