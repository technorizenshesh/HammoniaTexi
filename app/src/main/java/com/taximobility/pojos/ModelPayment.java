package com.taximobility.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelPayment {
    @SerializedName("no_of_seats")
    @Expose
    private String noOfSeats;
    @SerializedName("car_charge")
    @Expose
    private String carCharge;
    @SerializedName("ride_time_charge_permin")
    @Expose
    private String rideTimeChargePermin;
    @SerializedName("per_km")
    @Expose
    private String perKm;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("miles")
    @Expose
    private String miles;
    @SerializedName("perMin")
    @Expose
    private String perMin;
    @SerializedName("per_min_charge")
    @Expose
    private String perMinCharge;
    @SerializedName("per_miles_charge")
    @Expose
    private String perMilesCharge;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("discount_percent")
    @Expose
    private String discountPercent;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("night_charge_amount")
    @Expose
    private String nightChargeAmount;
    @SerializedName("service_tax")
    @Expose
    private String serviceTax;
    @SerializedName("service_tax_amount")
    @Expose
    private String serviceTaxAmount;
    @SerializedName("commission_to_site")
    @Expose
    private String commissionToSite;
    @SerializedName("card_details")
    @Expose
    private List<Object> cardDetails = null;
    @SerializedName("min_way")
    @Expose
    private String minWay;
    @SerializedName("estimate_time")
    @Expose
    private String estimateTime;
    @SerializedName("booking_detail")
    @Expose
    private ModelCurrentBookingResult bookingDetail;
    @SerializedName("user_detail")
    @Expose
    private List<UserDetail> userDetail = null;
    @SerializedName("driver_amount_j")
    @Expose
    private String driverAmountJ;
    @SerializedName("admin_amount_j")
    @Expose
    private String adminAmountJ;
    @SerializedName("pay_status")
    @Expose
    private String payStatus;

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getCarCharge() {
        return carCharge;
    }

    public void setCarCharge(String carCharge) {
        this.carCharge = carCharge;
    }

    public String getRideTimeChargePermin() {
        return rideTimeChargePermin;
    }

    public void setRideTimeChargePermin(String rideTimeChargePermin) {
        this.rideTimeChargePermin = rideTimeChargePermin;
    }

    public String getPerKm() {
        return perKm;
    }

    public void setPerKm(String perKm) {
        this.perKm = perKm;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getPerMin() {
        return perMin;
    }

    public void setPerMin(String perMin) {
        this.perMin = perMin;
    }

    public String getPerMinCharge() {
        return perMinCharge;
    }

    public void setPerMinCharge(String perMinCharge) {
        this.perMinCharge = perMinCharge;
    }

    public String getPerMilesCharge() {
        return perMilesCharge;
    }

    public void setPerMilesCharge(String perMilesCharge) {
        this.perMilesCharge = perMilesCharge;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNightChargeAmount() {
        return nightChargeAmount;
    }

    public void setNightChargeAmount(String nightChargeAmount) {
        this.nightChargeAmount = nightChargeAmount;
    }

    public String getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(String serviceTax) {
        this.serviceTax = serviceTax;
    }

    public String getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(String serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public String getCommissionToSite() {
        return commissionToSite;
    }

    public void setCommissionToSite(String commissionToSite) {
        this.commissionToSite = commissionToSite;
    }

    public List<Object> getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(List<Object> cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getMinWay() {
        return minWay;
    }

    public void setMinWay(String minWay) {
        this.minWay = minWay;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public ModelCurrentBookingResult getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(ModelCurrentBookingResult bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }

    public String getDriverAmountJ() {
        return driverAmountJ;
    }

    public void setDriverAmountJ(String driverAmountJ) {
        this.driverAmountJ = driverAmountJ;
    }

    public String getAdminAmountJ() {
        return adminAmountJ;
    }

    public void setAdminAmountJ(String adminAmountJ) {
        this.adminAmountJ = adminAmountJ;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
