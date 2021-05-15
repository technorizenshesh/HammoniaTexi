package com.taximobility.Interfaces;


import com.taximobility.pojos.ModelCurrentBooking;

public interface onSearchingDialogListener {
    void onRequestAccepted(ModelCurrentBooking data);
    void onRequestCancel();
    void onDriverNotFound();
}
