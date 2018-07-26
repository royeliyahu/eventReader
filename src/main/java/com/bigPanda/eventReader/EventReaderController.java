package com.bigPanda.eventReader;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventReaderController {

    @RequestMapping(method = RequestMethod.GET, value = "/wordcount")
    public String getWordCount(Model model){

        List<DataCount> dataList = EventReaderApplication.dataWordCount.entrySet().stream().map(
                data -> new DataCount(data.getValue(), data.getKey())).collect(Collectors.toList());

        model.addAttribute("message", "data word count: " );
        model.addAttribute("dataList", dataList);

        return "wordcount";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/eventcount")
    public String getEventCount(Model model){
        model.addAttribute("message", "event type count: " );
        List<DataCount> dataList = EventReaderApplication.eventTypeCount.entrySet().stream().map(
                data -> new DataCount(data.getValue(), data.getKey())).collect(Collectors.toList());

        model.addAttribute("dataList", dataList);
        return "eventcount";
    }

}
