package com.example.hw2.activities;

import com.example.hw2.activities.objects.Player;

import java.util.Comparator;

public class ScoresSortHelper implements Comparator<Player> {

        // Used for sorting in ascending order of
        // roll number
        public int compare(Player s1, Player s2)
        {
            return s2.getScore() - s1.getScore();
        }

}
