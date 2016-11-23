package com.mbit.interceptors;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

public class CustomLocationInterceptor implements Interceptor {

  private String _header;
  private String _defaultLocation;

  @Override
  public void initialize() {
    _header = "location";
    _defaultLocation = "US";
  }

  @Override
  public Event intercept(Event event) {

    String location = _defaultLocation;
    
    byte[] eventBody = event.getBody();

    System.out.println("--->body:" + eventBody);

    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(new String(eventBody));
      location = rootNode.get("location").getValueAsText();
      System.out.println("--->location:" + location);
    } catch (JsonProcessingException e) {

    } catch (Exception e) {

    }
   
    Map<String, String> headers = event.getHeaders();
    headers.put(_header, location);
   
    event.setHeaders(headers);

    System.out.println("--->headers:" + headers);
   
    return event;
  }

  @Override
  public List<Event> intercept(List<Event> events) {

    List<Event> interceptedEvents =
        new ArrayList<Event>(events.size());
    for (Event event : events) {
      // Intercept any event
      Event interceptedEvent = intercept(event);
      interceptedEvents.add(interceptedEvent);
    }

    return interceptedEvents;
  }

  @Override
  public void close() {
    // At interceptor shutdown
  }

  public static class Builder implements Interceptor.Builder {

    @Override
    public void configure(Context context) {
    }

    @Override
    public Interceptor build() {
      return new CustomLocationInterceptor();
    }
  }
}