package src.domain.path;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resolve_Test {

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URI uri = Paths.get("/taishozuki").resolve("111").toUri();
        //URL url = new URL("/taishozuki");

        URI uri2 = new URI("taishozuki");
        String a = String.join("/", "", "taishozuki", "111");
        //System.out.println(uri2.);
        System.out.println(a);

    }

}
