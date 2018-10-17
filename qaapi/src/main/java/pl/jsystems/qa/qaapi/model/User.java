package pl.jsystems.qa.qaapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    public String imie;
    public String nazwisko;
    public List<Device> device;

    public static class Device{
        public String type;
        @JsonProperty(value = "device.model", required = true)
        public List<Model> deviceModel;

        public static class Model {
            public String produce;
            @JsonProperty(value = "screen.size")
            public double screenSize;

        }

    }
}
