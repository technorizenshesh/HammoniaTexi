package com.hammoniatexiapp.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SuccessResSignUp implements Serializable {

    @SerializedName("result")
    @Expose
    public Result result;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("car_type_id")
        @Expose
        private String carTypeId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("user_name")
        @Expose
        private String userName;
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
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("online_status")
        @Expose
        private String onlineStatus;
        @SerializedName("screen_count")
        @Expose
        private String screenCount;
        @SerializedName("register_id")
        @Expose
        private String registerId;
        @SerializedName("bank_name")
        @Expose
        private String bankName;
        @SerializedName("acc_holder_name")
        @Expose
        private String accHolderName;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("social_id")
        @Expose
        private String socialId;
        @SerializedName("iban")
        @Expose
        private String iban;
        @SerializedName("code_bic")
        @Expose
        private String codeBic;
        @SerializedName("wallet")
        @Expose
        private String wallet;
        @SerializedName("promo_code")
        @Expose
        private String promoCode;
        @SerializedName("car_model")
        @Expose
        private String carModel;
        @SerializedName("car_color")
        @Expose
        private String carColor;
        @SerializedName("car_document")
        @Expose
        private String carDocument;
        @SerializedName("year_of_manufacture")
        @Expose
        private String yearOfManufacture;
        @SerializedName("car_image")
        @Expose
        private String carImage;
        @SerializedName("license")
        @Expose
        private String license;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("car_number")
        @Expose
        private String carNumber;
        @SerializedName("identity")
        @Expose
        private String identity;
        @SerializedName("badge")
        @Expose
        private String badge;
        @SerializedName("bearing")
        @Expose
        private Object bearing;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("date_time")
        @Expose
        private String dateTime;
        @SerializedName("cust_id")
        @Expose
        private String custId;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("car_id")
        @Expose
        private String carId;
        @SerializedName("verify_code")
        @Expose
        private String verifyCode;
        @SerializedName("lang")
        @Expose
        private String lang;
        @SerializedName("nxn_point")
        @Expose
        private String nxnPoint;
        @SerializedName("insurance")
        @Expose
        private String insurance;
        @SerializedName("lang_id")
        @Expose
        private String langId;
        @SerializedName("activity")
        @Expose
        private String activity;
        @SerializedName("booking_status")
        @Expose
        private String bookingStatus;
        @SerializedName("stripe_account_id")
        @Expose
        private String stripeAccountId;
        @SerializedName("vtc_card")
        @Expose
        private String vtcCard;
        @SerializedName("kbis")
        @Expose
        private String kbis;
        @SerializedName("otp")
        @Expose
        private String otp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCarTypeId() {
            return carTypeId;
        }

        public void setCarTypeId(String carTypeId) {
            this.carTypeId = carTypeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getScreenCount() {
            return screenCount;
        }

        public void setScreenCount(String screenCount) {
            this.screenCount = screenCount;
        }

        public String getRegisterId() {
            return registerId;
        }

        public void setRegisterId(String registerId) {
            this.registerId = registerId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getAccHolderName() {
            return accHolderName;
        }

        public void setAccHolderName(String accHolderName) {
            this.accHolderName = accHolderName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getSocialId() {
            return socialId;
        }

        public void setSocialId(String socialId) {
            this.socialId = socialId;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getCodeBic() {
            return codeBic;
        }

        public void setCodeBic(String codeBic) {
            this.codeBic = codeBic;
        }

        public String getWallet() {
            return wallet;
        }

        public void setWallet(String wallet) {
            this.wallet = wallet;
        }

        public String getPromoCode() {
            return promoCode;
        }

        public void setPromoCode(String promoCode) {
            this.promoCode = promoCode;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getCarColor() {
            return carColor;
        }

        public void setCarColor(String carColor) {
            this.carColor = carColor;
        }

        public String getCarDocument() {
            return carDocument;
        }

        public void setCarDocument(String carDocument) {
            this.carDocument = carDocument;
        }

        public String getYearOfManufacture() {
            return yearOfManufacture;
        }

        public void setYearOfManufacture(String yearOfManufacture) {
            this.yearOfManufacture = yearOfManufacture;
        }

        public String getCarImage() {
            return carImage;
        }

        public void setCarImage(String carImage) {
            this.carImage = carImage;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
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

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getCustId() {
            return custId;
        }

        public void setCustId(String custId) {
            this.custId = custId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getNxnPoint() {
            return nxnPoint;
        }

        public void setNxnPoint(String nxnPoint) {
            this.nxnPoint = nxnPoint;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getLangId() {
            return langId;
        }

        public void setLangId(String langId) {
            this.langId = langId;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public String getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(String bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public String getStripeAccountId() {
            return stripeAccountId;
        }

        public void setStripeAccountId(String stripeAccountId) {
            this.stripeAccountId = stripeAccountId;
        }

        public String getVtcCard() {
            return vtcCard;
        }

        public void setVtcCard(String vtcCard) {
            this.vtcCard = vtcCard;
        }

        public String getKbis() {
            return kbis;
        }

        public void setKbis(String kbis) {
            this.kbis = kbis;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

    }
}
