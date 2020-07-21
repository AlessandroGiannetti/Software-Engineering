/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.rest.client;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alegi
 */
public class Director {
  private int id;
    private String name;
    private String year;
    private List<Movie> movies = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void setMovies (List<Movie> movies) {
        this.movies = movies;
    }   
    
        @Override
    public int hashCode() {
        return id + name.hashCode() + year.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Director) && (id == ((Director) obj).getId()) && (name.equals(((Director) obj).getName())) && (year.equals(((Director) obj).getYear()));
    }

    @Override
    public String toString() {
        String text = "";
        for(Movie movie : movies){
            text += movie+"\n";
        }
        return "Director: ID:" + id + " ,Name: " + name + ", YearOfBirthday: " + year + " |\n"
                + text;
        
    }

}
