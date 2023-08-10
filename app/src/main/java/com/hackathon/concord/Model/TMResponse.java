package com.hackathon.concord.Model;

import java.util.HashMap;
import java.util.List;

public class TMResponse {
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }
    public static class Document {
        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

}
