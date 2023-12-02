package rresources;

import pojo.CreatePost;

public class TestDataBuild {
    public CreatePost addNewPost(){
        CreatePost create = new CreatePost();
        create.setUserId(11);
        create.setId(101);
        create.setTitle("automation api trail");
        create.setBody("this body is made to get the post trial known to the assertion trial that will be made ");


        return create;

    }
}
