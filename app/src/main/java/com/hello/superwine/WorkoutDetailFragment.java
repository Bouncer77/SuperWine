package com.hello.superwine;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WorkoutDetailFragment extends Fragment {
    /*Идентификатор комплекса упражнений, вы-
    бранного пользователем. Позднее, при выводе
    подробной информации, он будет использован
    для заполнения представлений фрагмента*/
    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, // используется для заполнения макета фрагмента
                             ViewGroup container, // объект ViewGroup из макета активности, содержащий фрагмент
                             Bundle savedInstanceState) { // ранее вы сохранили состояние фрагмента, а теперь хотите восстановить его
        // Inflate the layout for this fragment
        // фрагмента задается методом inflate() класса LayoutInflator
        return inflater.inflate(R.layout.fragment_workout_detail, // Заполняет макет фрагмента, преобразуя разметку XML в объекты Java.
                container,
                false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView(); // Метод getView() получает корневой объект View фрагмента
        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textViewWorkoutTitle);
            TextView description = (TextView) view.findViewById(R.id.textViewWorkoutDescription);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long id) {
        this.workoutId = id;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workoutId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            StopwatchFragment stopwatch = new StopwatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.stopwatch_container, stopwatch);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            workoutId = savedInstanceState.getLong("workoutId");
        }
    }


}
