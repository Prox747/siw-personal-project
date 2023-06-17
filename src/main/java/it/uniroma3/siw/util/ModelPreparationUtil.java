package it.uniroma3.siw.util;
import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Component
public class ModelPreparationUtil {
    @Autowired
    CredentialsService credentialsService;

    public String prepareModelForIndexTemplate(String template, Model modelToPrepare,
                                               Set<JobAd> last15JobAdsAdded,
                                               List<JobAd> most5popularJobAds) {
        modelToPrepare.addAttribute("newestJobAds", last15JobAdsAdded);
        modelToPrepare.addAttribute("mostPopularJobAds", most5popularJobAds);
        return template;
    }

//    public String prepareModelForMovieTemplate(String template, Model modelToPrepare, Movie movieToInject) {
//        modelToPrepare.addAttribute("movie", movieToInject);
//        if(movieToInject.getActors() != null)
//            modelToPrepare.addAttribute("actors", movieToInject.getActors());
//        //se non siamo loggati
//        if(!credentialsService.getCurrentCredentials().isPresent()) {
//            modelToPrepare.addAttribute("userIsApplicant", false);
//            modelToPrepare.addAttribute("userIsAdmin", false);
//            return template;
//        }
//
//        //se siamo loggati
//        User currentUser = credentialsService.getCurrentCredentials().get().getUser();
//        Review currentUserReview = currentUser.getReview();
//        //se è admin o è registrato e non ha ancora recensito il film, può aggiungere una recensione
//        if(credentialsService.userIsAdmin()) {
//            //se è admin può cancellare le recensioni
//            modelToPrepare.addAttribute("userIsAdmin", true);
//            modelToPrepare.addAttribute("userIsApplicant", true);
//            if(currentUserReview == null)
//                modelToPrepare.addAttribute("userCanAddReview", true);
//        }
//        if(credentialsService.userIsApplicant()) {
//            modelToPrepare.addAttribute("userIsApplicant", true);
//            modelToPrepare.addAttribute("userIsAdmin", false);
//            if(currentUserReview == null)
//                modelToPrepare.addAttribute("userCanAddReview", true);
//        }
//
//        //se l'utente ha già recensito il film, può cancellare la sua recensione
//        //ci serviamo del suo id per fare dei check
//        if(currentUserReview != null &&
//                currentUserReview.getReviewedMovie().getId().equals(movieToInject.getId())) {
//            modelToPrepare.addAttribute("currentUserId", currentUser.getId());
//        }
//
//        //ci dice se l'utente ha messo il film tra i preferiti
//        if(currentUser.getFavourites().contains(movieToInject))
//            modelToPrepare.addAttribute("isUserFavourite", true);
//
//        return template;
//    }
//
//    public String prepareModelForArtistTemplate(String template, Model modelToPrepare, Artist artistToInject) {
//        modelToPrepare.addAttribute("artist", artistToInject);
//        if(artistToInject.getMoviesActedIn() != null)
//            modelToPrepare.addAttribute("moviesActedIn", artistToInject.getMoviesActedIn());
//        if(artistToInject.getDirectedMovies() != null)
//            modelToPrepare.addAttribute("moviesDirected", artistToInject.getDirectedMovies());
//        //se non siamo loggati
//        if(!credentialsService.getCurrentCredentials().isPresent()) {
//            return template;
//        }
//        //se è admin o è registrato e non ha ancora recensito il film, può aggiungere una recensione
//        if(credentialsService.userIsAdmin()) {
//            //se è admin può cancellare le recensioni
//            modelToPrepare.addAttribute("userIsAdmin", true);
//        }
//        return template;
//    }
}
