package com.lld.CarRental.models;

import java.util.List;

public class Branch {

    private String id;
    private String name;
    private List<Vehicle> vehicles;
    private Integer distance;
    private double branchCommissionRate;


    public Branch(String name, int distance, double branchCommissionRate) {
        this.name = name;
        this.distance = distance;
        this.branchCommissionRate = branchCommissionRate;
    }

    public Integer getDistance() {
        return distance;
    }


    public double getBranchCommissionRate() {
        return branchCommissionRate;
    }

    public void setBranchCommissionRate(double branchCommissionRate) {
        this.branchCommissionRate = branchCommissionRate;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

//    @Override
//    public String toString() {
//        return "Branch{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
////                ", vehicles=" + vehicles + //break the SF error due to circular dependancy
//                ", distance=" + distance +
//                ", branchCommissionRate=" + branchCommissionRate +
//                '}';
//    }
}


