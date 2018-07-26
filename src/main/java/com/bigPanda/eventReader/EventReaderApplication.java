package com.bigPanda.eventReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class EventReaderApplication {

    static Map<String, Integer> eventTypeCount = new ConcurrentHashMap<>();
    static Map<String, Integer> dataWordCount = new ConcurrentHashMap<>();


    public static void main(String[] args) {
	    SpringApplication.run(EventReaderApplication.class, args);

        new Thread() {
            @Override
            public void run() {
                try {

                    Process process = null;
                    try {
                        process = Runtime.getRuntime().exec("generator-windows-amd64.exe");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (true){
                        byte b[] = new byte[256];
                        process.getInputStream().read(b);
                        String str = new String(b);
                        System.err.println(str);
                        String [] events = str.split("\n");


                        ObjectMapper mapper = new ObjectMapper();

                        for (int j = 0; j < events.length; j++) {
                            try {
                                Event event = mapper.readValue(events[j], Event.class);
                                System.out.println(event.toString());
                                if(dataWordCount.containsKey(event.getData())){
                                    dataWordCount.put(event.getData(), dataWordCount.get(event.getData()) + 1);
                                }
                                else{
                                    dataWordCount.put(event.getData(), 1);
                                }

                                if(eventTypeCount.containsKey(event.getEvent_type())){
                                    eventTypeCount.put(event.getEvent_type(), eventTypeCount.get(event.getEvent_type()) + 1);
                                }
                                else{
                                    eventTypeCount.put(event.getEvent_type(), 1);
                                }

                            }
                            catch (Exception e){
                                System.err.println("data: " + events[j] + " is not valid");
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


	}
}
