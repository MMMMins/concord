package com.hackathon.concord.Model;

import java.util.List;

public class AKResponse_AirQualityIndex {
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
    private String so2Grade;
    private String coFlag;
    private String khaiValue;
    private String so2Value;
    private String coValue;
    private String pm25Flag;
    private String pm10Flag;
    private String pm10Value;
    private String o3Grade;
    private String khaiGrade;
    private String pm25Value;
    private String no2Flag;
    private String no2Grade;
    private String o3Flag;
    private String pm25Grade;
    private String so2Flag;
    private String dataTime;
    private String coGrade;
    private String no2Value;
    private String o3Value;

    public String getSo2Grade() {
      return so2Grade;
    }

    public String getCoFlag() {
      return coFlag;
    }

    public String getKhaiValue() {
      return khaiValue;
    }

    public String getSo2Value() {
      return so2Value;
    }

    public String getCoValue() {
      return coValue;
    }

    public String getPm25Flag() {
      return pm25Flag;
    }

    public String getPm10Flag() {
      return pm10Flag;
    }

    public String getPm10Value() {
      return pm10Value;
    }

    public String getO3Grade() {
      return o3Grade;
    }

    public String getKhaiGrade() {
      return khaiGrade;
    }

    public String getPm25Value() {
      return pm25Value;
    }

    public String getNo2Flag() {
      return no2Flag;
    }

    public String getNo2Grade() {
      return no2Grade;
    }

    public String getO3Flag() {
      return o3Flag;
    }

    public String getPm25Grade() {
      return pm25Grade;
    }

    public String getSo2Flag() {
      return so2Flag;
    }

    public String getDataTime() {
      return dataTime;
    }

    public String getCoGrade() {
      return coGrade;
    }

    public String getNo2Value() {
      return no2Value;
    }

    public String getO3Value() {
      return o3Value;
    }
  }
}
