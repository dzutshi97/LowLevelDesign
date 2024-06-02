package com.lld.CarRental.models;

import java.util.UUID;

public abstract class Vehicle {

    private String id;
    private String name;
    private Branch branch;
    private User owner;
    private Double rate;
    private ReservationStatus reservationStatus;

    public Vehicle(String name, Branch branch,Double rate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.branch = branch;
        this.reservationStatus = ReservationStatus.AVAILABLE;
        this.rate = rate + branch.getBranchCommissionRate();
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

//    @Override
//    public String toString() {
//        return "Vehicle{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", branch=" + branch +
//                ", owner=" + owner +
//                ", rate=" + rate +
//                ", reservationStatus=" + reservationStatus +
//                '}';
//    }
}
