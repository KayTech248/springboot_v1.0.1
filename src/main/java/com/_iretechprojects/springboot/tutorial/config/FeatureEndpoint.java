package com._iretechprojects.springboot.tutorial.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

  private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

  public FeatureEndpoint() {
    featureMap.put("Department", new Feature(true, "Feature blah blah blah"));
    featureMap.put("User", new Feature(false, "Feature blah blah blah"));
    featureMap.put("Authentication", new Feature(false, "Feature blah blah blah"));
  }

  //Endpoint 1 to get list of features
  @ReadOperation
  public Map<String, Feature> features (){
    return featureMap;
  }

  //Endpoint 2 to get a particular feature
  @ReadOperation
  public Feature feature(@Selector String featureName){
    return featureMap.get(featureName);
  }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Feature {
    private boolean isEnabled;
    private String description;
  }


}
