package rresources;

public enum APIResources {
    getPostByID("http://jsonplaceholder.typicode.com/posts/1"),
    getPosts("http://jsonplaceholder.typicode.com/posts/"),
    addPost("http://jsonplaceholder.typicode.com/posts");
    private String resource;
    APIResources(String resource){this.resource= resource;}
    public String getResource(){return resource;}
}
