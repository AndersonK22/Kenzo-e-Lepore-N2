package br.mack.ps2;

//import br.mack.ps2.api.Trends;
//import br.mack.ps2.api.ScannerVai;
//import br.mack.ps2.api.Trends;
import br.mack.ps2.dao.Covid19DAOMySQL;
////import br.mack.ps2.dao.TrendsDao;
//import br.mack.ps2.dao.TrendsDao;
import br.mack.ps2.resource.InterfaceUsuarioCovid19;
import br.mack.ps2.resource.Covid19Resource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
//import br.mack.ps2.resource.TrendsResource;
//import io.dropwizard.Application;
//import io.dropwizard.Configuration;
//import io.dropwizard.assets.AssetsBundle;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;
//import org.eclipse.jetty.servlets.CrossOriginFilter;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.FilterRegistration;
//import java.util.EnumSet;
//import java.util.InputMismatchException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    public static void main( String[] args ) {
        try {
            (new App()).run(args);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        AssetsBundle assetsBundle = new AssetsBundle("/site","/",   "index.html");
        bootstrap.addBundle(assetsBundle);
        super.initialize(bootstrap);
    }


    @Override
    public void run(Configuration configuration, Environment environment){


        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);


        cors.setInitParameter("allowedOrigins","*");
        cors.setInitParameter("allowedHeaders","X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods","OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true,"");






        environment.jersey().register(new Covid19Resource());

        environment.jersey().setUrlPattern("/api/*");


        Scanner print = new Scanner(System.in);
        int escolha = 0;
        int escolha2 = 0;

        System.out.println("**** Menu ****");

        System.out.println("Deseja modificar 0 banco de dados?");
        System.out.println("Digite 1 para sim ou 2 para não:");
        escolha = print.nextInt();
        if (escolha == 1) {
                Covid19DAOMySQL dao1 = new Covid19DAOMySQL();
                InterfaceUsuarioCovid19 covid19 = new InterfaceUsuarioCovid19(dao1);

            covid19.iniciar();
            } if (escolha == 2) {
            System.out.println("Você saiu do programa!");
            System.out.println("Se quiser retornar ao programa, por favor de start novamente :) ");
            return;
        }
    }
    }

