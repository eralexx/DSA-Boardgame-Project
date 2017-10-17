package WebApp.DSA;

import Model.Classes.testClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/test")
public class RESTTest {

    protected List<testClass> tests;

    public RESTTest() {
        tests = new ArrayList<>();

        testClass t1 = new testClass();
        t1.setAttribute1(123123);
        t1.setAttribute2("ahahahahah");
        tests.add(t1);

        testClass t2 = new testClass();
        t2.setAttribute1(12312);
        t2.setAttribute2("suerrrtttte");
        tests.add(t2);
    }

    @GET
    @Path("/testArray")
    @Produces(MediaType.APPLICATION_JSON)
    public List<testClass> getTrackInJSON() {

        return tests;

    }
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public testClass sdsdfsdf() {
        testClass t2 = new testClass();
        t2.setAttribute1(12312);
        t2.setAttribute2("suerrrtttte");
        return t2;

    }



}