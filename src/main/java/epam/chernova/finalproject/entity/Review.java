package epam.chernova.finalproject.entity;

import java.util.Objects;

public class Review {
    private int idReview;
    private String reviewText;
    private int mark;
    private int idClient;

    public Review() {
    }

    public Review(int idReview, String reviewText, int mark, int idClient) {
        this.idReview = idReview;
        this.reviewText = reviewText;
        this.mark = mark;
        this.idClient = idClient;
    }

    public Review(String reviewText, int mark, int idClient) {
        this.reviewText = reviewText;
        this.mark = mark;
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return idReview == review.idReview &&
                mark == review.mark &&
                idClient == review.idClient &&
                Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReview, reviewText, mark, idClient);
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", reviewText='" + reviewText + '\'' +
                ", mark=" + mark +
                ", idClient=" + idClient +
                '}';
    }
}
