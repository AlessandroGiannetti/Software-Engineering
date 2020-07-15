/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client.movie;

import it.sapienza.soap.webservice.movies.Director;
import it.sapienza.soap.webservice.movies.Movie;
import it.sapienza.soap.webservice.movies.MovieMap;
import java.util.List;

/**
 *
 * @author studente
 */
public class SOAPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Movie> result = SOAPClient.getMovies().getEntry();
        Director director = null;
        
        for (int i = 0; i < result.size(); i++) {
            director = SOAPClient.getDirector(result.get(i).getDirectorID());
            System.out.println("Movie: ID:" + ((Movie) result.get(i)).getID() + " - Title: " + ((Movie) result.get(i)).getTitle() + " - Year: " + ((Movie) result.get(i)).getYear());
            System.out.println("Director: ID: " + director.getID() + " - Name: " + director.getName() + " - year of birthday: " + director.getYearOfBirth() + "\n");
        }
    }
    
    private static Director getDirector(java.lang.String arg0) {
        it.sapienza.soap.webservice.movies.WSImplService service = new it.sapienza.soap.webservice.movies.WSImplService();
        it.sapienza.soap.webservice.movies.WSInterface port = service.getWSImplPort();
        return port.getDirector(arg0);
    }
    
    private static MovieMap getMovies() {
        it.sapienza.soap.webservice.movies.WSImplService service = new it.sapienza.soap.webservice.movies.WSImplService();
        it.sapienza.soap.webservice.movies.WSInterface port = service.getWSImplPort();
        return port.getMovies();
    }
    
}
