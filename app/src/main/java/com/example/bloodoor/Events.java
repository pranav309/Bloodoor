package com.example.bloodoor;

public class Events {
    private String bankName, name, startDate, endData, description, status, duration, venue;

    public Events() {
    }

    public Events(String bankName, String startDate, String endData, String description, String duration, String venue) {
        this.bankName = bankName;
        this.startDate = startDate;
        this.endData = endData;
        this.description = description;
        this.duration = duration;
        this.venue = venue;
    }

    public Events(String bankName, String name, String startDate, String endData, String description, String status, String duration, String venue) {
        this.bankName = bankName;
        this.name = name;
        this.startDate = startDate;
        this.endData = endData;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.venue = venue;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
