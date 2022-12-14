package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.port.MovieRepository;
import io.mbnakaya.imdplay.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private List<Movie> movies = new ArrayList<>();

    MovieRepositoryImpl() {
        movies.add(Movie.builder()
                .id(0L)
                .tittle("The Avengers")
                .genre("Action, Adventure, Sci-Fi")
                .year(2012)
                .director("Joss Whedon")
                .rated("PG-13")
                .imdbRating(8.0)
                .votes(1387297)
                .build());

        movies.add(Movie.builder()
                .id(1L)
                .tittle("Avatar")
                .genre("Action, Adventure, Fantasy")
                .year(2009)
                .director("James Cameron")
                .rated("PG-13")
                .imdbRating(7.4)
                .votes(816342)
                .build());

        movies.add(Movie.builder()
                .id(2L)
                .tittle("Spider-Man")
                .genre("Action, Adventure, Sci-Fi")
                .year(2002)
                .director("Sam Raimi")
                .rated("PG-13")
                .imdbRating(7.8)
                .votes(1247508)
                .build());

        movies.add(Movie.builder()
                .id(3L)
                .tittle("Letters to Juliet")
                .genre("Adventure, Comedy, Drama")
                .year(2010)
                .director("Gary Winic")
                .rated("PG")
                .imdbRating(6.5)
                .votes(100961)
                .build());

        movies.add(Movie.builder()
                .id(4L)
                .tittle("Blood Diamond")
                .genre("Adventure, Drama, Thriller")
                .year(2006)
                .director("Edward Zwick")
                .rated("R")
                .imdbRating(8.0)
                .votes(549744)
                .build());

        movies.add(Movie.builder()
                .id(5L)
                .tittle("Forrest Gump")
                .genre("Drama, Romance")
                .year(1994)
                .director("Robert Zemeckis")
                .rated("PG-13")
                .imdbRating(8.8)
                .votes(2066658)
                .build());

        movies.add(Movie.builder()
                .id(6L)
                .tittle("Star Wars: Episode V - The Empire Strikes Back")
                .genre("Action, Adventure, Fantasy")
                .year(1980)
                .director("Irvin Kershner")
                .rated("PG")
                .imdbRating(8.7)
                .votes(1287175)
                .build());

        movies.add(Movie.builder()
                .id(7L)
                .tittle("Jurassic Park")
                .genre("Action, Adventure, Sci-Fi")
                .year(1993)
                .director("Steven Spielberg")
                .rated("PG-13")
                .imdbRating(8.2)
                .votes(985724)
                .build());

        movies.add(Movie.builder()
                .id(8L)
                .tittle("The Lord of the Rings: The Fellowship of the Ring")
                .genre("Action, Adventure, Drama")
                .year(2001)
                .director("Peter Jackson")
                .rated("PG-13")
                .imdbRating(8.8)
                .votes(1866719)
                .build());

        movies.add(Movie.builder()
                .id(9L)
                .tittle("Harry Potter and the Goblet of Fire")
                .genre("Adventure, Family, Fantasy")
                .year(2001)
                .director("Mike Newell")
                .rated("PG-13")
                .imdbRating(7.7)
                .votes(625197)
                .build());
    }

    @Override
    public Movie getById(Long id) {
        return this.movies.get(id.intValue());
    }

    @Override
    public Integer getMovieListSize() {
        return this.movies.size();
    }
}
