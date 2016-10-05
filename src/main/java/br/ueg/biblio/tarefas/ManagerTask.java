package br.ueg.biblio.tarefas;

import br.ueg.biblio.model.interfaces.Injetavel;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class ManagerTask implements Injetavel, Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jkey = context.getJobDetail().getKey();
        String ur = (String) context.getJobDetail().getJobDataMap().get("app");
        System.out.println("JOB KEY: " + jkey + " EM " + LocalDateTime.now() + " at " + ur);

        try {
            //
            URL url = new URL(ur + "view/publico/requisicao.jsf");
            System.out.println("END: " + url);
            String charset = "UTF-8";
            String param1 = "value1";
            String query;
            query = String.format("param1=%s", URLEncoder.encode(param1, charset));
            URLConnection connection = new URL(url + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            response = new URL(url + "?" + query).openStream();

        } catch (MalformedURLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
