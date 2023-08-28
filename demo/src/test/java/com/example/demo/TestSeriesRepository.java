package com.example.demo;

import com.example.demo.domain.model.Series;
import com.example.demo.repository.ISeriesRepository;
import java.sql.Date;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class TestSeriesRepository {
    /*
    @Autowired
    ISeriesRepository SeriesRepository;
    @Test
    void test() {
        Set<Series> seriesRating = SeriesRepository.findAllByRatingGreaterThan(8.21);
        System.out.println("Series rating greater than 8.21");
        for (Series m : seriesRating) {
            System.out.println(m.getTitle() + ": " + m.getRating());
        }
        Set<Series> seriesDate = SeriesRepository.findAllByReleaseDateBetween(Date.valueOf( "2008-03-10"), Date.valueOf( "2015-08-28"));
        System.out.println("Series between: " + Date.valueOf( "2008-03-10") + " & " + Date.valueOf( "2015-08-28"));
        for (Series m : seriesDate) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Set<Series> seriesDateBefore = SeriesRepository.findAllWithReleaseDateBefore(Date.valueOf( "2006-03-10"));
        System.out.println("Series before: " + Date.valueOf( "2006-03-10"));
        for (Series m : seriesDateBefore) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Set<Series> seriesDateAfter = SeriesRepository.findAllWithReleaseDateAfter(Date.valueOf( "2011-03-10"));
        System.out.println("Series after: " + Date.valueOf( "2011-03-10"));
        for (Series m : seriesDateAfter) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Series bestSeries = SeriesRepository.findFirstByOrderByRatingDesc();
        System.out.println("Best Series: " + bestSeries.getTitle() + "\nRating: " + bestSeries.getRating());
    }

     */
}
