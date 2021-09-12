package com.hammoniatexiapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.utils.Utils.Tools;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelRideHistory implements Serializable {
    @SerializedName("result")
    @Expose
    private ArrayList<Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public class Result implements Serializable{

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("driver_id")
        @Expose
        private String driverId;
        @SerializedName("picuplocation")
        @Expose
        private String picuplocation;
        @SerializedName("service_type")
        @Expose
        private String serviceType;
        @SerializedName("dropofflocation")
        @Expose
        private String dropofflocation;
        @SerializedName("picuplat")
        @Expose
        private String picuplat;
        @SerializedName("pickuplon")
        @Expose
        private String pickuplon;
        @SerializedName("droplat")
        @Expose
        private String droplat;
        @SerializedName("droplon")
        @Expose
        private String droplon;
        @SerializedName("shareride_type")
        @Expose
        private String sharerideType;
        @SerializedName("booktype")
        @Expose
        private String booktype;
        @SerializedName("car_type_id")
        @Expose
        private String carTypeId;
        @SerializedName("car_seats")
        @Expose
        private String carSeats;
        @SerializedName("passenger")
        @Expose
        private String passenger;
        @SerializedName("booked_seats")
        @Expose
        private String bookedSeats;
        @SerializedName("req_datetime")
        @Expose
        private String reqDatetime;
        @SerializedName("timezone")
        @Expose
        private String timezone;
        @SerializedName("picklatertime")
        @Expose
        private String picklatertime;
        @SerializedName("picklaterdate")
        @Expose
        private String picklaterdate;
        @SerializedName("route_img")
        @Expose
        private String routeImg;
        @SerializedName("start_time")
        @Expose
        private String startTime;
        @SerializedName("end_time")
        @Expose
        private String endTime;
        @SerializedName("wt_start_time")
        @Expose
        private String wtStartTime;
        @SerializedName("wt_end_time")
        @Expose
        private String wtEndTime;
        @SerializedName("accept_time")
        @Expose
        private String acceptTime;
        @SerializedName("waiting_status")
        @Expose
        private String waitingStatus;
        @SerializedName("waiting_cnt")
        @Expose
        private String waitingCnt;
        @SerializedName("reason_id")
        @Expose
        private String reasonId;
        @SerializedName("card_id")
        @Expose
        private String cardId;
        @SerializedName("apply_code")
        @Expose
        private String applyCode;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("favorite_ride")
        @Expose
        private String favoriteRide;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("user_rating_status")
        @Expose
        private String userRatingStatus;
        @SerializedName("tip_amount")
        @Expose
        private String tipAmount;
        @SerializedName("my_booking")
        @Expose
        private String myBooking;
        @SerializedName("fav_status")
        @Expose
        private String favStatus;

        @SerializedName("driver_details")
        @Expose
        private ArrayList<UserDetail> driverDetails = null;
        @SerializedName("user_details")
        @Expose
        private ArrayList<UserDetail> userDetails = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getPicuplocation() {
            return picuplocation;
        }

        public void setPicuplocation(String picuplocation) {
            this.picuplocation = picuplocation;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getDropofflocation() {
            return dropofflocation;
        }

        public void setDropofflocation(String dropofflocation) {
            this.dropofflocation = dropofflocation;
        }

        public String getPicuplat() {
            return picuplat;
        }

        public void setPicuplat(String picuplat) {
            this.picuplat = picuplat;
        }

        public String getPickuplon() {
            return pickuplon;
        }

        public void setPickuplon(String pickuplon) {
            this.pickuplon = pickuplon;
        }

        public String getDroplat() {
            return droplat;
        }

        public void setDroplat(String droplat) {
            this.droplat = droplat;
        }

        public String getDroplon() {
            return droplon;
        }

        public void setDroplon(String droplon) {
            this.droplon = droplon;
        }

        public String getSharerideType() {
            return sharerideType;
        }

        public void setSharerideType(String sharerideType) {
            this.sharerideType = sharerideType;
        }

        public String getBooktype() {
            return booktype;
        }

        public void setBooktype(String booktype) {
            this.booktype = booktype;
        }

        public String getCarTypeId() {
            return carTypeId;
        }

        public void setCarTypeId(String carTypeId) {
            this.carTypeId = carTypeId;
        }

        public String getCarSeats() {
            return carSeats;
        }

        public void setCarSeats(String carSeats) {
            this.carSeats = carSeats;
        }

        public String getPassenger() {
            return passenger;
        }

        public void setPassenger(String passenger) {
            this.passenger = passenger;
        }

        public String getBookedSeats() {
            return bookedSeats;
        }

        public void setBookedSeats(String bookedSeats) {
            this.bookedSeats = bookedSeats;
        }

        public String getReqDatetime() {
            return reqDatetime;
        }

        public void setReqDatetime(String reqDatetime) {
            this.reqDatetime = reqDatetime;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getPicklatertime() {
            return picklatertime;
        }

        public void setPicklatertime(String picklatertime) {
            this.picklatertime = picklatertime;
        }

        public String getPicklaterdate() {
            return picklaterdate;
        }

        public void setPicklaterdate(String picklaterdate) {
            this.picklaterdate = picklaterdate;
        }

        public String getRouteImg() {
            return routeImg;
        }

        public void setRouteImg(String routeImg) {
            this.routeImg = routeImg;
        }

        public String getStartTime() {
            return Tools.ChangeDateFormat("yyyy-MM-dd HH:mm:ss","hh:mm a",startTime);
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return Tools.ChangeDateFormat("yyyy-MM-dd HH:mm:ss","hh:mm a",endTime);
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getWtStartTime() {
            return wtStartTime;
        }

        public void setWtStartTime(String wtStartTime) {
            this.wtStartTime = wtStartTime;
        }

        public String getWtEndTime() {
            return wtEndTime;
        }

        public void setWtEndTime(String wtEndTime) {
            this.wtEndTime = wtEndTime;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public String getWaitingStatus() {
            return waitingStatus;
        }

        public void setWaitingStatus(String waitingStatus) {
            this.waitingStatus = waitingStatus;
        }

        public String getWaitingCnt() {
            return waitingCnt;
        }

        public void setWaitingCnt(String waitingCnt) {
            this.waitingCnt = waitingCnt;
        }

        public String getReasonId() {
            return reasonId;
        }

        public void setReasonId(String reasonId) {
            this.reasonId = reasonId;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getApplyCode() {
            return applyCode;
        }

        public void setApplyCode(String applyCode) {
            this.applyCode = applyCode;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getFavoriteRide() {
            return favoriteRide;
        }

        public void setFavoriteRide(String favoriteRide) {
            this.favoriteRide = favoriteRide;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserRatingStatus() {
            return userRatingStatus;
        }

        public void setUserRatingStatus(String userRatingStatus) {
            this.userRatingStatus = userRatingStatus;
        }

        public String getTipAmount() {
            return tipAmount;
        }

        public void setTipAmount(String tipAmount) {
            this.tipAmount = tipAmount;
        }

        public String getMyBooking() {
            return myBooking;
        }

        public void setMyBooking(String myBooking) {
            this.myBooking = myBooking;
        }

        public String getFavStatus() {
            return favStatus;
        }

        public void setFavStatus(String favStatus) {
            this.favStatus = favStatus;
        }


        public ArrayList<UserDetail> getDriverDetails() {
            return driverDetails;
        }

        public void setDriverDetails(ArrayList<UserDetail> driverDetails) {
            this.driverDetails = driverDetails;
        }

        public ArrayList<UserDetail> getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(ArrayList<UserDetail> userDetails) {
            this.userDetails = userDetails;
        }

    }
}
