package experis.academy.filmapi.mapperImpl;

import experis.academy.filmapi.dto.MovieDto;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.model.Movie;

public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto modelToDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setReleaseYear(movie.getReleaseYear());
        movieDto.setGenre(movie.getGenre());
        movieDto.setPosterPictureURL(movie.getPosterPictureURL());
        movieDto.setTrailerLink(movie.getTrailerLink());

        return movieDto;
    }

    @Override
    public Movie dtoToModel(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setGenre(movieDto.getGenre());
        movie.setPosterPictureURL(movieDto.getPosterPictureURL());
        movie.setTrailerLink(movieDto.getTrailerLink());

        return movie;
    }

}
