package pl.edu.agh.testify.client.model;

public class LinkBuilder {

    public static String task(long id) {
        return "http://localhost:11226/task/" + id; // TODO: 2017-06-23
    }
}
