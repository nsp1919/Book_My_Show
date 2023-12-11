package com.example.book_my_show.Service;


import com.example.book_my_show.Exceptions.MovieNotFoundException;
import com.example.book_my_show.Exceptions.ShowNotFoundException;
import com.example.book_my_show.Exceptions.TheaterNotFoundException;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TheaterRepository;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;
import com.example.book_my_show.RequestDTO.ShowSeatRequestDTO;
import com.example.book_my_show.ResponseDTO.ShowDetailsResponse;

import com.example.book_my_show.ResponseDTO.ShowResponseForMovie;
import com.example.book_my_show.Transformer.ShowSeatTransformer;
import com.example.book_my_show.Transformer.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.ShortBufferException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(AddShowRequestDTO addShowRequestDTO) {
        Movie movie=movieRepository.findByName(addShowRequestDTO.getMovieName());
        if (movie==null)
            throw new MovieNotFoundException("Movie Name is invalid");
        Optional<Theater> optionalTheater=theaterRepository.findById(addShowRequestDTO.getTheaterId());
        if (!optionalTheater.isPresent())
            throw new TheaterNotFoundException("Theater with the given Id is not available");
        Theater theater=optionalTheater.get();
        Shows shows= ShowTransformer.convertShow(addShowRequestDTO);
        if (showRepository.existsByShowDateAndShowTime(shows.getShowDate(),shows.getShowTime())){
            throw new ShowNotFoundException("Another Show is Scheduled For This Time Please Select Another Time");
        }
        shows.setMovie(movie);
        shows.setTheater(theater);

        movie.getShowsList().add(shows);
        theater.getShowsList().add(shows);

        Shows shows1=showRepository.save(shows);
        return "Show has been added successfully with show ID "+shows1.getId();
    }

    public String enableShowSeats(ShowSeatRequestDTO showSeatRequestDTO) {
        Optional<Shows> optionalShows=showRepository.findById(showSeatRequestDTO.getShowId());
        if (optionalShows.isEmpty())
            throw new ShowNotFoundException("Show Not Found For Given ShowId");
        Shows shows=optionalShows.get();
        Theater theater=shows.getTheater();
        List<TheaterSeat>theaterSeatList=theater.getTheaterSeatList();
        List<ShowSeat>showSeatList=new ArrayList<>();
        for (TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeat= ShowSeatTransformer.createShowSeat(theaterSeat,shows);
            switch (theaterSeat.getSeatType()){
                case GOLD:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfGoldSeats());
                    showSeat.setFoodAvailable(true);
                    break;
                case SILVER:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfSilverSeats());
                    break;
                case CLASSIC:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfClassicSeats());
                    break;
                case PREMIUM:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfPremiumSeats());
                    showSeat.setFoodAvailable(true);
                    break;
            }
            showSeatList.add(showSeat);
        }

        shows.setShowSeatList(showSeatList);
        showRepository.save(shows);

        //We can save child also yo save child use showSeatRepository.save all(showSeatList)
        return "shows are enabled";
    }

    public String mostShowedMovie(LocalDate reqDate) {
        Integer movieId=showRepository.getMostShowedMovie(reqDate);
        Optional<Movie> optionalMovie=movieRepository.findById(movieId);
        if (!optionalMovie.isPresent())
            throw new MovieNotFoundException("Movie Not found on given date");
        Movie movie=optionalMovie.get();
        return movie.getName();

    }

    public List<String> getAvailableSeatsForShow(Integer showId) {
        Optional<Shows> optionalShows=showRepository.findById(showId);
        if (optionalShows.isEmpty())
            throw new ShowNotFoundException("Show Not Found For Given ShowId");
        Shows shows=optionalShows.get();
        List<ShowSeat>showSeatList=shows.getShowSeatList();
        List<String> availableSeatList=new ArrayList<>();
        for (ShowSeat showSeat:showSeatList){
            if (showSeat.isSeatAvailable())
                availableSeatList.add(showSeat.getSeatNo());
        }
        return availableSeatList;
    }

    public ShowDetailsResponse getShowDetails(Integer showId) {
        Optional<Shows> optionalShows=showRepository.findById(showId);
        if (optionalShows.isEmpty())
            throw new ShowNotFoundException("Show Not Found For Given ShowId");
        Shows shows=optionalShows.get();
        ShowDetailsResponse showDetailsResponse=ShowTransformer.showDetailsResponse(shows);
        return showDetailsResponse;

    }

    public List<ShowResponseForMovie> getShowsOnGivenDate(Integer movieId,LocalDate date) {
        Optional<Movie> optionalMovie=movieRepository.findById(movieId);
        if (optionalMovie.isEmpty())
            throw new MovieNotFoundException("Movie Not Found With Given MovieId");
        Movie movie=optionalMovie.get();
        List<Shows> showsList=movie.getShowsList();
        List<ShowResponseForMovie> showResponseForMoviesList=new ArrayList<>();

        for (Shows shows:showsList){
            if (shows.getShowDate().equals(date)) {
                ShowResponseForMovie showResponseForMovie = ShowTransformer.showResponseForMovie(shows);
                showResponseForMoviesList.add(showResponseForMovie);

            }
        }
        if (showResponseForMoviesList.size()==0)
            throw new ShowNotFoundException("No Shows Aee Available For The Given Date");
        return showResponseForMoviesList;
    }


}
