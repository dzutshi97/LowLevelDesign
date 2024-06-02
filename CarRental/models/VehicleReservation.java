package com.lld.CarRental.models;

import java.util.UUID;

public class VehicleReservation {

    private String reservationID;
    private Vehicle vehicle;
    private User user;
    private Branch branch;
    private PaymentStatus paymentStatus;
    private Double billAmount;
    private Integer fromDateTime;
    private Integer endDateTime;

   // private Bill bill; //this can contain total amount

    public VehicleReservation() {
        this.reservationID = UUID.randomUUID().toString();
    }

    public Integer getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Integer endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(Integer fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
