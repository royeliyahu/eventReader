package com.bigPanda.eventReader;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("data")
    private String data;

    @JsonProperty("timestamp")
    private Long timestamp;

    public Event(){
    }

    public Event(String event_type, String data, Long timestamp) {
        this.event_type = event_type;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_type='" + event_type + '\'' +
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
