package com.taximobility.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelUser {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cust_id")
    @Expose
    private Object custId;
    @SerializedName("card_id")
    @Expose
    private Object cardId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone_code")
    @Expose
    private String phoneCode;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("social_id")
    @Expose
    private Object socialId;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("register_id")
    @Expose
    private String registerId;
    @SerializedName("ios_register_id")
    @Expose
    private String iosRegisterId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("car_id")
    @Expose
    private Object carId;
    @SerializedName("car_type_id")
    @Expose
    private Object carTypeId;
    @SerializedName("online_status")
    @Expose
    private String onlineStatus;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("verify_code")
    @Expose
    private Object verifyCode;
    @SerializedName("lang")
    @Expose
    private Object lang;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("place")
    @Expose
    private Object place;
    @SerializedName("promo_code")
    @Expose
    private String promoCode;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("nxn_point")
    @Expose
    private String nxnPoint;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("car_model")
    @Expose
    private Object carModel;
    @SerializedName("year_of_manufacture")
    @Expose
    private Object yearOfManufacture;
    @SerializedName("car_color")
    @Expose
    private Object carColor;
    @SerializedName("car_number")
    @Expose
    private Object carNumber;
    @SerializedName("car_image")
    @Expose
    private String carImage;
    @SerializedName("car_document")
    @Expose
    private String carDocument;
    @SerializedName("insurance")
    @Expose
    private String insurance;
    @SerializedName("document")
    @Expose
    private String document;
    @SerializedName("identity")
    @Expose
    private String identity;
    @SerializedName("lang_id")
    @Expose
    private Object langId;
    @SerializedName("activity")
    @Expose
    private Object activity;
    @SerializedName("badge")
    @Expose
    private String badge;
    @SerializedName("bearing")
    @Expose
    private Object bearing;
    @SerializedName("avg_rating")
    @Expose
    private String avgRating;
    @SerializedName("card_detail")
    @Expose
    private List<Object> cardDetail = new ArrayList<Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getCustId() {
        return custId;
    }

    public void setCustId(Object custId) {
        this.custId = custId;
    }

    public Object getCardId() {
        return cardId;
    }

    public void setCardId(Object cardId) {
        this.cardId = cardId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getSocialId() {
        return socialId;
    }

    public void setSocialId(Object socialId) {
        this.socialId = socialId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getIosRegisterId() {
        return iosRegisterId;
    }

    public void setIosRegisterId(String iosRegisterId) {
        this.iosRegisterId = iosRegisterId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCarId() {
        return carId;
    }

    public void setCarId(Object carId) {
        this.carId = carId;
    }

    public Object getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Object carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Object getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Object verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNxnPoint() {
        return nxnPoint;
    }

    public void setNxnPoint(String nxnPoint) {
        this.nxnPoint = nxnPoint;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Object getCarModel() {
        return carModel;
    }

    public void setCarModel(Object carModel) {
        this.carModel = carModel;
    }

    public Object getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Object yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Object getCarColor() {
        return carColor;
    }

    public void setCarColor(Object carColor) {
        this.carColor = carColor;
    }

    public Object getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Object carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarDocument() {
        return carDocument;
    }

    public void setCarDocument(String carDocument) {
        this.carDocument = carDocument;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Object getLangId() {
        return langId;
    }

    public void setLangId(Object langId) {
        this.langId = langId;
    }

    public Object getActivity() {
        return activity;
    }

    public void setActivity(Object activity) {
        this.activity = activity;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Object getBearing() {
        return bearing;
    }

    public void setBearing(Object bearing) {
        this.bearing = bearing;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public List<Object> getCardDetail() {
        return cardDetail;
    }

    public void setCardDetail(List<Object> cardDetail) {
        this.cardDetail = cardDetail;
    }
}
