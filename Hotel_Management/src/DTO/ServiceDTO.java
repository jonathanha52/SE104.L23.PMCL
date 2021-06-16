/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author huynh
 */
public class ServiceDTO {
    private int serviceID;
    private String serviceName, serviceUnit, serviceDepartment;
    private double serviceUnitPrice;

    public void setServiceDepartment(String serviceDepartment) {
        this.serviceDepartment = serviceDepartment;
    }

    public String getServiceDepartment() {
        return serviceDepartment;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public void setServiceUnitPrice(double serviceUnitPrice) {
        this.serviceUnitPrice = serviceUnitPrice;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceUnit() {
        return serviceUnit;
    }

    public double getServiceUnitPrice() {
        return serviceUnitPrice;
    }
}
