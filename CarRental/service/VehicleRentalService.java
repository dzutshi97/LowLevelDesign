package com.lld.CarRental.service;

import com.lld.CarRental.exception.VehicleNotFoundException;
import com.lld.CarRental.models.*;
import com.lld.CarRental.repository.VehicleDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleRentalService {

    VehicleDao vehicleDao;
    SearchVehicleStrategy searchVehicleStrategy;

    public VehicleRentalService() {
        this.vehicleDao = new VehicleDao();
    }

    public void onboardStation(VehicleType vehicleType){

        //station 1
        Branch branch = new Branch("mumbai",100,30);
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle car = new Car("Sedan Alto",branch, 100.0);
        vehicles.add(car);

        Vehicle bike = new Car("Honda City",branch,200.0);
        vehicles.add(bike);

        Vehicle car2 = new Car("Maruti",branch,50.0);
        vehicles.add(car2);

        branch.setVehicles(vehicles);
        vehicleDao.getVehicleDao().put(car.getId(),car);
        vehicleDao.getVehicleDao().put(bike.getId(),bike);
        vehicleDao.getVehicleDao().put(car2.getId(),car2);

        //station 2
        Branch branch2 = new Branch("goa",200,20);
        List<Vehicle> vehiclesb2 = new ArrayList<>();
        Vehicle carb2 = new Car("Sedan",branch2,100.0);
        vehiclesb2.add(carb2);

        Vehicle bikeb2 = new Car("Honda",branch2,200.0);
        vehiclesb2.add(bikeb2);

        Vehicle car2b2 = new Car("XUV",branch2,60.0);
        vehiclesb2.add(car2b2);

        branch2.setVehicles(vehiclesb2);
        vehicleDao.getVehicleDao().put(carb2.getId(),carb2);
        vehicleDao.getVehicleDao().put(bikeb2.getId(),bikeb2);
        vehicleDao.getVehicleDao().put(car2b2.getId(),car2b2);

    }

    public Vehicle searchVehicle(VehicleType vehicleType){
        List<Vehicle> vehicles = new ArrayList<>();
        Map<String,Vehicle> vehiclesDao = vehicleDao.getVehicleDao();
        for(Map.Entry<String,Vehicle> v: vehiclesDao.entrySet()){
            vehicles.add(v.getValue());
        }

        this.searchVehicleStrategy = new ChepeastRateStrategy();
        Vehicle vehicle = this.searchVehicleStrategy.search(vehicles); //you can use optional here instead of returning Vehicle
        if(vehicle == null){
            throw new VehicleNotFoundException();
        }
        System.out.println("Vehicle found. Branch Name- "+vehicle.getBranch().getName()+ " Name - "+ vehicle.getName()+" Rate - "+vehicle.getRate());
        return vehicle;
    }

    public void BookVehicle(Vehicle vehicle){
        VehicleReservation reservation = new VehicleReservation();
        vehicle.setReservationStatus(ReservationStatus.BOOKED);
        reservation.setVehicle(vehicle);
        reservation.setPaymentStatus(PaymentStatus.DONE);
//        reservation.setBillAmount(); //store amount per hour. Calculate: Total duration * amount per hour. Maybe store fromDateTime and endDateTime as 2 fields in resr
        // create a dao for reservation and store there

    }
}
