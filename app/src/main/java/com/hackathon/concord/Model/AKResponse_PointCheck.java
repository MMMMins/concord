package com.hackathon.concord.Model;

import java.util.List;

public class AKResponse_PointCheck {
    private ResponseData response;

    public ResponseData getResponse() {
        return response;
    }

    public static class ResponseData {
        private BodyData body;

        public BodyData getBody() {
            return body;
        }
    }

    public static class BodyData {
        private int totalCount;
        private List<Item> items;

        public int getTotalCount() {
            return totalCount;
        }

        public List<Item> getItems() {
            return items;
        }
    }

    public static class Item {
        private double tm;
        private String addr;
        private String stationName;

        public double getTm() {
            return tm;
        }

        public String getAddr() {
            return addr;
        }

        public String getStationName() {
            return stationName;
        }
    }
}
