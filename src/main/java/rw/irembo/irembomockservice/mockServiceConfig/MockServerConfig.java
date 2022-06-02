package rw.irembo.irembomockservice.mockServiceConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
@Configuration
public class MockServerConfig {
    private static final String HOST="localhost";
    private static final int PORT=8000;
    private static WireMockServer server=new WireMockServer(PORT);

    @Bean
    public void initializeServer(){
        server.loadMappingsUsing(new JsonFileMappingsSource(new ClasspathFileSource("stubs")));
        printInfo(server);
        server.start();
        WireMock.configureFor(PORT);
        log.info("server started");
        requestMappingConstructor();
    }

    private void requestMappingConstructor(){
        stubFor(get("/test").willReturn(aResponse().withBody("Welcome to mock!")));
        stubFor(get("/json")
                .willReturn(okJson("{ \"message\": \"Hello\" }")));
    }


    private static void printInfo(final WireMockServer wireMockServer) {
        log.info(wireMockServer.listAllStubMappings().getMappings().toString() +" "+ " mappings configured. See configuration at:"+" "+ "       http://localhost:" + PORT + "/__admin/");
    }

}
