package com.kinotoday.kiev.model;

public class Session {
    private String ticketsLink;
    private java.util.Date timeStart;
    private Integer price;
    private java.util.Date timeEnd;
    private Movie movie;
    private Cinema cinema;

    public Cinema getCinema()
    {
        return cinema;
    }

    public void setCinema( Cinema cinema )
    {
        this.cinema = cinema;
    }

    public String getTicketsLink() {
        return ticketsLink;
    }

    public void setTicketsLink(String ticketsLink) {
        this.ticketsLink = ticketsLink;
    }

    public java.util.Date getTimeStart()
    {
        return timeStart;
    }

    public void setTimeStart( java.util.Date timeStart )
    {
        this.timeStart = timeStart;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public java.util.Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(java.util.Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Session{" +
                "movie=" + (movie != null ? movie.getTitle() : "no movie") +
                ", price=" + price +
                ", timeStart=" + timeStart +
                ", ticketsLink='" + ticketsLink + '\'' +
                '}';
    }
}